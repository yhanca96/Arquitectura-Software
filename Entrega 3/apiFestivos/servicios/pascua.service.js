//Servicio de calculo de festivos: Fijo / Puente festivo / Basado en Pascua

//Identificadores de los tipos (campo id de la coleccion tipos)
const TIPO = {
    FIJO: 1,          //no se puede variar
    LEY_PUENTE: 2,    //se traslada al siguiente lunes
    PASCUA: 3,        //dias respecto al domingo de Pascua
    PASCUA_LUNES: 4   //dias respecto al domingo de Pascua y se traslada al siguiente lunes
};

//----- Funciones de fechas entregadas por el profesor (ServicioFechas.js) -----
function obtenerDomingoRamos(anio) {
    const a = anio % 19;
    const b = anio % 4;
    const c = anio % 7;
    const d = (19 * a + 24) % 30;

    const dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

    let dia = 15 + dias;
    let mes = 3;
    if (dia > 31) {
        mes = 4;
        dia = dia - 31;
    }

    return new Date(anio, mes - 1, dia);
}

function agregarDias(fecha, dias) {
    const nuevaFecha = new Date(fecha);
    nuevaFecha.setDate(nuevaFecha.getDate() + dias);
    return nuevaFecha;
}

function siguienteLunes(fecha) {
    const nuevaFecha = new Date(fecha);
    const diaSemana = nuevaFecha.getDay();
    let diasParaLunes = 0;
    if (diaSemana != 1) {
        diasParaLunes = 1 + (7 - diaSemana) % 7;
    }
    return agregarDias(fecha, diasParaLunes);
}
//-------------------------------------------------------------------------------

//Domingo de Pascua = Domingo de Ramos + 7 dias
function obtenerDomingoPascua(anio) {
    return agregarDias(obtenerDomingoRamos(anio), 7);
}

//Formatea una fecha como AAAA-MM-DD
function formatear(fecha) {
    const mes = String(fecha.getMonth() + 1).padStart(2, '0');
    const dia = String(fecha.getDate()).padStart(2, '0');
    return `${fecha.getFullYear()}-${mes}-${dia}`;
}

//Calcula la fecha de un festivo en un anio segun su tipo
function calcularFecha(anio, idTipo, festivo, pascua) {
    switch (idTipo) {
        case TIPO.FIJO:
            return new Date(anio, festivo.mes - 1, festivo.dia);
        case TIPO.LEY_PUENTE:
            return siguienteLunes(new Date(anio, festivo.mes - 1, festivo.dia));
        case TIPO.PASCUA:
            return agregarDias(pascua, festivo.diasPascua);
        case TIPO.PASCUA_LUNES:
            return siguienteLunes(agregarDias(pascua, festivo.diasPascua));
    }
}

//Lista de festivos del anio { festivo, fecha } ordenada por fecha
function calcularFestivos(anio, tipos) {
    const pascua = obtenerDomingoPascua(anio);
    const festivos = [];
    for (const tipo of tipos) {
        for (const festivo of tipo.festivos) {
            const fecha = calcularFecha(anio, tipo.id, festivo, pascua);
            festivos.push({ festivo: festivo.nombre, fecha: formatear(fecha) });
        }
    }
    return festivos.sort((a, b) => a.fecha.localeCompare(b.fecha));
}

//Indica si una fecha es festivo
function esFestivo(anio, mes, dia, tipos) {
    const buscada = formatear(new Date(anio, mes - 1, dia));
    return calcularFestivos(anio, tipos).some((f) => f.fecha === buscada);
}

module.exports = {
    calcularFestivos,
    esFestivo
}
