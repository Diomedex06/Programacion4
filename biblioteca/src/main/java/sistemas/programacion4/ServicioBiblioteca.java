package sistemas.programacion4;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

@Component
public class ServicioBiblioteca {

    private final LibroRepositorio libroRepositorio;
    private final PeriodicoRepositorio periodicoRepositorio;
    private final ComputadorRepositorio computadorRepositorio;

    @Autowired
    public ServicioBiblioteca(LibroRepositorio libroRepositorio, PeriodicoRepositorio periodicoRepositorio, ComputadorRepositorio computadorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.periodicoRepositorio = periodicoRepositorio;
        this.computadorRepositorio = computadorRepositorio;
    }
    
    public void agregarLibro(Libro libro) {
        libroRepositorio.save(libro);
    }

    public void agregarPeriodico(Periodico periodico) {
        periodicoRepositorio.save(periodico);
    }

    public void agregarComputador(Computador computador) {
        computadorRepositorio.save(computador);
    }

    public void quitarLibro(Libro libro) {
        libroRepositorio.delete(libro);
    }

    public void quitarPeriodico(Periodico periodico) {
        periodicoRepositorio.delete(periodico);
    }

    public void quitarComputador(Computador computador) {
        computadorRepositorio.delete(computador);
    }

    public Collection<Libro> buscarLibros(String criterio) {
        return libroRepositorio.findByCriteria(criterio);
    }

    public Collection<Periodico> buscarPeriodicos(String criterio) {
        return periodicoRepositorio.findByCriteria(criterio);
    }

    public Collection<Computador> buscarComputadores(String criterio) {
        return computadorRepositorio.findByCriteria(criterio);
    }

    public Collection<Recurso> obtenerTodos() {

        List<Recurso> todosLosRecursos = new ArrayList<>();
        
        libroRepositorio.findAll().forEach(todosLosRecursos::add);
        periodicoRepositorio.findAll().forEach(todosLosRecursos::add);
        computadorRepositorio.findAll().forEach(todosLosRecursos::add);
        
        return todosLosRecursos;
    }
}