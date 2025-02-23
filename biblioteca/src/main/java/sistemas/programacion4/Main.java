package sistemas.programacion4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        ServicioBiblioteca biblioteca = new ServicioBiblioteca();

        // guardar libros
        biblioteca.agregar(new Libro("Cien años de soledad", LocalDateTime.now(), true, "Gabriel García Márquez", "Sudamericana", Year.of(1967)));
        biblioteca.agregar(new Libro("El principito", LocalDateTime.now(), true, "Antoine de Saint-Exupéry", "Reynal & Hitchcock", Year.of(1943)));
        biblioteca.agregar(new Libro("1984", LocalDateTime.now(), true, "George Orwell", "Secker & Warburg", Year.of(1949)));
        // guardar periódicos
        biblioteca.agregar(new Periodico("El Tiempo", LocalDateTime.now(), true, LocalDate.of(2024, 2, 23), "Prisa"));
        biblioteca.agregar(new Periodico("The New York Times", LocalDateTime.now(), true, LocalDate.of(2024, 2, 23), "The New York Times Company"));
        biblioteca.agregar(new Periodico("El Espectador", LocalDateTime.now(), true, LocalDate.of(2024, 2, 23), "Grupo Nación"));
        // guardar computadoras
        biblioteca.agregar(new Computador("Laptop Dell", LocalDateTime.now(), true, "Dell", "XPS 13", "Windows 11", TipoComputador.PORTATIL));
        biblioteca.agregar(new Computador("iMac", LocalDateTime.now(), true, "Apple", "iMac 24" , "macOS Ventura", TipoComputador.ESCRITORIO));
        biblioteca.agregar(new Computador("Samsung Galaxy Tab", LocalDateTime.now(), true, "Samsung", "Galaxy Tab S9", "Android", TipoComputador.TABLET));
        
        // Mostrar recursos
        System.out.println("Lista de recursos en la biblioteca:");
        biblioteca.obtenerTodos().forEach(System.out::println);

        // Buscar recursos
        String criterioBusqueda = "soledad";
        System.out.println("\nRecursos encontrados con el criterio: " + criterioBusqueda);
        biblioteca.buscarRecursos(criterioBusqueda).forEach(System.out::println);

        // Elimina solo el primer recurso encontrado
        biblioteca.buscarRecursos(criterioBusqueda).stream().findFirst().ifPresent(biblioteca::quitarRecurso);

        // Mostrar recursos despues de eliminar
        System.out.println("\nLista de recursos después de la eliminación:");
        biblioteca.obtenerTodos().forEach(System.out::println);
    }
}
