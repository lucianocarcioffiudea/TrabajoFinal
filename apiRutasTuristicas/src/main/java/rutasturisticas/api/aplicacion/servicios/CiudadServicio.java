package rutasturisticas.api.aplicacion.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import rutasturisticas.api.dominio.entidades.Ciudad;
import rutasturisticas.api.infraestructura.repositorios.*;

@Service
public class CiudadServicio {

    @Autowired
    private ICiudadRepositorio repositorio;

    public List<Ciudad> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public Ciudad get(int id) {
        return repositorio.findById(id).isEmpty() ? null : repositorio.findById(id).get();
    }

    public Ciudad agregar(Ciudad ciudad) {
        ciudad.setId(0);
        return repositorio.save(ciudad);
    }

    public Ciudad modificar(Ciudad ciudad) {
        if (repositorio.findById(ciudad.getId()).isEmpty())
            return null;
        return repositorio.save(ciudad);
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
