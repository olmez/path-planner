const express = require('express');
const app = express();
const port = 3000;

console.log('Running the server');

app.use(express.static('public'));
app.use(express.json());


app.listen(
    port, () => console.log(`App is running at http://localhost:${port}`));
