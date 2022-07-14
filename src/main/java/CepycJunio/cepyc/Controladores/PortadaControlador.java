package CepycJunio.cepyc.Controladores;

import CepycJunio.cepyc.Entidades.ContenidoInicio;
import CepycJunio.cepyc.Entidades.Foto;
import CepycJunio.cepyc.Entidades.ImageUtil;
import CepycJunio.cepyc.Entidades.Mensaje;
import CepycJunio.cepyc.Entidades.UnidadProductiva;
import CepycJunio.cepyc.Repositorios.ContenidoInicioRepositorio;
import CepycJunio.cepyc.Repositorios.MensajeRepositorio;
import CepycJunio.cepyc.Servicios.ContenidoInicioServicio;
import CepycJunio.cepyc.Servicios.FotoServicio;
import CepycJunio.cepyc.Servicios.MailingServicio;
import CepycJunio.cepyc.Servicios.UnidadProductivaServicio;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Controller("/")
public class PortadaControlador {

    @Autowired
    ContenidoInicioRepositorio contenidoInicioRepositorio;

    @Autowired
    MailingServicio mailingServicio;

    @Autowired
    MensajeRepositorio mensajeRepositorio;

    @Autowired
    UnidadProductivaServicio unidadProductivaServicio;

    ContenidoInicioServicio contenidoInicioServicio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/editable-portada")
    public String portadaEditable() {
        return "tabla-mensajes.html";
    }

    @GetMapping("/editable-portada/editar")
    public String editable(ModelMap modelo, Model model, ModelMap modelo2) {

        ContenidoInicio contenidoInicio = new ContenidoInicio();
        List<Foto> fotos = contenidoInicio.getFotos();
        ImageUtil imageUtil = new ImageUtil();

        for (Iterator<Foto> iterator = fotos.iterator(); iterator.hasNext();) {
            Foto next = iterator.next();
            imageUtil.getImgData(next.getContenido());

        }

        model.addAttribute("imgUtil", imageUtil);
        modelo.addAttribute("fotos", fotos);
        modelo2.addAttribute("contenido", contenidoInicio);

        return "tabla-mensajes.html";
    }

    //Post de la creación de contenido
    @PostMapping("/editable-portada-post")
    public String guardar(@RequestParam(name = "archivo1", required = false) MultipartFile archivo1,
            @RequestParam(name = "archivo2", required = false) MultipartFile archivo2,
            @RequestParam(name = "archivo3", required = false) MultipartFile archivo3,
            Long idContenido) throws IOException {

     
        contenidoInicioServicio.setearContenido(archivo1, archivo2, archivo3);

        return "redirect:/editable-portada/editar";
    }

    @GetMapping("/editable-portada/editar/{idContenido}/{idFoto}")
    public String borrar(@PathVariable Long idFoto, @PathVariable Long idContenido) {

        FotoServicio fotoServicio = new FotoServicio();
        fotoServicio.borrar(idFoto, idContenido);
        return "redirect:/editable-portada";
    }
    
    
   
    
    

    /*//Post de la creación de usuario
    @PostMapping("/")
    public String Portada(@RequestParam String nombre, @RequestParam String cuerpo, @RequestParam String mail) {
        usuarioServicio.crearUsuario(nombre, mail);

        Mensaje mensaje = new Mensaje();

        mensaje = mailingServicio.crearMensajeYusuario(cuerpo, mail);
        Long idMensaje = mensaje.getId();

        mailingServicio.enviar("Hemos recibido tu mensaje, ¡gracias!.", mensajeRepositorio.getById(idMensaje).getMail());

        return "index.html";
    }*/
}
