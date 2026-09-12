package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.Rol;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.model.seguridad.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    List<UsuarioRol> findByUsuarioIdUsuario(Long idUsuario);

    boolean existsByUsuarioAndRol(Usuario usuario, Rol rol);

    Optional<UsuarioRol> findByUsuarioIdUsuarioAndRolIdRol(Long idUsuario, Long idRol);

    @Query("select case when count(ur) > 0 then true else false end from UsuarioRol ur " +
            "where ur.usuario.idUsuario = :idUsuario and ur.usuario.estado = 'Y' " +
            "and upper(ur.rol.nombreRol) = upper(:nombreRol) and ur.rol.estado = 'Y'")
    boolean existsActiveRole(@Param("idUsuario") Long idUsuario, @Param("nombreRol") String nombreRol);

    @Query("select distinct u from Usuario u join u.usuarioRoles ur join ur.rol r " +
            "where u.estado = 'Y' and r.estado = 'Y' and upper(r.nombreRol) = upper(:nombreRol)")
    List<Usuario> findActiveUsersByRole(@Param("nombreRol") String nombreRol);
}
