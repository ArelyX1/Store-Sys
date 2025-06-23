function mostrarGrafo() {
  fetch('http://localhost:8080/api/grafo/visual')
    .then(res => res.json())
    .then(data => {
      const container = document.getElementById('grafo');
      const options = {
        edges: {
          arrows: 'to',
          font: { align: 'middle' }
        },
        nodes: {
          shape: 'ellipse',
          font: { size: 18 }
        },
        physics: { enabled: true }
      };
      new vis.Network(container, data, options);
    })
    .catch(err => {
      document.getElementById('grafo').innerHTML = 'Error al cargar el grafo.';
      console.error(err);
    });
}

function agregarVertice() {
  const data = document.getElementById('inputVertice').value;
  fetch('http://localhost:8080/api/grafo/vertices', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ data })
  })
  .then(() => {
    mostrarGrafo();
    document.getElementById('inputVertice').value = '';
  });
}

function agregarArista() {
  const source = document.getElementById('inputOrigen').value;
  const destination = document.getElementById('inputDestino').value;
  const weight = parseInt(document.getElementById('inputPeso').value, 10);
  fetch('http://localhost:8080/api/grafo/aristas', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ source, destination, weight })
  })
  .then(() => {
    mostrarGrafo();
    document.getElementById('inputOrigen').value = '';
    document.getElementById('inputDestino').value = '';
    document.getElementById('inputPeso').value = '';
  });
}

function eliminarVertice() {
  const data = document.getElementById('inputEliminarVertice').value;
  if (!data) return;
  fetch(`http://localhost:8080/api/grafo/vertices/${encodeURIComponent(data)}`, {
    method: 'DELETE'
  })
  .then(() => {
    mostrarGrafo();
    document.getElementById('inputEliminarVertice').value = '';
  });
}

function eliminarArista() {
  const source = document.getElementById('inputEliminarOrigen').value;
  const destination = document.getElementById('inputEliminarDestino').value;
  fetch('http://localhost:8080/api/grafo/aristas', {
    method: 'DELETE',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ source, destination })
  })
  .then(() => {
    mostrarGrafo();
    document.getElementById('inputEliminarOrigen').value = '';
    document.getElementById('inputEliminarDestino').value = '';
  });
}

function getDijkstra() {
  const start = document.getElementById('inputAlgStart').value;
  fetch(`http://localhost:8080/api/grafo/dijkstra?start=${encodeURIComponent(start)}`)
    .then(res => res.json())
    .then(data => {
      document.getElementById('algResult').innerText = 'Dijkstra desde ' + start + ': ' + JSON.stringify(data);
    });
}
