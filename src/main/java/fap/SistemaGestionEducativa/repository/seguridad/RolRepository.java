package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByNombreRol(String nombreRol);
    Optional<Rol> findByNombreRol(String nombreRol);
}
