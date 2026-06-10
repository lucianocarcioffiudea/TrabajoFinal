package rutasturisticas.api.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rutasturisticas.api.aplicacion.servicios.ParadaServicio;
import rutasturisticas.api.dominio.entidades.Parada;

import java.util.List;

@RestController
@RequestMapping("/api/paradas")
@CrossOrigin(origins = "*")
public class ParadaControlador {

    @Autowired
    private ParadaServicio servicio;

    @GetMapping("/")
    public List<Parada> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Parada get(@PathVariable int id) {
        return servicio.get(id);
    }

    @GetMapping("/ruta/{idRuta}")
    public List<Parada> listarRuta(@PathVariable int idRuta) {
        return servicio.listarRuta(idRuta);
    }

    @PostMapping("/")
    public Parada agregar(@RequestBody Parada parada) {
        return servicio.agregar(parada);
    }

    @PutMapping("/")
    public Parada modificar(@RequestBody Parada parada) {
        return servicio.modificar(parada);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}
