package rutasturisticas.api.presentacion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rutasturisticas.api.aplicacion.servicios.CiudadServicio;
import rutasturisticas.api.dominio.entidades.Ciudad;

@RestController
@RequestMapping("/api/ciudades")
@CrossOrigin(origins = "*")
public class CiudadControlador {

    @Autowired
    private CiudadServicio servicio;

    @GetMapping(value = "/")
    public List<Ciudad> listar() {
        return servicio.listar();
    }

    @GetMapping(value = "/{id}")
    public Ciudad get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping(value = "/")
    public Ciudad agregar(@RequestBody Ciudad ciudad) {
        return servicio.agregar(ciudad);
    }

    @PutMapping(value = "/")
    public Ciudad modificar(@RequestBody Ciudad ciudad) {
        return servicio.modificar(ciudad);
    }

    @DeleteMapping(value = "/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }

}
