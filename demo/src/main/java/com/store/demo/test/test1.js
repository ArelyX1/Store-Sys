function cargarProductos() {
    fetch('http://localhost:8080/api/productos')
        .then(response => response.json())
        .then(productos => {
            const lista = document.getElementById('lista-productos');
            lista.innerHTML = '';
            productos.forEach(nombre => {
                const li = document.createElement('li');
                li.textContent = nombre;
                lista.appendChild(li);
            });
        })
        .catch(err => console.error('Error al cargar productos:', err));
}
