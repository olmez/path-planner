const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');
const findPathButton = document.getElementById('findPathButton');

let start = null;
let end = null;
let blackCells = [];
let isDrawing = false;

const grid = [];
const cellSize = 20;

for (let x = 0; x < canvas.width; x += cellSize) {
  for (let y = 0; y < canvas.height; y += cellSize) {
    grid.push({x, y});
    console.log('Grid:', x);
  }
}

grid.forEach(cell => {
  ctx.strokeRect(cell.x, cell.y, cellSize, cellSize);
});



canvas.addEventListener('mousedown', event => {
  const rect = canvas.getBoundingClientRect();
  const x = event.clientX - rect.left;
  const y = event.clientY - rect.top;

  const cell = {
    x: Math.floor(x / cellSize) * cellSize,
    y: Math.floor(y / cellSize) * cellSize
  };

  if (!start) {
    start = cell;
    ctx.fillStyle = 'green';
    ctx.fillRect(cell.x, cell.y, cellSize, cellSize);
  } else if (!end) {
    end = cell;
    ctx.fillStyle = 'red';
    ctx.fillRect(cell.x, cell.y, cellSize, cellSize);
  } else {
    isDrawing = true;
    ctx.fillStyle = 'black';
    ctx.fillRect(cell.x, cell.y, cellSize, cellSize);
    blackCells.push({x: cell.x, y: cell.y});
  }
});

canvas.addEventListener('mousemove', event => {
  if (!isDrawing) return;

  const rect = canvas.getBoundingClientRect();
  const x = event.clientX - rect.left;
  const y = event.clientY - rect.top;

  const cell = {
    x: Math.floor(x / cellSize) * cellSize,
    y: Math.floor(y / cellSize) * cellSize
  };

  if (!blackCells.some(c => c.x === cell.x && c.y === cell.y)) {
    ctx.fillStyle = 'black';
    ctx.fillRect(cell.x, cell.y, cellSize, cellSize);
    blackCells.push({x: cell.x, y: cell.y});  // Store the cell in blackCells
  }
});

canvas.addEventListener('mouseup', () => {
  isDrawing = false;
});


findPathButton.addEventListener('click', () => {
  console.log('Button clicked');
  console.log('Start:', start);
  console.log('End:', end);
  console.log('Black Cells:', blackCells);
  console.log('Canvas width:', canvas.width);
  console.log('Canvas height:', canvas.height);
  console.log('Cell size:', cellSize);

  const serializedData = JSON.stringify(serializePathRequest({
    gridSizeX: canvas.width - cellSize,
    gridSizeY: canvas.height - cellSize,
    cellSize: cellSize,
    start: start,
    end: end,
    blackCells: blackCells
  }));

  console.log('Making fetch request...');
  fetch('http://localhost:8080/api/path-planning-endpoint', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: serializedData,
  })
      .then(response => response.json())
      .then(data => {
        console.log('Response from server:', data);
        drawPlannedPath(data.plannedPath);
        reset();
      })
      .catch(error => {
        console.error('Error:', error);
        reset();
      });
});

function reset() {
  start = null;
  end = null;
  blackCells = [];
  isDrawing = false;
}

function serializeLocation(location) {
  return {x: location.x, y: location.y};
}

function serializePathRequest(pathRequest) {
  return {
    gridSizeX: pathRequest.gridSizeX,
    gridSizeY: pathRequest.gridSizeY,
    cellSize: pathRequest.cellSize,
    start: serializeLocation(pathRequest.start),
    end: serializeLocation(pathRequest.end),
    blackCells: pathRequest.blackCells.map(serializeLocation)
  };
}

function drawPlannedPath(plannedPath) {
  plannedPath.forEach(cell => {
    if (cell.x === start.x && cell.y === start.y) return;
    if (cell.x === end.x && cell.y === end.y) return;
    ctx.fillStyle = 'rgba(255, 165, 0, 0.5)';
    ctx.fillRect(cell.x, cell.y, cellSize, cellSize);
  });
}