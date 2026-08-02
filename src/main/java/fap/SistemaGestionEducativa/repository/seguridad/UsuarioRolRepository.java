package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    List<UsuarioRol> findByUsuarioIdUsuario(Long idUsuario);

    List<UsuarioRol> findByRolIdRol(Long idRol);
}
