//cargar el repositorio y el servicio de calculo
const repositorio = require('../repositorios/festivos.repositorio');
const servicioPascua = require('../servicios/pascua.service');

//metodo web para verificar si una fecha es festivo
exports.verificar = (req, res) => {
    const { anio, mes, dia } = req.fecha;
    repositorio.listarTipos((err, tipos) => {
        if (err) {
            return res.status(500).send({ mensaje: 'Error consultando los festivos' });
        }
        const esFestivo = servicioPascua.esFestivo(anio, mes, dia, tipos);
        return res.send(esFestivo ? 'Es Festivo' : 'No es festivo');
    });
}

//metodo web para obtener los festivos de un anio
exports.obtener = (req, res) => {
    repositorio.listarTipos((err, tipos) => {
        if (err) {
            return res.status(500).send({ mensaje: 'Error consultando los festivos' });
        }
        return res.send(servicioPascua.calcularFestivos(req.anio, tipos));
    });
}
