package sistemas.programacion4;
import java.time.LocalDateTime;

public interface Recurso {
    
    Integer getId();
    String getNombre();
    String getFechaIngreso();
    boolean isActivo();
    void darDeBaja();
    String toString();
}