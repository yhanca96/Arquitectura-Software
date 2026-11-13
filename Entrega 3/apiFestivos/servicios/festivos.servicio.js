const fechas = require("./fechas.servicio");
const FestivoDto = require("../dtos/festivo.dto");

const ANIO_MIN = 1900;
const ANIO_MAX = 2099;

//Identificadores de los tipos (coinciden con el campo id de la coleccion tipos)
const TIPO = {
  FIJO: 1, //no se puede variar
  LEY_PUENTE: 2, //se traslada al siguiente lunes
  PASCUA: 3, //se calcula con dias respecto al domingo de Pascua
  PASCUA_LUNES: 4, //dias respecto a Pascua y luego se traslada al siguiente lunes
};

function formatear(fecha) {
  const mm = String(fecha.getMonth() + 1).padStart(2, "0");
  const dd = String(fecha.getDate()).padStart(2, "0");
  return `${fecha.getFullYear()}-${mm}-${dd}`;
}

function obtenerDomingoPascua(anio) {
  return fechas.agregarDias(fechas.obtenerDomingoRamos(anio), 7);
}

function calcularFecha(anio, tipo, festivo, pascua) {
  switch (tipo.id) {
    case TIPO.FIJO:
      return new Date(anio, festivo.mes - 1, festivo.dia);
    case TIPO.LEY_PUENTE:
      return fechas.siguienteLunes(
        new Date(anio, festivo.mes - 1, festivo.dia),
      );
    case TIPO.PASCUA:
      return fechas.agregarDias(pascua, festivo.diasPascua);
    case TIPO.PASCUA_LUNES:
      return fechas.siguienteLunes(
        fechas.agregarDias(pascua, festivo.diasPascua),
      );
    default:
      throw new Error(`Tipo de festivo desconocido: ${tipo.id}`);
  }
}

function esAnioValido(anio) {
  return Number.isInteger(anio) && anio >= ANIO_MIN && anio <= ANIO_MAX;
}

function esFechaValida(anio, mes, dia) {
  if (!esAnioValido(anio) || !Number.isInteger(mes) || !Number.isInteger(dia)) {
    return false;
  }
  const fecha = new Date(anio, mes - 1, dia);
  return (
    fecha.getFullYear() === anio &&
    fecha.getMonth() === mes - 1 &&
    fecha.getDate() === dia
  );
}

function obtenerFestivos(anio, tipos) {
  const pascua = obtenerDomingoPascua(anio);
  const lista = [];
  for (const tipo of tipos) {
    for (const festivo of tipo.festivos) {
      const fecha = calcularFecha(anio, tipo, festivo, pascua);
      lista.push(new FestivoDto(festivo.nombre, formatear(fecha)));
    }
  }
  return lista.sort((a, b) => a.fecha.localeCompare(b.fecha));
}

function esFestivo(anio, mes, dia, tipos) {
  const buscada = formatear(new Date(anio, mes - 1, dia));
  return obtenerFestivos(anio, tipos).some((f) => f.fecha === buscada);
}

module.exports = {
  obtenerFestivos,
  esFestivo,
  esAnioValido,
  esFechaValida,
  obtenerDomingoPascua,
};
