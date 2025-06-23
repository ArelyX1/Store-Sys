function mostrarGrafo() {
  fetch('http://localhost:8080/api/grafo/json')
    .then(response => response.json())
    .then(data => {
      const contenedor = document.getElementById('grafo');
      contenedor.innerHTML = ''; // Limpia el contenido anterior
      data.forEach(v => {
        const vertice = document.createElement('div');
        vertice.innerHTML = `<strong>Vértice: ${v.vertex}</strong>`;
        if (v.edges.length > 0) {
          const lista = document.createElement('ul');
          v.edges.forEach(e => {
            const item = document.createElement('li');
            item.textContent = `→ ${e.destination} (peso: ${e.weight})`;
            lista.appendChild(item);
          });
          vertice.appendChild(lista);
        } else {
          vertice.innerHTML += `<div style="color:gray">Sin aristas</div>`;
        }
        contenedor.appendChild(vertice);
      });
    })
    .catch(error => {
      document.getElementById('grafo').innerHTML = 'Error al obtener el grafo.';
      console.error('Error:', error);
    });
}
