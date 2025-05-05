package sistemas.programacion4;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "COMPUTADOR")
public class Computador implements Recurso {
    @Id
    private Integer id;
    private String nombre;
    @Column ("FECHA_INGRESO")
    private String fechaIngreso;
    private boolean activo;
    
    private String marca;
    private String modelo;
    @Column ("SISTEMA_OPERATIVO")
    private String sistemaOperativo;
    private TipoComputador tipo;

    public Computador() {}

    public Computador(Integer id, String nombre, String fechaIngreso, boolean activo, String marca, String modelo, String sistemaOperativo, TipoComputador tipo) {
        this.id = id;
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.activo = activo;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.tipo = tipo;
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

    public String getMarca() { 
        return marca; 
    }
    public String getModelo() { 
        return modelo; 
    }
    public String getSistemaOperativo() { 
        return sistemaOperativo; 
    }
    public TipoComputador getTipo() { 
        return tipo; 
    }

    @Override
    public String toString() {
        return "COMPUTADOR\n" +
               "Id=" + id + "\n" +
               "Nombre=" + nombre + "\n" +
               "FechaIngreso=" + fechaIngreso + "\n" +
               "Activo=" + activo + "\n" +
               "Marca=" + marca + "\n" +
               "Modelo=" + modelo + "\n" +
               "SistemaOperativo=" + sistemaOperativo + "\n" +
               "Tipo=" + tipo + "\n";
    }
}