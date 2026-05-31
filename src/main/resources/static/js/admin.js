function abrirModalFormulario(botonElemento = null) {
    const modal = new bootstrap.Modal(document.getElementById('modalPlato'));
    const buscador = document.getElementById('buscadorIngredientes');
    if(buscador) { buscador.value = ''; filtrarIngredientes(); }

    document.querySelectorAll('input[name="ingredientesIds"]').forEach(cb => cb.checked = false);

    if (botonElemento) {
        document.getElementById('tituloModal').innerText = "Modificar Plato";
        document.getElementById('inputId').value = botonElemento.getAttribute('data-id');
        document.getElementById('inputNombre').value = botonElemento.getAttribute('data-nombre');
        document.getElementById('inputPrecio').value = botonElemento.getAttribute('data-precio');
    } else {
        document.getElementById('tituloModal').innerText = "Nuevo Plato Completo";
        document.getElementById('inputId').value = "";
        document.getElementById('inputNombre').value = "";
        document.getElementById('inputPrecio').value = "";
        document.getElementById('inputChef').value = "";
        document.getElementById('inputDescripcion').value = "";
    }
    modal.show();
}

function verDetalleReceta(nombre) {
    fetch(`/api/receta?nombreAlimento=${encodeURIComponent(nombre)}`)
        .then(response => response.json())
        .then(data => {
            Swal.fire({
                title: `<h2 style="color: #ff9f1c; margin:0; text-transform: uppercase; font-weight: bold;">${nombre}</h2>`,
                html: `
                        <div style="text-align: left; margin-top: 20px; font-size: 1.1rem; border-top: 1px solid #444; padding-top: 15px;">
                            <p style="color: #ddd;"><i class="bi bi-person-badge me-2" style="color: #ff9f1c; font-size: 1.3rem;"></i> <strong>Chef:</strong> ${data.chef}</p>
                            <p style="color: #ddd;"><i class="bi bi-list-check me-2" style="color: #ff9f1c; font-size: 1.3rem;"></i> <strong>Preparación:</strong><br> <span style="font-size: 1rem; color: #aaa;">${data.descripcion}</span></p>
                            <p style="color: #ddd;"><i class="bi bi-basket me-2" style="color: #ff9f1c; font-size: 1.3rem;"></i> <strong>Ingredientes:</strong><br> <span style="font-size: 1rem; color: #aaa;">${data.ingredientes}</span></p>
                        </div>
                    `,
                background: 'rgba(26, 26, 26, 0.95)',
                backdrop: `rgba(0,0,0,0.6)`,
                confirmButtonText: 'Cerrar',
                confirmButtonColor: '#ff9f1c'
            });
        });
}

function filtrarIngredientes() {
    const input = document.getElementById('buscadorIngredientes');
    const filtro = input.value.toLowerCase();
    const contenedor = document.getElementById('contenedorIngredientes');
    const items = contenedor.getElementsByClassName('ingrediente-item');

    for (let i = 0; i < items.length; i++) {
        const label = items[i].getElementsByClassName('ingrediente-label')[0];
        const texto = label.textContent || label.innerText;
        if (texto.toLowerCase().indexOf(filtro) > -1) {
            items[i].style.display = "";
        } else {
            items[i].style.display = "none";
        }
    }
}

function crearNuevoIngrediente() {
    Swal.fire({
        title: 'Nuevo Ingrediente',
        input: 'text',
        inputLabel: 'Nombre del ingrediente',
        inputPlaceholder: 'Ej. Salsa Picante (Presione Enter para poder Escribir)',
        showCancelButton: true,
        confirmButtonText: '<i class="bi bi-save"></i> Guardar',
        cancelButtonText: 'Cancelar',
        background: '#1a1a1a',
        color: '#fff',
        confirmButtonColor: '#ff9f1c'
    }).then((result) => {
        if (result.isConfirmed && result.value.trim() !== '') {
            const formData = new URLSearchParams();
            formData.append('descripcion', result.value.trim());

            fetch('/api/ingrediente/guardar', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    const container = document.getElementById('contenedorIngredientes');
                    const div = document.createElement('div');
                    div.className = 'form-check ingrediente-item d-flex align-items-center';
                    div.innerHTML = `
                    <input class="form-check-input me-2 mt-0 border-secondary" type="checkbox" name="ingredientesIds" value="${data.id}" id="ing_${data.id}" checked>
                    <label class="form-check-label ingrediente-label flex-grow-1" for="ing_${data.id}" id="label_ing_${data.id}">${data.descripcion}</label>
                    <button type="button" class="btn btn-sm btn-link text-info p-0 ms-2" data-id="${data.id}" data-desc="${data.descripcion}" onclick="modificarIngrediente(this.getAttribute('data-id'), this.getAttribute('data-desc'))">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                `;
                    container.appendChild(div);

                    document.getElementById('buscadorIngredientes').value = '';
                    filtrarIngredientes();

                    Swal.fire({ toast: true, position: 'top-end', icon: 'success', title: 'Ingrediente guardado', showConfirmButton: false, timer: 2000 });
                });
        }
    });
}

