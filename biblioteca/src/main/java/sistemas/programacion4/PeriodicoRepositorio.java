package sistemas.programacion4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class PeriodicoRepositorio implements RecursoRepositorios <Periodico> {

    private Collection<Periodico> periodicos;

	public PeriodicoRepositorio() {
        this.periodicos = new ArrayList<>();
    }

	@Override
	public void agregar(Periodico recurso) {
        periodicos.add(recurso);
	}

	@Override
	public void quitarRecurso(Periodico recurso) {
		periodicos.remove(recurso);
	}

	@Override
	public Collection<Periodico> buscarRecursos(String criterio) {
		return periodicos.stream()
                .filter(r -> r.coincideConCriterio(criterio))
                .collect(Collectors.toList());
	}

	@Override
	public Collection<Periodico> obtenerTodos() {
		return new ArrayList<>(periodicos);
	}
    
}