package CepycJunio.cepyc.Servicios;

import CepycJunio.cepyc.Entidades.Mensaje;
import CepycJunio.cepyc.Repositorios.MensajeRepositorio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MailingServicio {

    @Autowired
    MensajeRepositorio mensajeRepositorio;

    @Transactional
    public void crearMensaje(String nombre, String email, String cuerpo) {
        Mensaje mensaje = new Mensaje();
        Long idMensaje = mensaje.getId();

        mensaje.setCuerpo(cuerpo);
        mensaje.setNombre(nombre);
        mensaje.setMail(email);

        mensajeRepositorio.save(mensaje);

    }

    @Transactional
    public void borrarMensaje(Long id) {
        mensajeRepositorio.delete(mensajeRepositorio.getById(id));
    }

    public List<Mensaje> buscarMensajePorNombre(String nombre) {
        List<Mensaje> mensajes = new ArrayList<>();
        mensajes = mensajeRepositorio.findByNombre(nombre);

        return mensajes;
    }

    public List<Mensaje> listarMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        mensajes = mensajeRepositorio.findAll();

        return mensajes;
    }

    public List<Mensaje> buscarMensajePorMail(String mail) {

        List<Mensaje> mensajesEncontrados = new ArrayList<>();

        for (Iterator<Mensaje> iterator = this.listarMensajes().iterator(); iterator.hasNext();) {
            Mensaje next = iterator.next();
            if (next.getMail().equals(mail)) {
                mensajesEncontrados.add(next);
            }
        }

        if (!mensajesEncontrados.isEmpty()) {
            return mensajesEncontrados;
        }

        return null;
    }

}
