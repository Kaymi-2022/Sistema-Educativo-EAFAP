package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>
{
    boolean existsByUsername(String username);
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    List<Usuario> findAllByEstado(String estado);

    @EntityGraph(attributePaths = {"usuarioRoles", "usuarioRoles.rol"})
    Optional<Usuario> findByUsernameIgnoreCase(String username);

    Optional<Usuario> findByDni(String dni);
}
