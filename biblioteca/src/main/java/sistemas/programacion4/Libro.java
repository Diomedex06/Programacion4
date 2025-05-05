package sistemas.programacion4;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "LIBRO")
public class Libro implements Recurso {
    @Id
    private Integer id;
    private String nombre;
    @Column("FECHA_INGRESO")
    private String fechaIngreso;
    private boolean activo;
    private String autor;
    private String editorial;
    private int anio;

    public Libro() {}

    public Libro(Integer id, String nombre, String fechaIngreso, boolean activo, String autor, String editorial, int anio) {
        this.id = id;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.autor = autor;
        this.editorial = editorial;
        this.anio = anio;
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

    public String getAutor() { 
        return autor; 
    }
    public String getEditorial() { 
        return editorial; 
    }
    public int getAnio() { 
        return anio; 
    }

    @Override
    public String toString() {
        return "LIBRO\n" +
               "Id=" + id + "\n" +
               "Nombre=" + nombre + "\n" +
               "FechaIngreso=" + fechaIngreso + "\n" +
               "Activo=" + activo + "\n" +
               "Autor=" + autor + "\n" +
               "Editorial=" + editorial + "\n" +
               "Año=" + anio + "\n";
    }
}