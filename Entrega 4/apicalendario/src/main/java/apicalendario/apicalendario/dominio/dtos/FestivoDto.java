package apicalendario.apicalendario.dominio.dtos;

// Estructura de cada festivo que responde la API Festivos: { "festivo": "...", "fecha": "AAAA-MM-DD" }
public class FestivoDto {

    private String festivo;
    private String fecha;

    public FestivoDto() {
    }

    public String getFestivo() {
        return festivo;
    }

    public void setFestivo(String festivo) {
        this.festivo = festivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
