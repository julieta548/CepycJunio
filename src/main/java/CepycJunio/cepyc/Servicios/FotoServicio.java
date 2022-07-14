package CepycJunio.cepyc.Servicios;

import CepycJunio.cepyc.Entidades.Foto;
import CepycJunio.cepyc.Repositorios.ContenidoInicioRepositorio;
import CepycJunio.cepyc.Repositorios.FotoRepositorio;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoServicio {

    @Autowired
    FotoRepositorio fotoRepositorio;

    @Autowired
    ContenidoInicioRepositorio contenidoInicioRepositorio;

    @Transactional
    public Foto guardar(MultipartFile archivo, Long idContenido) throws IOException {

        if (archivo != null) {
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());

            List<Foto> fotos = contenidoInicioRepositorio.getById(idContenido).getFotos();
            if (fotos.contains(foto)) {
                for (Iterator<Foto> iterator = fotos.iterator(); iterator.hasNext();) {
                    Foto next = iterator.next();
                    if (next.equals(foto)) {
                        this.borrar(next.getId(), idContenido);
                    } else {
                        fotoRepositorio.save(foto);
                    }
                }
            }
            return foto;
        } else {
            return null;
        }
    }

    @Transactional
    public void borrar(Long idFoto, Long idContenido) {

        ContenidoInicioServicio contenidoInicioServicio = new ContenidoInicioServicio();
        contenidoInicioServicio.removerFoto(idFoto, idContenido);
        fotoRepositorio.deleteById(idFoto);

    }

}
