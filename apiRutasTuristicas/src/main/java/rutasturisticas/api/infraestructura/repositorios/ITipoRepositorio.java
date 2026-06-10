package rutasturisticas.api.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rutasturisticas.api.dominio.entidades.Tipo;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Integer>{

}
