package sistemas.programacion4;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

class ServicioBiblioteca {
    private Collection<Recurso> recursos;

    public ServicioBiblioteca() {
        this.recursos = new ArrayList<>();
    }

    public void agregarPeriodico(Periodico periodico) {
        periodicoRepositorio.agregar(periodico);
    }

    public void agregarComputador(Computador computador) {
        computadorRepositorio.agregar(computador);
    }

    public void quitarLibro(Libro libro) {
        libroRepositorio.quitarRecurso(libro);
    }

    public void quitarPeriodico(Periodico periodico) {
        periodicoRepositorio.quitarRecurso(periodico);
    }

    public void quitarComputador(Computador computador) {
        computadorRepositorio.quitarRecurso(computador);
    }

    public Collection<Libro> buscarLibros(String criterio) {
        return libroRepositorio.buscarRecursos(criterio);
    }

    public Collection<Periodico> buscarPeriodicos(String criterio) {
        return periodicoRepositorio.buscarRecursos(criterio);
    }

    public Collection<Computador> buscarComputadores(String criterio) {
        return computadorRepositorio.buscarRecursos(criterio);
    }

    public Collection<Recurso> obtenerTodos() {
        
        Collection<Libro> libros = libroRepositorio.obtenerTodos();
        Collection<Periodico> periodicos = periodicoRepositorio.obtenerTodos();
        Collection<Computador> computadores = computadorRepositorio.obtenerTodos();

        List<Recurso> todosLosRecursos = new ArrayList<>();
        todosLosRecursos.addAll(libros);
        todosLosRecursos.addAll(periodicos);
        todosLosRecursos.addAll(computadores);

        return todosLosRecursos;
    }
}