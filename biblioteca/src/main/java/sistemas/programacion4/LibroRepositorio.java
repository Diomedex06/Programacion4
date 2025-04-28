package sistemas.programacion4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class LibroRepositorio implements RecursoRepositorios <Libro> {

    private Collection<Libro> libros;

	public LibroRepositorio() {
        this.libros = new ArrayList<>();
    }

	@Override
	public void agregar(Libro recurso) {
        libros.add(recurso);
	}

	@Override
	public void quitarRecurso(Libro recurso) {
		libros.remove(recurso);
	}

	@Override
	public Collection<Libro> buscarRecursos(String criterio) {
		return libros.stream()
                .filter(r -> r.coincideConCriterio(criterio))
                .collect(Collectors.toList());
	}

	@Override
	public Collection<Libro> obtenerTodos() {
		return new ArrayList<>(libros);
	}
    
}