package fap.SistemaGestionEducativa.service.security;

import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserService {
    private final UsuarioRepository usuarioRepository;

    public Usuario requireCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Usuario no autenticado.");
        }
        return usuarioRepository.findByUsernameIgnoreCase(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));
    }

    public boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }

    public void requireOwnStudent(Long idUsuario) {
        if (hasRole("DISCENTE") && !hasRole("ADMIN") && !hasRole("DOCENTE")
                && !requireCurrentUser().getIdUsuario().equals(idUsuario)) {
            throw new AccessDeniedException("No puede consultar datos de otro discente.");
        }
    }

    public void requireSelfOrAdmin(Long idUsuario) {
        if (hasRole("ADMIN")) return;
        if (!hasRole("DISCENTE") || !requireCurrentUser().getIdUsuario().equals(idUsuario)) {
            throw new org.springframework.security.access.AccessDeniedException("No puede consultar el reporte solicitado.");
        }
    }
}
