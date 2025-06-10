package sistemas.programacion4;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComputadorRepositorio extends CrudRepository<Computador, Integer> {

    @Query("""
        SELECT * FROM computador 
        WHERE 
            nombre LIKE CONCAT('%', :criterio, '%') OR
            marca LIKE CONCAT('%', :criterio, '%') OR
            modelo LIKE CONCAT('%', :criterio, '%') OR
            SISTEMA_OPERATIVO LIKE CONCAT('%', :criterio, '%') OR
            CAST(tipo AS VARCHAR) LIKE CONCAT('%', :criterio, '%') OR
            CAST(activo AS VARCHAR) LIKE CONCAT('%', :criterio, '%') OR
            FECHA_INGRESO LIKE CONCAT('%', :criterio, '%')
        """)
    Collection<Computador> findByCriteria(@Param("criterio") String criterio);
}