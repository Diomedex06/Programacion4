package sistemas.programacion4;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public void agregarRecurso(Recurso recurso) {
        if (recurso instanceof Libro) {
            libroRepositorio.save((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            periodicoRepositorio.save((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            computadorRepositorio.save((Computador) recurso);
        } else {
            System.out.println("Tipo de recurso no soportado");
        }
    }

    public void quitarRecurso(Recurso recurso) {
        if (recurso instanceof Libro) {
            libroRepositorio.delete((Libro) recurso);
        } else if (recurso instanceof Periodico) {
            periodicoRepositorio.delete((Periodico) recurso);
        } else if (recurso instanceof Computador) {
            computadorRepositorio.delete((Computador) recurso);
        } else {
            System.out.println("Tipo de recurso no soportado");
        }
    }

    public Collection<Recurso> buscaRecurso(String criterio) {
        List <Recurso> recursoEncontrado = new ArrayList<>();

        recursoEncontrado.addAll(libroRepositorio.findByCriteria(criterio));
        recursoEncontrado.addAll(periodicoRepositorio.findByCriteria(criterio));
        recursoEncontrado.addAll(computadorRepositorio.findByCriteria(criterio));

        return recursoEncontrado;
    }

    public Collection<Recurso> obtenerTodos() {

        List<Recurso> todosLosRecursos = new ArrayList<>();
        
        libroRepositorio.findAll().forEach(todosLosRecursos::add);
        periodicoRepositorio.findAll().forEach(todosLosRecursos::add);
        computadorRepositorio.findAll().forEach(todosLosRecursos::add);
        
        return todosLosRecursos;
    }

    public boolean quitarRecursoPorId(Integer id) {
    
        Optional<Recurso> recurso = obtenerRecursoPorId(id);
        
        if (recurso.isPresent()) {
            
            if (recurso.get() instanceof Libro) {
                libroRepositorio.deleteById(id);
            } else if (recurso.get() instanceof Periodico) {
                periodicoRepositorio.deleteById(id);
            } else if (recurso.get() instanceof Computador) {
                computadorRepositorio.deleteById(id);
            }
            return true;
        }
        return false;
    }

    public Optional<Recurso> obtenerRecursoPorId(Integer id) {
        
        Optional<Libro> libro = libroRepositorio.findById(id);
        if(libro.isPresent()) return libro.map(l -> (Recurso) l);
        
        Optional<Periodico> periodico = periodicoRepositorio.findById(id);
        if(periodico.isPresent()) return periodico.map(p -> (Recurso) p);
        
        Optional<Computador> computador = computadorRepositorio.findById(id);
        return computador.map(c -> (Recurso) c);
    }
    
}