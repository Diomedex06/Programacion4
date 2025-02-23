package sistemas.programacion4;
import java.time.LocalDateTime;

class Computador extends Recurso {
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private TipoComputador tipo;

    public Computador(String nombre, LocalDateTime fechaIngreso, boolean activo, String marca, String modelo, String sistemaOperativo, TipoComputador tipo) {
        super(nombre, fechaIngreso, activo);
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.tipo = tipo;
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
    public boolean coincideConCriterio(String criterio) {
        return super.coincideConCriterio(criterio) || marca.contains(criterio) || modelo.contains(criterio) || sistemaOperativo.contains(criterio) || tipo.name().contains(criterio);
    }

    @Override
    public String toString() {
        return super.toString() +
               "Marca=" + marca + "\n" +
               "Modelo=" + modelo + "\n" +
               "SistemaOperativo=" + sistemaOperativo + "\n" +
               "Tipo=" + tipo + "\n";
    }
}

enum TipoComputador {
    PORTATIL, ESCRITORIO, TABLET;
}
