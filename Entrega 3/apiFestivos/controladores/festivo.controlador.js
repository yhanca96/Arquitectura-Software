const tipo = require("../modelos/tipo.modelo");
const servicio = require("../servicios/festivos.servicio");

const aEntero = (texto) => (/^\d+$/.test(texto) ? Number(texto) : NaN);

exports.obtener = (req, res) => {
  const anio = aEntero(req.params.anio);
  if (!servicio.esAnioValido(anio)) {
    return res
      .status(400)
      .send({ mensaje: "El año debe ser un número entre 1900 y 2099" });
  }
  tipo.listar((err, tipos) => {
    if (err) {
      return res.status(500).send({ mensaje: "Error obteniendo los festivos" });
    }
    try {
      return res.send(servicio.obtenerFestivos(anio, tipos));
    } catch (error) {
      console.log("Error calculando festivos", error);
      return res.status(500).send({ mensaje: "Error calculando los festivos" });
    }
  });
};

exports.verificar = (req, res) => {
  const anio = aEntero(req.params.anio);
  const mes = aEntero(req.params.mes);
  const dia = aEntero(req.params.dia);
  if (!servicio.esFechaValida(anio, mes, dia)) {
    return res.send("Fecha No válida");
  }
  tipo.listar((err, tipos) => {
    if (err) {
      return res.status(500).send({ mensaje: "Error verificando la fecha" });
    }
    try {
      return res.send(
        servicio.esFestivo(anio, mes, dia, tipos)
          ? "Es festivo"
          : "No es festivo",
      );
    } catch (error) {
      console.log("Error verificando festivo", error);
      return res.status(500).send({ mensaje: "Error verificando la fecha" });
    }
  });
};
