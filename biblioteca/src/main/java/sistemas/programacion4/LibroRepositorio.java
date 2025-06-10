package sistemas.programacion4;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LibroRepositorio extends CrudRepository<Libro, Integer> {

    @Query("""
    SELECT * FROM libro 
    WHERE 
        nombre LIKE CONCAT('%', :criterio, '%') OR
        autor LIKE CONCAT('%', :criterio, '%') OR
        editorial LIKE CONCAT('%', :criterio, '%') OR
        FECHA_INGRESO LIKE CONCAT('%', :criterio, '%') OR
        CAST(anio AS VARCHAR) LIKE CONCAT('%', :criterio, '%') OR
        CAST(activo AS VARCHAR) LIKE CONCAT('%', :criterio, '%')
    """)
    Collection<Libro> findByCriteria(@Param("criterio") String criterio);
}