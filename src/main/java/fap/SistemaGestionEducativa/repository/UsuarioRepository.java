package fap.SistemaGestionEducativa.repository;

import fap.SistemaGestionEducativa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>
{
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
