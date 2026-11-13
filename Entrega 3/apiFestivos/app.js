//cargar libreria ExpressJS
const express = require('express');
const app = express();

//realizar la conexion a la BD
const bd = require('./modelos/bd');

//Cargar libreria para 'parseo' de contenido JSON
app.use(express.json());

//Dejar disponibles las rutas a los metodos web
require('./rutas/festivo.rutas')(app);

const puerto = 3030;

bd.conectar()
    .then(() => {
        app.listen(puerto, () => {
            console.log(`Servicio iniciado a través de la url http://localhost:${puerto}`);
        });
    })
    .catch((err) => {
        console.log('No se pudo conectar a MONGO', err);
        process.exit(1);
    });
