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

    // 1. MOSTRAR EL PANEL CON TODOS LOS DATOS PARA EL FORMULARIO
    @GetMapping("/menu")
    public String mostrarMenu(Model model) {
        List<Gerente> gerentes = gerenteRepository.findAll();
        model.addAttribute("nombreGerente", gerentes.isEmpty() ? "Sin Gerente" : gerentes.get(0).getNombre());
        model.addAttribute("alimentos", alimentoRepository.findAll());

        // Pasamos chefs e ingredientes para llenar los "Select" y "Checkboxes" del formulario
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
        if (chefId != null) { chefRepository.findById(chefId).ifPresent(receta::setChef); }
        if (ingredientesIds != null && !ingredientesIds.isEmpty()) {
            receta.setIngredientes(ingredienteRepository.findAllById(ingredientesIds));
        } else {
            receta.getIngredientes().clear();
        }

        recetaRepository.save(receta);
        alimento.setReceta(receta);
        alimentoRepository.save(alimento);

        // --- NUEVA LÓGICA: GUARDAR LA IMAGEN SUBIDA ---
        if (imagen != null && !imagen.isEmpty()) {
            try {
                // Definimos la ruta donde guardamos las fotos estáticas
                Path directorioImagenes = Paths.get("src/main/resources/static/img");
                if (!Files.exists(directorioImagenes)) {
                    Files.createDirectories(directorioImagenes);
                }

                // Guardamos la foto con el nombre del plato (ej: "Empanadas.jpg")
                String nombreFoto = alimento.getNombre() + ".jpg";
                Path rutaCompleta = directorioImagenes.resolve(nombreFoto);

                // Copiamos el archivo subido a nuestra carpeta (reemplaza si ya existe)
                Files.copy(imagen.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

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

    // 5. GUARDAR UN NUEVO INGREDIENTE DESDE LA VISTA
    @PostMapping("/ingrediente/guardar")
    public String guardarIngrediente(@RequestParam("descripcion") String descripcion) {
        com.example.springMenuRivera.modelo.Ingrediente nuevoIngrediente = new com.example.springMenuRivera.modelo.Ingrediente();
        nuevoIngrediente.setDescripcion(descripcion);

        // Si tu clase Ingrediente exige una cantidad, descomenta esta línea y ponle un valor base:
        // nuevoIngrediente.setCantidad(1);

        ingredienteRepository.save(nuevoIngrediente);
        return "redirect:/menu";
    }
}