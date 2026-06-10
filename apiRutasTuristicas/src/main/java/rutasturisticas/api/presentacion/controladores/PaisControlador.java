package rutasturisticas.api.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import rutasturisticas.api.aplicacion.servicios.PaisServicio;
import rutasturisticas.api.dominio.entidades.Pais;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin(origins = "*") // Permite la conexión con Angular
public class PaisControlador {

    @Autowired
    private PaisServicio servicio;

    @GetMapping("/")
    public List<Pais> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Pais get(@PathVariable int id) {
        return servicio.get(id);
    }

    @PostMapping("/")
    public Pais agregar(@RequestBody Pais pais) {
        return servicio.agregar(pais);
    }

    @PutMapping("/")
    public Pais modificar(@RequestBody Pais pais) {
        return servicio.modificar(pais);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return servicio.eliminar(id);
    }
}