

package CepycJunio.cepyc.Repositorios;

import CepycJunio.cepyc.Entidades.ContenidoInicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContenidoInicioRepositorio extends JpaRepository<ContenidoInicio, Long>{
    


}
