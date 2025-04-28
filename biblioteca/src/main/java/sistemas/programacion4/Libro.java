package sistemas.programacion4;
import java.time.LocalDateTime;
import java.time.Year;

class Libro extends Recurso {
    private String autor;
    private String editorial;
    private Year anio;

    public Libro(String nombre, LocalDateTime fechaIngreso, boolean activo, String autor, String editorial, Year anio) {
        super(nombre, fechaIngreso, activo);
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public Year getAnio() {
        return anio;
    }

    @Override
    public boolean coincideConCriterio(String criterio) {
        return super.coincideConCriterio(criterio) || autor.contains(criterio) || editorial.contains(criterio);
    }

    @Override
    public String toString() {
        return super.toString() +
               "Autor=" + autor + "\n" +
               "Editorial=" + editorial + "\n" +
               "Año=" + anio + "\n";
    }
}

