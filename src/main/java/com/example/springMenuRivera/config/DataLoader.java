package com.example.springMenuRivera.config;

import com.example.springMenuRivera.modelo.*;
import com.example.springMenuRivera.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner cargarDatos(GerenteRepository gerenteRepo,
                                         ChefRepository chefRepo,
                                         AlimentoRepository alimentoRepo,
                                         RecetaRepository recetaRepo,
                                         IngredienteRepository ingredienteRepo) {
        return args -> {

            // Solo inserta si la base de datos está vacía
            if (gerenteRepo.count() == 0) {

                // 1. GERENTE
                Gerente gerente = new Gerente();
                gerente.setNombre("Carlos Mendez");
                gerenteRepo.save(gerente);

                // 2. CHEFS
                Chef chef1 = new Chef(); chef1.setNombre("Francis Mallmann");
                Chef chef2 = new Chef(); chef2.setNombre("Doña Petrona");
                Chef chef3 = new Chef(); chef3.setNombre("Germán Martitegui");
                chefRepo.saveAll(List.of(chef1, chef2, chef3));

                // 3. INGREDIENTES (Creamos un stock de ingredientes para usar)
                Ingrediente iCarne = new Ingrediente(100, "Carne picada");
                Ingrediente iCebolla = new Ingrediente(50, "Cebolla");
                Ingrediente iAceituna = new Ingrediente(30, "Aceitunas");
                Ingrediente iCostillar = new Ingrediente(20, "Costillar de vaca");
                Ingrediente iSal = new Ingrediente(100, "Sal gruesa");
                Ingrediente iNalga = new Ingrediente(40, "Nalga para milanesa");
                Ingrediente iMozzarella = new Ingrediente(30, "Queso Mozzarella");
                Ingrediente iTomate = new Ingrediente(60, "Tomate fresco");
                Ingrediente iMaiz = new Ingrediente(40, "Maíz blanco");
                Ingrediente iChorizo = new Ingrediente(50, "Chorizo y embutidos");
                Ingrediente iPan = new Ingrediente(50, "Pan francés");
                Ingrediente iChimi = new Ingrediente(20, "Chimichurri");
                Ingrediente iHuevo = new Ingrediente(60, "Huevos");
                Ingrediente iBife = new Ingrediente(25, "Bife de chorizo");
                Ingrediente iPapa = new Ingrediente(80, "Papas");
                Ingrediente iProvolone = new Ingrediente(30, "Queso Provolone");
                Ingrediente iLeche = new Ingrediente(20, "Leche");
                Ingrediente iDulce = new Ingrediente(15, "Dulce de Leche");

                // Guardamos todos los ingredientes juntos
                ingredienteRepo.saveAll(List.of(iCarne, iCebolla, iAceituna, iCostillar, iSal, iNalga,
                        iMozzarella, iTomate, iMaiz, iChorizo, iPan, iChimi,
                        iHuevo, iBife, iPapa, iProvolone, iLeche, iDulce));

                // 4. RECETAS (Usamos el constructor que ya tenías armado)
                Receta r1 = new Receta(chef2, "Empanadas Mendocinas", "Rehogar la cebolla, agregar la carne picada a cuchillo, condimentar y añadir aceitunas. Armar y hornear a 180°C hasta dorar.");
                r1.getIngredientes().addAll(List.of(iCarne, iCebolla, iAceituna));

                Receta r2 = new Receta(chef1, "Asado de Tira", "Hacer buen fuego con leña. Salar el costillar con sal gruesa y asar a fuego muy lento por 2 horas de cada lado.");
                r2.getIngredientes().addAll(List.of(iCostillar, iSal));

                Receta r3 = new Receta(chef3, "Milanesa Napolitana", "Empanar la nalga, freír, cubrir con salsa de tomate y abundante mozzarella. Gratinar al horno fuerte.");
                r3.getIngredientes().addAll(List.of(iNalga, iTomate, iMozzarella));

                Receta r4 = new Receta(chef2, "Locro Patrio", "Hervir el maíz blanco con los chorizos, panceta y carnes durante 4 horas a fuego lento hasta que espese.");
                r4.getIngredientes().addAll(List.of(iMaiz, iChorizo, iSal));

                Receta r5 = new Receta(chef1, "Choripán Clásico", "Asar el chorizo a la parrilla, abrir al medio (mariposa), tostar el pan francés y bañar en chimichurri.");
                r5.getIngredientes().addAll(List.of(iChorizo, iPan, iChimi));

                Receta r6 = new Receta(chef2, "Tomaticán Cuyano", "Rehogar cebolla, agregar tomates frescos picados y dejar cocinar. Romper huevos encima y revolver hasta cuajar.");
                r6.getIngredientes().addAll(List.of(iTomate, iCebolla, iHuevo));

                Receta r7 = new Receta(chef1, "Bife de Chorizo a caballo", "Cocinar el bife a la plancha de hierro bien caliente. Servir con papas fritas y dos huevos fritos encima.");
                r7.getIngredientes().addAll(List.of(iBife, iPapa, iHuevo));

                Receta r8 = new Receta(chef3, "Provoleta a la Parrilla", "Salar el provolone y asar a fuego fuerte en la parrilla hasta dorar por fuera y derretir por dentro.");
                r8.getIngredientes().addAll(List.of(iProvolone, iSal));

                Receta r9 = new Receta(chef2, "Pastel de Papa", "Preparar un puré. Intercalar en una fuente capas de carne picada rehogada con cebolla y el puré. Gratinar.");
                r9.getIngredientes().addAll(List.of(iCarne, iPapa, iCebolla));

                Receta r10 = new Receta(chef3, "Flan Mixto", "Mezclar huevos y leche, acaramelar un molde y cocinar a baño maría. Servir frío con cucharada generosa de dulce de leche.");
                r10.getIngredientes().addAll(List.of(iHuevo, iLeche, iDulce));

                recetaRepo.saveAll(List.of(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10));

                // 5. ALIMENTOS / PLATILLOS
                Alimento a1 = new Alimento("Docena de Empanadas", 4500.0); a1.setReceta(r1);
                Alimento a2 = new Alimento("Porción de Asado", 8500.0); a2.setReceta(r2);
                Alimento a3 = new Alimento("Milanesa Napolitana con fritas", 6000.0); a3.setReceta(r3);
                Alimento a4 = new Alimento("Cazuela de Locro", 5000.0); a4.setReceta(r4);
                Alimento a5 = new Alimento("Choripán al paso", 2500.0); a5.setReceta(r5);
                Alimento a6 = new Alimento("Tomaticán", 4000.0); a6.setReceta(r6);
                Alimento a7 = new Alimento("Bife de Chorizo a Caballo", 9000.0); a7.setReceta(r7);
                Alimento a8 = new Alimento("Provoleta fundida", 3500.0); a8.setReceta(r8);
                Alimento a9 = new Alimento("Porción de Pastel de Papa", 4200.0); a9.setReceta(r9);
                Alimento a10 = new Alimento("Flan Casero Mixto", 2000.0); a10.setReceta(r10);

                alimentoRepo.saveAll(List.of(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10));
            }
        };
    }
}