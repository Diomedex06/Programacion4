package sistemas.programacion4;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PeriodicoRepositorio extends CrudRepository<Periodico, Integer> {

    @Query("""
        SELECT * FROM periodico 
        WHERE 
            nombre LIKE CONCAT('%', :criterio, '%') OR
            editorial LIKE CONCAT('%', :criterio, '%') OR
            FECHA_INGRESO LIKE CONCAT('%', :criterio, '%') OR
            CAST(FECHA_PUBLICACION AS VARCHAR) LIKE CONCAT('%', :criterio, '%') OR
            CAST(activo AS VARCHAR) LIKE CONCAT('%', :criterio, '%')
        """)
    Collection<Periodico> findByCriteria(@Param("criterio") String criterio);
}