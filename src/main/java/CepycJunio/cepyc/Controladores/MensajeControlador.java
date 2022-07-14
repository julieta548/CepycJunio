

package CepycJunio.cepyc.Controladores;

import CepycJunio.cepyc.Entidades.Mensaje;
import CepycJunio.cepyc.Repositorios.MensajeRepositorio;
import CepycJunio.cepyc.Servicios.MailingServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/editable-portada")
public class MensajeControlador {

    @Autowired
    MensajeRepositorio mensajeRepositorio;
    
    @Autowired
    MailingServicio mailingServicio;
    
    @GetMapping("/tabla-mensajes")
    public String verMensajes(ModelMap modelo){
        List<Mensaje> mensajes = mailingServicio.listarMensajes();
        
        modelo.addAttribute("mensajes", mensajes);
        
        return "tabla-mensajes.html";
    }
    
   
    @GetMapping("/tabla-mensajes/{id}")
    public String borrarMensaje(@PathVariable Long id){
        mailingServicio.borrarMensaje(id);
        
        return "redirect:/tabla-mensajes";
    }
    
    @PostMapping("/tabla-mensajes")
    public String crearMensaje(@RequestParam String nombre, @RequestParam String cuerpo, @RequestParam String mail){
        mailingServicio.crearMensaje(nombre, mail, cuerpo);
        
        return "redirect:/tabla-mensajes";
    }
    
}
