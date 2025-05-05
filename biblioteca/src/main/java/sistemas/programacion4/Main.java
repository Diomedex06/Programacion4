package sistemas.programacion4;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        try (ConfigurableApplicationContext contexto = SpringApplication.run(Main.class, args)) {
            DataSource dataSource = contexto.getBean(DataSource.class);
            try (Connection connection = dataSource.getConnection()) {
                crearTablas(connection);
            }
    
            ServicioBiblioteca servicioBiblioteca = contexto.getBean(ServicioBiblioteca.class);
    
            // Agregar recursos
            servicioBiblioteca.agregarRecurso(new Libro(null, "Cien años de soledad", LocalDateTime.now().toString(), true, "Gabriel García Márquez", "Sudamericana", 1967));
            servicioBiblioteca.agregarRecurso(new Libro(null, "El principito", LocalDateTime.now().toString(), true, "Antoine de Saint-Exupéry", "Reynal & Hitchcock", 1943));
            servicioBiblioteca.agregarRecurso(new Libro(null, "1984", LocalDateTime.now().toString(), true, "George Orwell", "Secker & Warburg", 1949));

            servicioBiblioteca.agregarRecurso(new Periodico(null, "El Tiempo", LocalDateTime.now().toString(), true, LocalDate.of(2024, 2, 23).toString(), "Prisa"));
            servicioBiblioteca.agregarRecurso(new Periodico(null, "The New York Times", LocalDateTime.now().toString(), true, LocalDate.of(2024, 2, 23).toString(), "The New York Times Company"));
            servicioBiblioteca.agregarRecurso(new Periodico(null, "El Espectador", LocalDateTime.now().toString(), true, LocalDate.of(2024, 2, 23).toString(), "Grupo Nación"));

            servicioBiblioteca.agregarRecurso(new Computador(null, "Laptop Dell", LocalDateTime.now().toString(), true, "Dell", "XPS 13", "Windows 11", TipoComputador.PORTATIL));
            servicioBiblioteca.agregarRecurso(new Computador(null, "iMac", LocalDateTime.now().toString(), true, "Apple", "iMac 24", "macOS Ventura", TipoComputador.ESCRITORIO));
            servicioBiblioteca.agregarRecurso(new Computador(null, "Samsung Galaxy Tab", LocalDateTime.now().toString(), true, "Samsung", "Galaxy Tab S9", "Android", TipoComputador.TABLET));


            // Mostrar todos los recursos
            System.out.println("\n Lista de recursos \n");
            servicioBiblioteca.obtenerTodos().forEach(System.out::println);
    
            // Buscar por criterio
            String criterio = "el";
            System.out.println("\n Recursos encontrados con criterio '" + criterio + "' \n");
            List<Recurso> resultados = Stream.concat(
                servicioBiblioteca.buscaRecurso(criterio).stream(),
                Stream.concat(
                    servicioBiblioteca.buscaRecurso(criterio).stream(),
                    servicioBiblioteca.buscaRecurso(criterio).stream()
                )
            ).collect(Collectors.toList());
            
            resultados.forEach(System.out::println);
    
            // Eliminar solo el primer recurso encontrado
            if (!resultados.isEmpty()) {
                Recurso primerRecurso = resultados.get(0);
                if (primerRecurso instanceof Libro) {
                    servicioBiblioteca.quitarRecurso((Libro) primerRecurso);
                } else if (primerRecurso instanceof Periodico) {
                    servicioBiblioteca.quitarRecurso((Periodico) primerRecurso);
                } else if (primerRecurso instanceof Computador) {
                    servicioBiblioteca.quitarRecurso((Computador) primerRecurso);
                }
                System.out.println("\n Recurso eliminado: \n");
                System.out.println(primerRecurso);
            }else {
                System.out.println("No se encontraron recursos para eliminar.");
            }
    
            // Mostrar recursos después de la eliminación
            System.out.println("\nLista de recursos después de la eliminación \n");
            servicioBiblioteca.obtenerTodos().forEach(System.out::println);

            try (Connection connection = dataSource.getConnection()) {
                limpiarBaseDeDatos(connection);
            }
        }
    }

    private static void crearTablas(Connection conexion) throws SQLException {

        conexion.createStatement().execute("""
            CREATE TABLE IF NOT EXISTS COMPUTADOR(
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre TEXT,
                FECHA_INGRESO VARCHAR(30),
                activo BOOLEAN,
                marca TEXT,
                modelo TEXT,
                SISTEMA_OPERATIVO TEXT,
                tipo VARCHAR(20)
            );
            CREATE TABLE IF NOT EXISTS LIBRO(
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre TEXT,
                FECHA_INGRESO VARCHAR(30),
                activo BOOLEAN,
                autor TEXT,
                editorial TEXT,
                anio INT
            );
            CREATE TABLE IF NOT EXISTS PERIODICO(
                id INT AUTO_INCREMENT PRIMARY KEY,
                nombre TEXT,
                FECHA_INGRESO VARCHAR(30),
                activo BOOLEAN,
                FECHA_PUBLICACION DATE,
                editorial TEXT
            );
        """);
        conexion.commit();
    }

    private static void limpiarBaseDeDatos(Connection conexion) throws SQLException {
        System.out.println("\nLimpiando base de datos...");
        conexion.createStatement().execute("DROP ALL OBJECTS DELETE FILES");
        System.out.println("Base de datos limpiada exitosamente");
    }
}