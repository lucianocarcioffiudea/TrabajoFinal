package rutasturisticas.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rutasturisticas.api.dominio.entidades.Ruta;
import rutasturisticas.api.infraestructura.repositorios.IRutaRepositorio;

import java.util.List;

@Service
public class RutaServicio {

    @Autowired
    private IRutaRepositorio repositorio;

    public List<Ruta> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public List<Ruta> listarCiudad(int idCiudad) {
        return repositorio.findByCiudadId(idCiudad);
    }

    public Ruta get(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Ruta agregar(Ruta ruta) {
        ruta.setId(0);
        return repositorio.save(ruta);
    }

    public Ruta modificar(Ruta ruta) {
        if (!repositorio.existsById(ruta.getId())) {
            return null;
        }
        return repositorio.save(ruta);
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
