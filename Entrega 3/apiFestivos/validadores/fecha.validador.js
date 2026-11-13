//Middleware que valida los parametros de fecha antes de llegar al controlador

const ANIO_MIN = 1900; //rango en el que es valido el calculo del domingo de Pascua
const ANIO_MAX = 2099;

//convierte un parametro de la ruta a entero (NaN si no son solo digitos)
const aEntero = (texto) => (/^\d+$/.test(texto) ? Number(texto) : NaN);

const esAnioValido = (anio) => Number.isInteger(anio) && anio >= ANIO_MIN && anio <= ANIO_MAX;

//valida /:anio/:mes/:dia
exports.validarFecha = (req, res, next) => {
    const anio = aEntero(req.params.anio);
    const mes = aEntero(req.params.mes);
    const dia = aEntero(req.params.dia);

    const fecha = new Date(anio, mes - 1, dia);
    const esValida = esAnioValido(anio)
        && fecha.getFullYear() === anio
        && fecha.getMonth() === mes - 1
        && fecha.getDate() === dia;

    if (!esValida) {
        return res.send('Fecha No válida');
    }
    req.fecha = { anio, mes, dia };
    next();
}

//valida /:anio
exports.validarAnio = (req, res, next) => {
    const anio = aEntero(req.params.anio);
    if (!esAnioValido(anio)) {
        return res.status(400).send({ mensaje: 'Año No válido' });
    }
    req.anio = anio;
    next();
}
