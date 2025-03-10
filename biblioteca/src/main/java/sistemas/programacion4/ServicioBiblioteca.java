package sistemas.programacion4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ServicioBiblioteca {
    private Collection<Recurso> recursos;

    public ServicioBiblioteca() {
        this.recursos = new ArrayList<>();
    }

    public void agregar(Recurso recurso) {
        recursos.add(recurso);
    }

    public void quitarRecurso(Recurso recurso) {
        recursos.remove(recurso);
    }

    public Collection<Recurso> buscarRecursos(String criterio) {
        return recursos.stream()
                .filter(r -> r.coincideConCriterio(criterio))
                .collect(Collectors.toList());
    }

    public Collection<Recurso> obtenerTodos() {
        return new ArrayList<>(recursos);
    }
}
