document.addEventListener('DOMContentLoaded', () => {
    const selectMenu = document.getElementById('menuSelect');

    // Escuchamos cuando el gerente cambia de plato
    selectMenu.addEventListener('change', function() {
        const nombreAlimento = this.value;

        // Si vuelve a la opción por defecto, reseteamos la vista
        if (!nombreAlimento) {
            resetearVista();
            return;
        }

        // Buscamos los datos en el backend
        fetch(`/api/receta?nombreAlimento=${encodeURIComponent(nombreAlimento)}`)
            .then(response => response.json())
            .then(data => {
                actualizarVista(data, nombreAlimento);
            })
            .catch(error => {
                console.error('Error al obtener la receta:', error);
                alert('Hubo un problema al cargar los datos.');
            });
    });
});

function actualizarVista(data, nombreAlimento) {
    document.getElementById('labelChef').textContent = data.chef;
    document.getElementById('labelReceta').innerHTML = data.descripcion;
    document.getElementById('labelIngredientes').innerHTML = data.ingredientes;

    // Automatizamos las imágenes. Si no encuentra la foto del plato, carga default.jpg
    const img1 = document.getElementById('imgPlato1');
    const img2 = document.getElementById('imgPlato2');

    img1.src = `/img/${nombreAlimento}.jpg`;
    img2.src = `/img/${nombreAlimento}.jpg`;

    img1.onerror = () => img1.src = '/img/default.jpg';
    img2.onerror = () => img2.src = '/img/default.jpg';
}

function resetearVista() {
    document.getElementById('labelChef').textContent = "Seleccione un plato para ver el chef";
    document.getElementById('labelReceta').innerHTML = "Seleccione un plato para ver la receta";
    document.getElementById('labelIngredientes').innerHTML = "<li>Seleccione un plato para ver ingredientes</li>";

    // Si no hay foto, ocultamos el icono de imagen rota
    document.getElementById('imgPlato1').style.display = 'none';
    document.getElementById('imgPlato2').style.display = 'none';
}
// Usamos SweetAlert2 para mostrar la receta de forma elegante
function verDetalleReceta(nombre) {
    // Usamos encodeURIComponent para evitar problemas con espacios o caracteres especiales
    fetch(`/api/receta?nombreAlimento=${encodeURIComponent(nombre)}`)
        .then(response => response.json())
        .then(data => {
            // Tu lógica de SweetAlert2 aquí...
            Swal.fire({
                title: nombre,
                html: `...`,
                // ... resto de tu configuración
            });
        })
        .catch(error => console.error('Error:', error));
}