function modificarIngrediente(id, nombreActual) {
    Swal.fire({
        title: 'Modificar Ingrediente',
        input: 'text',
        inputValue: nombreActual,
        inputLabel: 'Nuevo nombre',
        showCancelButton: true,
        confirmButtonText: '<i class="bi bi-save"></i> Actualizar',
        cancelButtonText: 'Cancelar',
        background: '#1a1a1a',
        color: '#fff',
        confirmButtonColor: '#ff9f1c'
    }).then((result) => {
        if (result.isConfirmed && result.value.trim() !== '' && result.value.trim() !== nombreActual) {
            const nuevoNombre = result.value.trim();
            const formData = new URLSearchParams();
            formData.append('id', id);
            formData.append('descripcion', nuevoNombre);

            fetch('/api/ingrediente/actualizar', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if(data.success) {
                        document.getElementById('label_ing_' + id).innerText = data.descripcion;
                        const boton = document.querySelector(`button[data-id="${id}"]`);
                        if(boton) boton.setAttribute('data-desc', data.descripcion);
                        Swal.fire({ toast: true, position: 'top-end', icon: 'success', title: 'Ingrediente actualizado', showConfirmButton: false, timer: 2000 });
                    } else {
                        Swal.fire('Error', 'No se pudo actualizar.', 'error');
                    }
                });
        }
    });
}

function crearNuevoChef() {
    Swal.fire({
        title: 'Nuevo Chef',
        input: 'text',
        inputLabel: 'Nombre completo del chef (Presione Enter para poder Escribir)',
        inputPlaceholder: 'Ej. Cocinero 123',
        showCancelButton: true,
        confirmButtonText: '<i class="bi bi-save"></i> Guardar',
        cancelButtonText: 'Cancelar',
        background: '#1a1a1a',
        color: '#fff',
        confirmButtonColor: '#ff9f1c'
    }).then((result) => {
        if (result.isConfirmed && result.value.trim() !== '') {
            const formData = new URLSearchParams();
            formData.append('nombre', result.value.trim());

            fetch('/api/chef/guardar', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    const select = document.getElementById('inputChef');
                    const option = document.createElement('option');
                    option.value = data.id;
                    option.text = data.nombre;
                    select.add(option);
                    select.value = data.id;
                    Swal.fire({ toast: true, position: 'top-end', icon: 'success', title: 'Chef guardado', showConfirmButton: false, timer: 2000 });
                });
        }
    });
}
function eliminarChefSeleccionado() {
    const selectChef = document.getElementById('inputChef');
    const chefId = selectChef.value;
    const chefNombre = selectChef.options[selectChef.selectedIndex]?.text;

    if (!chefId) {
        Swal.fire({ icon: 'warning', title: 'Atención', text: 'Primero selecciona un chef.', background: '#1a1a1a', color: '#fff' });
        return;
    }

    Swal.fire({
        title: `¿Eliminar a ${chefNombre}?`,
        text: "Si no tiene platos asignados, se eliminará permanentemente.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        background: '#1a1a1a',
        color: '#fff'
    }).then((result) => {
        if (result.isConfirmed) {

            const formData = new URLSearchParams();
            formData.append('id', chefId);

            fetch('/api/chef/eliminar', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if(data.success) {
                        // Si se borró en la BD, lo quitamos visualmente del desplegable
                        selectChef.remove(selectChef.selectedIndex);
                        selectChef.value = ""; // Dejamos el desplegable en blanco

                        Swal.fire({ toast: true, position: 'top-end', icon: 'success', title: 'Chef eliminado', showConfirmButton: false, timer: 2000 });
                    } else {
                        // Si falló por Integridad Referencial, mostramos el motivo
                        Swal.fire({ icon: 'error', title: 'Operación denegada', text: data.message, background: '#1a1a1a', color: '#fff' });
                    }
                })
                .catch(error => {
                    Swal.fire('Error', 'Hubo un problema de conexión con el servidor.', 'error');
                });
        }
    });
}

// NUEVA FUNCIÓN: Modificar al Gerente Principal
function modificarGerente(id, nombreActual) {
    Swal.fire({
        title: 'Modificar Perfil',
        input: 'text',
        inputValue: nombreActual,
        inputLabel: 'Nombre del Gerente',
        showCancelButton: true,
        confirmButtonText: '<i class="bi bi-save"></i> Actualizar',
        cancelButtonText: 'Cancelar',
        background: '#1a1a1a',
        color: '#fff',
        confirmButtonColor: '#ff9f1c'
    }).then((result) => {
        if (result.isConfirmed && result.value.trim() !== '' && result.value.trim() !== nombreActual) {
            const nuevoNombre = result.value.trim();
            const formData = new URLSearchParams();
            formData.append('id', id);
            formData.append('nombre', nuevoNombre);

            fetch('/api/gerente/actualizar', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if(data.success) {
                        // Cambiamos el texto en la barra de navegación
                        document.getElementById('nombreGerenteVista').innerText = data.nombre;

                        // Actualizamos el dato oculto del botón por si quiere volver a editarlo
                        const boton = document.querySelector(`button[data-id="${id}"]`);
                        if(boton) boton.setAttribute('data-nombre', data.nombre);

                        Swal.fire({ toast: true, position: 'top-end', icon: 'success', title: 'Perfil actualizado', showConfirmButton: false, timer: 2000 });
                    } else {
                        Swal.fire('Error', 'No se pudo actualizar el perfil.', 'error');
                    }
                });
        }
    });
}