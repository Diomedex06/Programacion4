package sistemas.programacion4;
import java.time.LocalDateTime;
import java.time.LocalDate;


public class Periodico extends Recurso {
    private LocalDate fechaPublicacion;
    private String editorial;

    public Periodico(String nombre, LocalDateTime fechaIngreso, boolean activo, LocalDate fechaPublicacion, String editorial) {
        super(nombre, fechaIngreso, activo);
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    @Override
    public boolean coincideConCriterio(String criterio) {
        return super.coincideConCriterio(criterio) || editorial.contains(criterio);
    }

    @Override
    public String toString() {
        return super.toString() +
               "FechaPublicacion=" + fechaPublicacion + "\n" +
               "Editorial=" + editorial + "\n";
    }
}
