

package CepycJunio.cepyc.Repositorios;

import CepycJunio.cepyc.Entidades.Mensaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MensajeRepositorio extends JpaRepository<Mensaje, Long>{

    @Query
    List<Mensaje> findByNombre(String nombre);
    
}
