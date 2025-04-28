package sistemas.programacion4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NombreComponente {
    @Value("${app.name}")
    private String nombreAplicacion;

    @PostConstruct
    public void imprimirNombreAplicacion() {
        System.out.println("Bienvenido a " + nombreAplicacion + "\n" );
    }
}
