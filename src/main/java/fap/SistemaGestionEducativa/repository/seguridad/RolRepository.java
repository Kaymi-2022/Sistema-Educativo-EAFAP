package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface RolRepository extends JpaRepository<Rol, Long> {
    List<Rol> findAllByEstado(String estado);
    boolean existsByNombreRolIgnoreCase(String nombreRol);
}
