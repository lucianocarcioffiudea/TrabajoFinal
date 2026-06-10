package rutasturisticas.api.infraestructura.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rutasturisticas.api.dominio.entidades.Ciudad;

@Repository
public interface ICiudadRepositorio extends JpaRepository<Ciudad, Integer>{

}

