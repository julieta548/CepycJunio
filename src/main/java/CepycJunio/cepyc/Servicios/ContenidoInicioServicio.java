package CepycJunio.cepyc.Servicios;

import CepycJunio.cepyc.Entidades.ContenidoInicio;
import CepycJunio.cepyc.Entidades.Foto;
import CepycJunio.cepyc.Repositorios.ContenidoInicioRepositorio;
import CepycJunio.cepyc.Repositorios.FotoRepositorio;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContenidoInicioServicio {

    @Autowired
    FotoRepositorio fotoRepositorio;

    @Autowired
    FotoServicio fotoServicio;

    @Autowired
    ContenidoInicioRepositorio contenidoInicioRepositorio;

    public Long crearContenido() {
        ContenidoInicio contenidoInicio = new ContenidoInicio();
        return contenidoInicio.getId();
    }

    public ContenidoInicio buscar(Long idContenido) {
        return contenidoInicioRepositorio.getById(idContenido);
    }

    @Transactional
    public Long setearContenido(MultipartFile archivo1, MultipartFile archivo2, MultipartFile archivo3) throws IOException {

        ContenidoInicio contenidoInicio = new ContenidoInicio();
                
        contenidoInicio.getFotos().add(fotoServicio.guardar(archivo1, contenidoInicio.getId()));

        contenidoInicio.getFotos().add(fotoServicio.guardar(archivo2, contenidoInicio.getId()));

        contenidoInicio.getFotos().add(fotoServicio.guardar(archivo3, contenidoInicio.getId()));

        contenidoInicioRepositorio.save(contenidoInicio);
        return contenidoInicio.getId();

    }

    public void removerFoto(Long idFoto, Long idContenido) {

        Foto foto = fotoRepositorio.getById(idFoto);
        contenidoInicioRepositorio.getById(idContenido).getFotos().remove(foto);
        contenidoInicioRepositorio.save(contenidoInicioRepositorio.getById(idContenido));
    }

}
