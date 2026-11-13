module.exports = (app) => {
  const controlFestivo = require("../controladores/festivo.controlador");

  //metodo de la API que obtiene los festivos de un anio
  app.get("/festivos/obtener/:anio", controlFestivo.obtener);

  //metodo de la API que verifica si una fecha es festivo
  app.get("/festivos/verificar/:anio/:mes/:dia", controlFestivo.verificar);
};
