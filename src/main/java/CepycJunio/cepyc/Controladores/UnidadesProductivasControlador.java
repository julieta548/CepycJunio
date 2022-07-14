

package CepycJunio.cepyc.Controladores;

import CepycJunio.cepyc.Entidades.UnidadProductiva;
import CepycJunio.cepyc.Servicios.UnidadProductivaServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/unidades-productivas")
public class UnidadesProductivasControlador {

    @Autowired
    UnidadProductivaServicio unidadProductivaServicio;
    
    
    
    @PostMapping("/unidades-borrar/{id}")
    public String unidadesBorrar(@PathVariable Long id){
        unidadProductivaServicio.borrarUnidad(id);
        
        return "redirect:/tabla-mensajes";
    }
    
    @PostMapping("/crear-unidad-productiva")
    public String unidadesBorrar(@RequestParam String nombre, @RequestParam String produccion, @RequestParam String departamento, @RequestParam String direccion){
        unidadProductivaServicio.crearUnidadProductiva(nombre, produccion, departamento, direccion);
        
        return "redirect:/tabla-mensajes";
    }
    
     @GetMapping("/unidades-productivas")
    public String unidadesProductivas(ModelMap modelo){
        
        List<UnidadProductiva> unidades = unidadProductivaServicio.buscarUnidades();
        
        modelo.addAttribute("unidades", unidades);
        
        return "redirect:/editable-portada";
    }
}
