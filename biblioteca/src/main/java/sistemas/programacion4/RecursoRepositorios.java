package sistemas.programacion4;

import java.util.Collection;

public interface RecursoRepositorios <T extends Recurso>{
    void agregar(T recurso);
    void quitarRecurso(T recurso);
    Collection<T> buscarRecursos(String criterio);
    Collection<T> obtenerTodos();
}