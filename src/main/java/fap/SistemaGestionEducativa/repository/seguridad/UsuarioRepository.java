package fap.SistemaGestionEducativa.repository.seguridad;

import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>
{
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByDni(String dni);
    boolean existsByUsername(String username);
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    List<Usuario> findByEstado(String estado);
}
