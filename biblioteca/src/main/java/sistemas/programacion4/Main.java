package sistemas.programacion4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
   
        var contexto = new AnnotationConfigApplicationContext(ConfiguracionAplicacion.class);

        ServicioBiblioteca servicioBiblioteca = contexto.getBean(ServicioBiblioteca.class);

        // Agregar recursos
        servicioBiblioteca.agregarLibro(new Libro("Cien años de soledad", LocalDateTime.now(), true, "Gabriel García Márquez", "Sudamericana", Year.of(1967)));
        servicioBiblioteca.agregarLibro(new Libro("El principito", LocalDateTime.now(), true, "Antoine de Saint-Exupéry", "Reynal & Hitchcock", Year.of(1943)));
        servicioBiblioteca.agregarLibro(new Libro("1984", LocalDateTime.now(), true, "George Orwell", "Secker & Warburg", Year.of(1949)));

        servicioBiblioteca.agregarPeriodico(new Periodico("El Tiempo", LocalDateTime.now(), true, LocalDate.of(2024, 2, 23), "Prisa"));
        servicioBiblioteca.agregarPeriodico(new Periodico("The New York Times", LocalDateTime.now(), true, LocalDate.of(2024, 2, 23), "The New York Times Company"));
        servicioBiblioteca.agregarPeriodico(new Periodico("El Espectador", LocalDateTime.now(), true, LocalDate.of(2024, 2, 23), "Grupo Nación"));

        servicioBiblioteca.agregarComputador(new Computador("Laptop Dell", LocalDateTime.now(), true, "Dell", "XPS 13", "Windows 11", TipoComputador.PORTATIL));
        servicioBiblioteca.agregarComputador(new Computador("iMac", LocalDateTime.now(), true, "Apple", "iMac 24", "macOS Ventura", TipoComputador.ESCRITORIO));
        servicioBiblioteca.agregarComputador(new Computador("Samsung Galaxy Tab", LocalDateTime.now(), true, "Samsung", "Galaxy Tab S9", "Android", TipoComputador.TABLET));

        // Mostrar todos los recursos
        System.out.println("Lista de recursos:");
        servicioBiblioteca.obtenerTodos().forEach(System.out::println);

        // Buscar recursos
        String criterioBusqueda = "El";
        System.out.println("\nRecursos encontrados con el criterio: " + criterioBusqueda);
        List<Recurso> resultados = buscarRecursos(criterioBusqueda, servicioBiblioteca);
        resultados.forEach(System.out::println);
        
        //Eliminar solo el primer recurso encontrado
        eliminarPrimerRecursoEncontrado(criterioBusqueda, servicioBiblioteca);

        // Mostrar recursos después de la eliminación
        System.out.println("\nLista de recursos después de la eliminación:");
        servicioBiblioteca.obtenerTodos().forEach(System.out::println);

        contexto.close();
    }

    // Buscar recursos en todos los repositorios
    private static List<Recurso> buscarRecursos(String criterio, ServicioBiblioteca servicioBiblioteca) {
        return Stream.concat(
                Stream.concat(
                        servicioBiblioteca.buscarLibros(criterio).stream(),
                        servicioBiblioteca.buscarPeriodicos(criterio).stream()),
                        servicioBiblioteca.buscarComputadores(criterio).stream()).collect(Collectors.toList());
    }

    // Método para eliminar el primer recurso encontrado
    private static void eliminarPrimerRecursoEncontrado(String criterio, ServicioBiblioteca servicioBiblioteca) {
        List<Recurso> resultados = buscarRecursos(criterio, servicioBiblioteca);
        if (!resultados.isEmpty()) {
            Recurso primerRecurso = resultados.get(0);
            if (primerRecurso instanceof Libro) {
                servicioBiblioteca.quitarLibro((Libro) primerRecurso);
            } else if (primerRecurso instanceof Periodico) {
                servicioBiblioteca.quitarPeriodico((Periodico) primerRecurso);
            } else if (primerRecurso instanceof Computador) {
                servicioBiblioteca.quitarComputador((Computador) primerRecurso);
            }
            System.out.println("Recurso eliminado: " + primerRecurso);
        } else {
            System.out.println("No se encontraron recursos para eliminar.");
        }
    }

}