package com.example.springMenuRivera.controller;

import com.example.springMenuRivera.modelo.*;
import com.example.springMenuRivera.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;

@Controller
public class MenuWebController {

    @Autowired private GerenteRepository gerenteRepository;
    @Autowired private AlimentoRepository alimentoRepository;
    @Autowired private ChefRepository chefRepository;
    @Autowired private IngredienteRepository ingredienteRepository;
    @Autowired private RecetaRepository recetaRepository;

    // 1. MOSTRAR EL PANEL (Actualizado para enviar el ID del Gerente)
    @GetMapping("/menu")
    public String mostrarMenu(Model model) {
        List<Gerente> gerentes = gerenteRepository.findAll();

        if (gerentes.isEmpty()) {
            model.addAttribute("nombreGerente", "Sin Gerente");
            model.addAttribute("idGerente", -1);
        } else {
            model.addAttribute("nombreGerente", gerentes.get(0).getNombre());
            model.addAttribute("idGerente", gerentes.get(0).getId());
        }

        model.addAttribute("alimentos", alimentoRepository.findAll());
        model.addAttribute("chefsDisponibles", chefRepository.findAll());
        model.addAttribute("ingredientesDisponibles", ingredienteRepository.findAll());

        return "menu-vista";
    }

    // 2. GUARDAR O ACTUALIZAR (AHORA SOPORTA SUBIDA DE IMÁGENES)
    @PostMapping("/menu/guardar")
    public String guardarAlimento(@RequestParam(value = "id", required = false) Integer id,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("precio") Double precio,
                                  @RequestParam(value = "chefId", required = false) Integer chefId,
                                  @RequestParam(value = "descripcionProceso", required = false) String descripcionProceso,
                                  @RequestParam(value = "ingredientesIds", required = false) List<Integer> ingredientesIds,
                                  @RequestParam(value = "imagen", required = false) MultipartFile imagen) { // <-- NUEVO PARÁMETRO

        Alimento alimento = (id != null) ? alimentoRepository.findById(id).orElse(new Alimento()) : new Alimento();
        alimento.setNombre(nombre);
        alimento.setPrecio(precio);

        // Manejo de la Receta
        Receta receta = alimento.getReceta();
        if (receta == null) { receta = new Receta(); }
        receta.setNombreReceta(nombre);
        receta.setDescripcionProceso(descripcionProceso);

        // Asignar Chef e Ingredientes
        if (chefId != null) {
            chefRepository.findById(chefId).ifPresent(receta::setChef);
        } else {
            receta.setChef(null); // ¡Esto lo desasigna formalmente!
        }
        if (ingredientesIds != null && !ingredientesIds.isEmpty()) {
            receta.setIngredientes(ingredienteRepository.findAllById(ingredientesIds));
        } else {
            receta.getIngredientes().clear();
        }

        recetaRepository.save(receta);
        alimento.setReceta(receta);
        alimentoRepository.save(alimento);

        // --- NUEVA LÓGICA: GUARDAR LA IMAGEN SUBIDA EN CARPETA EXTERNA ---
        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Ahora guardamos en una carpeta "uploads" fuera del empaquetado de Spring
                java.nio.file.Path directorioImagenes = java.nio.file.Paths.get("uploads");
                if (!java.nio.file.Files.exists(directorioImagenes)) {
                    java.nio.file.Files.createDirectories(directorioImagenes);
                }

                String nombreFoto = alimento.getNombre() + ".jpg";
                java.nio.file.Path rutaCompleta = directorioImagenes.resolve(nombreFoto);

                java.nio.file.Files.copy(imagen.getInputStream(), rutaCompleta, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                System.out.println("Error al guardar la imagen: " + e.getMessage());
            }
        }

