package sistemas.programacion4;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Collection;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    private final ServicioBiblioteca servicioBiblioteca;

    public RecursoController(ServicioBiblioteca servicioBiblioteca) {
        this.servicioBiblioteca = servicioBiblioteca;
    }

    @GetMapping
    public Collection<Recurso> obtenerTodos() {
        return servicioBiblioteca.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> obtenerPorId(@PathVariable Integer id) {
        Optional<Recurso> recurso = servicioBiblioteca.obtenerRecursoPorId(id);
        return recurso.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/libros")
    public ResponseEntity<Libro> agregarLibro(@RequestBody Libro libro) {
        servicioBiblioteca.agregarRecurso(libro);
        return ResponseEntity.ok(libro);
    }

    @PostMapping("/periodicos")
    public ResponseEntity<Periodico> agregarPeriodico(@RequestBody Periodico periodico) {
        servicioBiblioteca.agregarRecurso(periodico);
        return ResponseEntity.ok(periodico);
    }

    @PostMapping("/computadores")
    public ResponseEntity<Computador> agregarComputador(@RequestBody Computador computador) {
        servicioBiblioteca.agregarRecurso(computador);
        return ResponseEntity.ok(computador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRecurso(@PathVariable("id") Integer id) {
        try {
            boolean eliminado = servicioBiblioteca.quitarRecursoPorId(id);
            if (eliminado) {
                return ResponseEntity.ok(Map.of(
                    "message", "Recurso eliminado con éxito",
                    "id", id
                ));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al eliminar recurso: " + e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public Collection<Recurso> buscarPorCriterio(@RequestParam(name = "criterio") String criterio) {
        return servicioBiblioteca.buscaRecurso(criterio);
    }

}