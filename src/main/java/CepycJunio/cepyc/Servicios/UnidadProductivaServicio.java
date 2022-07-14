package CepycJunio.cepyc.Servicios;

import CepycJunio.cepyc.Entidades.UnidadProductiva;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import CepycJunio.cepyc.Repositorios.UnidadProductivaRepositorio;
import java.util.List;

@Service
public class UnidadProductivaServicio {

    @Autowired
    UnidadProductivaRepositorio unidadProductivaRepositorio;


    @Transactional
    public void crearUnidadProductiva(String nombre, String produccion, String departamento, String direccion) {

        UnidadProductiva unidadProductiva = new UnidadProductiva();
        unidadProductiva.setNombre(nombre);
        unidadProductiva.setProduccion(produccion);
        unidadProductiva.setDepartamento(departamento);
        unidadProductiva.setDireccion(direccion);

        unidadProductivaRepositorio.save(unidadProductiva);

    }

    public List<UnidadProductiva> buscarUnidades() {
        List<UnidadProductiva> unidades = unidadProductivaRepositorio.findAll();

        return unidades;
    }

    public void borrarUnidad(Long id) {

        unidadProductivaRepositorio.delete(unidadProductivaRepositorio.getById(id));
        
    }

}
