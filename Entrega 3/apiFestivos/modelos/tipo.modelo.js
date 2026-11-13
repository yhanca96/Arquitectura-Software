const bd = require("./bd");
const Tipo = function () {};

Tipo.listar = function (resultado) {
  const basedatos = bd.obtenerBaseDatos();

  basedatos
    .collection("tipos")

    .find({})
    .project({ _id: 0 })
    .sort({ id: 1 })
    .toArray()

    .then((res) => resultado(null, res))
    .catch((err) => {
      console.log("Error listando tipos de festivo", err);
      resultado(err, null);
    });
};

module.exports = Tipo;
