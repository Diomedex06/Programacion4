package sistemas.programacion4;

public interface Recurso {
    
    Integer getId();
    String getNombre();
    String getFechaIngreso();
    boolean isActivo();
    void darDeBaja();
    String toString();
}