package rutasturisticas.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rutasturisticas.api.dominio.entidades.Parada;
import rutasturisticas.api.infraestructura.repositorios.IParadaRepositorio;

import java.util.List;

@Service
public class ParadaServicio {

    @Autowired
    private IParadaRepositorio repositorio;

    public List<Parada> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public List<Parada> listarRuta(int idRuta) {
        return repositorio.findByRutaIdOrderByOrdenAsc(idRuta);
    }

    public Parada get(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Parada agregar(Parada parada) {
        parada.setId(0);
        return repositorio.save(parada);
    }

    public Parada modificar(Parada parada) {
        if (!repositorio.existsById(parada.getId())) {
            return null;
        }
        return repositorio.save(parada);
    }

    public boolean eliminar(int id) {
        try {
            repositorio.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}