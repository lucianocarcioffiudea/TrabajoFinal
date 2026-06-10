package rutasturisticas.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rutasturisticas.api.dominio.entidades.Pais;
import rutasturisticas.api.infraestructura.repositorios.IPaisRepositorio;

import java.util.List;

@Service
public class PaisServicio {

    @Autowired
    private IPaisRepositorio repositorio;

    public List<Pais> listar() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public Pais get(int id) {
        return repositorio.findById(id).orElse(null);
    }

    public Pais agregar(Pais pais) {
        pais.setId(0);
        return repositorio.save(pais);
    }

    public Pais modificar(Pais pais) {
        if (!repositorio.existsById(pais.getId())) {
            return null;
        }
        return repositorio.save(pais);
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
