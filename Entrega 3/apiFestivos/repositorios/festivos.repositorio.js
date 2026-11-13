//Cargar la libreria para operar con bases de datos mongo
const mongo = require('mongodb').MongoClient;

//Cargar la configuracion de la BD
const configBD = require('../configuracion/bd.config');

//Asignar cadena de conexion
const credenciales = configBD.USUARIO
    ? `${encodeURIComponent(configBD.USUARIO)}:${encodeURIComponent(configBD.CLAVE)}@`
    : '';
const url = `mongodb://${credenciales}${configBD.SERVIDOR}:${configBD.PUERTO}`;

//objeto que contiene la conexion a la bd
let basedatos;

//conectar al servidor y obtener el objeto de acceso a la bd
exports.conectar = async () => {
    const cliente = await mongo.connect(url);
    basedatos = cliente.db(configBD.BASEDATOS);
    console.log('Se ha establecido conexion al servidor de MONGO');
}

//obtener los tipos de festivo, cada uno con su vector de festivos
exports.listarTipos = (resultado) => {
    basedatos.collection('tipos')
        .find({})
        .project({ _id: 0 })
        .sort({ id: 1 })
        .toArray()
        .then((tipos) => resultado(null, tipos))
        .catch((err) => {
            console.log('Error consultando los tipos de festivo', err);
            resultado(err, null);
        });
}
