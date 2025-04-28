package sistemas.programacion4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({
    "sistemas.programacion4"
})
@PropertySource("classpath:application.properties") // Cargar el archivo de propiedades

public class ConfiguracionAplicacion {
    
}