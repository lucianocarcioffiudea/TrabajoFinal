package rutasturisticas.api.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rutasturisticas.api.aplicacion.servicios.RutaServicio;
import rutasturisticas.api.dominio.entidades.Ruta;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "*")
public class RutaControlador {

    @Autowired
    private RutaServicio servicio;

    @GetMapping("/")
    public List<Ruta> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Ruta get(@PathVariable int id) {
        return servicio.get(id);
    }

    @GetMapping("/ciudad/{idCiudad}")
    public List<Ruta> listarCiudad(@PathVariable int idCiudad) {
        return servicio.listarCiudad(idCiudad);
    }

    @PostMapping("/")
    public Ruta agregar(@RequestBody Ruta ruta) {
        return servicio.agregar(ruta);
    }

    @PutMapping("/")
    public Ruta modificar(@RequestBody Ruta ruta) {
        return servicio.modificar(ruta);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}