package sistemas.programacion4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ComputadorRepositorio implements RecursoRepositorios <Computador> {

    private Collection<Computador> computadores;

	public ComputadorRepositorio() {
        this.computadores = new ArrayList<>();
    }

	@Override
	public void agregar(Computador recurso) {
        computadores.add(recurso);
	}

	@Override
	public void quitarRecurso(Computador recurso) {
		computadores.remove(recurso);
	}

	@Override
	public Collection<Computador> buscarRecursos(String criterio) {
		return computadores.stream()
                .filter(r -> r.coincideConCriterio(criterio))
                .collect(Collectors.toList());
	}

	@Override
	public Collection<Computador> obtenerTodos() {
		return new ArrayList<>(computadores);
	}
    
}