package rutasturisticas.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rutasturisticas.api.dominio.entidades.Tipo;
import rutasturisticas.api.infraestructura.repositorios.ITipoRepositorio;

import java.util.List;

@Service
public class TipoServicio {

    @Autowired
    private ITipoRepositorio repositorio;

    public List<Tipo> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public Tipo get(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Tipo agregar(Tipo tipo) {
        tipo.setId(0);
        return repositorio.save(tipo);
    }

    public Tipo modificar(Tipo tipo) {
        if (!repositorio.existsById(tipo.getId())) {
            return null;
        }
        return repositorio.save(tipo);
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