package sistemas.programacion4;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories("sistemas.programacion4") 
@ComponentScan({
    "sistemas.programacion4"
})
@PropertySource("classpath:application.properties")

public class ConfiguracionAplicacion {
    @Bean
    public DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();

        dataSource.setUrl("jdbc:h2:file:./db-biblioteca");

        return dataSource;
    }    
}