const API_BASE_URL = '/api/recursos';

document.addEventListener('DOMContentLoaded', function() {

    document.getElementById('tipoRecurso').addEventListener('change', function() {
        document.querySelectorAll('.resource-form').forEach(form => {
            form.style.display = 'none';
        });
        document.getElementById('form' + capitalizeFirstLetter(this.value)).style.display = 'block';
    });

    cargarRecursos();
});

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

function mostrarError(mensaje) {
    console.error('Error:', mensaje);
    alert('Ocurrió un error: ' + mensaje);
}

async function cargarRecursos() {
    try {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) throw new Error('Error al cargar recursos');
        const data = await response.json();
        mostrarRecursos(data);
    } catch (error) {
        mostrarError(error.message);
    }
}

async function buscarRecursos() {
    const criterio = document.getElementById('criterioBusqueda').value.trim();
    if (!criterio) return cargarRecursos();
    
    try {
        const response = await fetch(`${API_BASE_URL}/buscar?criterio=${encodeURIComponent(criterio)}`);
        if (!response.ok) throw new Error('Error al buscar recursos');
        const data = await response.json();
        mostrarRecursos(data);
    } catch (error) {
        mostrarError(error.message);
    }
}

function mostrarRecursos(recursos) {
    const contenedor = document.getElementById('listaRecursos');
    contenedor.innerHTML = '<h2>Recursos Disponibles</h2>';
    
    if (recursos.length === 0) {
        contenedor.innerHTML += '<p class="no-results">No se encontraron recursos.</p>';
        return;
    }
    
    recursos.forEach(recurso => {
        const div = document.createElement('div');
        div.className = 'recurso';
        
        let detalles = `<h3>${recurso.nombre}</h3>`;
        
        if ('autor' in recurso) {
            detalles += `<p><strong>Tipo:</strong> Libro</p>`;
            detalles += `<p><strong>Autor:</strong> ${recurso.autor}</p>`;
            detalles += `<p><strong>Editorial:</strong> ${recurso.editorial}</p>`;
            detalles += `<p><strong>Año:</strong> ${recurso.anio}</p>`;
        } else if ('fechaPublicacion' in recurso) {
            detalles += `<p><strong>Tipo:</strong> Periódico</p>`;
            detalles += `<p><strong>Fecha Publicación:</strong> ${recurso.fechaPublicacion}</p>`;
            detalles += `<p><strong>Editorial:</strong> ${recurso.editorial}</p>`;
        } else if ('marca' in recurso) {
            detalles += `<p><strong>Tipo:</strong> Computador</p>`;
            detalles += `<p><strong>Marca:</strong> ${recurso.marca}</p>`;
            detalles += `<p><strong>Modelo:</strong> ${recurso.modelo}</p>`;
            detalles += `<p><strong>Sistema Operativo:</strong> ${recurso.sistemaOperativo}</p>`;
            detalles += `<p><strong>Tipo Computador:</strong> ${recurso.tipo}</p>`;
        }
        
        detalles += `<button onclick="eliminarRecurso(${recurso.id})">Eliminar</button>`;
        div.innerHTML = detalles;
        contenedor.appendChild(div);
    });
}

async function agregarLibro() {
    const libro = {
        nombre: document.getElementById('nombreLibro').value.trim(),
        autor: document.getElementById('autor').value.trim(),
        editorial: document.getElementById('editorial').value.trim(),
        anio: parseInt(document.getElementById('anio').value),
        activo: true
    };
    
    if (!libro.nombre || !libro.autor || !libro.editorial || isNaN(libro.anio)) {
        return mostrarError('Por favor complete todos los campos del libro');
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/libros`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(libro)
        });
        
        if (!response.ok) throw new Error('Error al agregar libro');
        
        alert('Libro agregado con éxito');
        document.getElementById('formLibro').reset();
        cargarRecursos();
    } catch (error) {
        console.log(error.mensaje);
    }
}

async function agregarPeriodico() {
    const periodico = {
        nombre: document.getElementById('nombrePeriodico').value.trim(),
        fechaPublicacion: document.getElementById('fechaPublicacion').value,
        editorial: document.getElementById('editorialPeriodico').value.trim(),
        activo: true
    };
    
    if (!periodico.nombre || !periodico.fechaPublicacion || !periodico.editorial) {
        return mostrarError('Por favor complete todos los campos del periódico');
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/periodicos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(periodico)
        });
        
        if (!response.ok) throw new Error('Error al agregar periódico');
        
        alert('Periódico agregado con éxito');
        document.getElementById('formPeriodico').reset();
        cargarRecursos();
    } catch (error) {
        console.log(error.mensaje);
    }
}

async function agregarComputador() {
    const computador = {
        nombre: document.getElementById('nombreComputador').value.trim(),
        marca: document.getElementById('marca').value.trim(),
        modelo: document.getElementById('modelo').value.trim(),
        sistemaOperativo: document.getElementById('sistemaOperativo').value.trim(),
        tipo: document.getElementById('tipoComputador').value,
        activo: true
    };
    
    if (!computador.nombre || !computador.marca || !computador.modelo || !computador.sistemaOperativo) {
        return mostrarError('Por favor complete todos los campos del computador');
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/computadores`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(computador)
        });
        
        if (!response.ok) throw new Error('Error al agregar computador');
        
        alert('Computador agregado con éxito');
        document.getElementById('formComputador').reset();
        cargarRecursos();
    } catch (error) {
        console.log(error.mensaje);
    }
}

async function eliminarRecurso(id) {
    if (!confirm('¿Está seguro de eliminar este recurso?')) return;
    
    try {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) throw new Error('Error al eliminar recurso');
        
        alert('Recurso eliminado con éxito');
        cargarRecursos();
    } catch (error) {
        mostrarError(error.message);
    }
}