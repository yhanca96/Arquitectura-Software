class FestivoDto {
  constructor(festivo, fecha) {
    this.festivo = festivo; //nombre del festivo
    this.fecha = fecha; //fecha calculada, formato AAAA-MM-DD
  }
}

module.exports = FestivoDto;
