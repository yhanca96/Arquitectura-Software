module.exports = (app) => {
    //Importar el validador y el controlador
    const validador = require('../validadores/fecha.validador');
    const controlFestivos = require('../controladores/festivos.controlador');

    //metodo de la API que verifica si una fecha es festivo
    app.get('/api/festivos/verificar/:anio/:mes/:dia', validador.validarFecha, controlFestivos.verificar);

    //metodo de la API que obtiene los festivos de un año (lo consume la API Calendario)
    app.get('/api/festivos/obtener/:anio', validador.validarAnio, controlFestivos.obtener);
}
