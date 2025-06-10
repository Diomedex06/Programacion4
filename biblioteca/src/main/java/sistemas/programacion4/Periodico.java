package sistemas.programacion4;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table ("PERIODICO")
public class Periodico implements Recurso {
    @Id
    private Integer id;
    private String nombre;
    @Column("FECHA_INGRESO")
    private String fechaIngreso;
    private boolean activo;
    @Column("FECHA_PUBLICACION")
    private String fechaPublicacion;
    private String editorial;

    public Periodico() {}

    public Periodico(Integer id, String nombre, String fechaIngreso, boolean activo, String fechaPublicacion, String editorial) {
        this.id = id;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }

    @Override
    public Integer getId() { 
        return id; 
    }
    @Override
    public String getNombre() { 
        return nombre; 
    }
    @Override
    public String getFechaIngreso() { 
        return fechaIngreso; 
    }
    @Override
    public boolean isActivo() { 
        return activo; 
    }
    @Override
    public void darDeBaja() { 
        this.activo = false; 
    }

    public String getFechaPublicacion() { 
        return fechaPublicacion; 
    }
    public String getEditorial() { 
        return editorial; 
    }

    @Override
    public String toString() {
        return "PERIODICO\n" +
               "Id=" + id + "\n" +
               "Nombre=" + nombre + "\n" +
               "FechaIngreso=" + fechaIngreso + "\n" +
               "Activo=" + activo + "\n" +
               "FechaPublicacion=" + fechaPublicacion + "\n" +
               "Editorial=" + editorial + "\n";
    }
}