package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.Rol;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.model.seguridad.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    List<UsuarioRol> findByUsuarioIdUsuario(Long idUsuario);

    boolean existsByUsuarioAndRol(Usuario usuario, Rol rol);

    Optional<UsuarioRol> findByUsuarioIdUsuarioAndRolIdRol(Long idUsuario, Long idRol);
}
