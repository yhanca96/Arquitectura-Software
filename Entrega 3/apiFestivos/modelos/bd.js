const mongo = require("mongodb").MongoClient;

const configBD = require("../configuracion/bd.config");

const credenciales = configBD.USUARIO
  ? `${encodeURIComponent(configBD.USUARIO)}:${encodeURIComponent(configBD.CLAVE)}@`
  : "";
const url = `mongodb://${credenciales}${configBD.SERVIDOR}:${configBD.PUERTO}`;

let basedatos;

module.exports = {
  conectar: async function () {
    const cliente = await mongo.connect(url);
    basedatos = cliente.db(configBD.BASEDATOS);
    console.log("Se ha establecido conexion al servidor de MONGO");
  },

  obtenerBaseDatos: function () {
    return basedatos;
  },
};
