package rutasturisticas.api.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rutasturisticas.api.aplicacion.servicios.TipoServicio;
import rutasturisticas.api.dominio.entidades.Tipo;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@CrossOrigin(origins = "*")
public class TipoControlador {

    @Autowired
    private TipoServicio servicio;

    @GetMapping("/")
    public List<Tipo> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Tipo get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping("/")
    public Tipo agregar(@RequestBody Tipo tipo) {
        return servicio.agregar(tipo);
    }

    @PutMapping("/")
    public Tipo modificar(@RequestBody Tipo tipo) {
        return servicio.modificar(tipo);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}