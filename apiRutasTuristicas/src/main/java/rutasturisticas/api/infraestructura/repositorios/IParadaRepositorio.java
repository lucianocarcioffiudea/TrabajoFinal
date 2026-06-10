package rutasturisticas.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rutasturisticas.api.dominio.entidades.Parada;

@Repository
public interface IParadaRepositorio extends JpaRepository<Parada, Integer>{

    List<Parada> findByRutaIdOrderByOrdenAsc(int idRuta);

}