        return "redirect:/menu";
    }

    // 3. ELIMINAR UN PLATO
    @GetMapping("/menu/eliminar/{id}")
    public String eliminarAlimento(@PathVariable("id") Integer id) {
        alimentoRepository.deleteById(id);
        return "redirect:/menu";
    }

    // 4. API PARA VER DETALLES (SWEETALERT2)
    @GetMapping("/api/receta")
    @ResponseBody
    public java.util.Map<String, String> obtenerDetalleReceta(@RequestParam String nombreAlimento) {
        java.util.Map<String, String> response = new java.util.HashMap<>();
        Alimento alimento = alimentoRepository.findAll().stream()
                .filter(a -> a.getNombre().equals(nombreAlimento)).findFirst().orElse(null);

        if (alimento != null && alimento.getReceta() != null) {
            Receta receta = alimento.getReceta();
            response.put("chef", receta.getNombreChef() != null ? receta.getNombreChef().getNombre() : "Sin asignar");
            response.put("descripcion", receta.getDescripcionProceso() != null ? receta.getDescripcionProceso() : "Sin receta");

            StringBuilder ingHtml = new StringBuilder();
            if (receta.getIngredientes() != null) {
                for (Ingrediente i : receta.getIngredientes()) {
                    ingHtml.append("• ").append(i.getDescripcion()).append("<br>");
                }
            }
            response.put("ingredientes", ingHtml.toString());
        } else {
            response.put("chef", "N/A");
            response.put("descripcion", "No configurada");
            response.put("ingredientes", "Ninguno");
        }
        return response;
    }

    // 5. GUARDAR INGREDIENTE VÍA AJAX (Sin recargar la página)
    @PostMapping("/api/ingrediente/guardar")
    @ResponseBody
    public java.util.Map<String, Object> guardarIngredienteApi(@RequestParam("descripcion") String descripcion) {
        com.example.springMenuRivera.modelo.Ingrediente nuevoIng = new com.example.springMenuRivera.modelo.Ingrediente();
        nuevoIng.setDescripcion(descripcion);
        nuevoIng = ingredienteRepository.save(nuevoIng); // Lo guardamos y obtenemos su ID generado

        // Devolvemos un JSON con los datos para que JavaScript actualice la vista
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("id", nuevoIng.getId());
        response.put("descripcion", nuevoIng.getDescripcion());
        return response;
    }

    // 6. GUARDAR CHEF VÍA AJAX (Sin recargar la página)
    @PostMapping("/api/chef/guardar")
    @ResponseBody
    public java.util.Map<String, Object> guardarChefApi(@RequestParam("nombre") String nombre) {
        com.example.springMenuRivera.modelo.Chef nuevoChef = new com.example.springMenuRivera.modelo.Chef();
        nuevoChef.setNombre(nombre);
        nuevoChef = chefRepository.save(nuevoChef);

        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("id", nuevoChef.getId());
        response.put("nombre", nuevoChef.getNombre());
        return response;
    }

    // 7. ELIMINAR UN CHEF EXISTENTE VÍA AJAX
    @PostMapping("/api/chef/eliminar")
    @ResponseBody
    public java.util.Map<String, Object> eliminarChefApi(@RequestParam("id") Integer id) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        try {
            chefRepository.deleteById(id);
            response.put("success", true);
        } catch (Exception e) {
            // Si entra aquí, es porque MySQL bloqueó el borrado (el chef tiene platos)
            response.put("success", false);
            response.put("message", "No se puede eliminar este chef porque tiene platos asignados en el menú. Reasigna sus platos primero.");
        }
        return response;
    }
    // 8. ACTUALIZAR INGREDIENTE VÍA AJAX (Sin recargar la página)
    @PostMapping("/api/ingrediente/actualizar")
    @ResponseBody
    public java.util.Map<String, Object> actualizarIngredienteApi(@RequestParam("id") Integer id, @RequestParam("descripcion") String descripcion) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        try {
            // Buscamos el ingrediente existente, le cambiamos el nombre y lo guardamos
            com.example.springMenuRivera.modelo.Ingrediente ing = ingredienteRepository.findById(id).orElseThrow();
            ing.setDescripcion(descripcion);
            ingredienteRepository.save(ing);

            response.put("success", true);
            response.put("id", ing.getId());
            response.put("descripcion", ing.getDescripcion());
        } catch (Exception e) {
            response.put("success", false);
        }
        return response;
    }

    // 9. ACTUALIZAR GERENTE VÍA AJAX
    @PostMapping("/api/gerente/actualizar")
    @ResponseBody
    public java.util.Map<String, Object> actualizarGerenteApi(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre) {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        try {
            com.example.springMenuRivera.modelo.Gerente gerente = gerenteRepository.findById(id).orElseThrow();
            gerente.setNombre(nombre);
            gerenteRepository.save(gerente);

            response.put("success", true);
            response.put("nombre", gerente.getNombre());
        } catch (Exception e) {
            response.put("success", false);
        }
        return response;
    }
}