package fap.SistemaGestionEducativa.service.security;

import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRolRepository;
import fap.SistemaGestionEducativa.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioRolValidator {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;

    public Usuario requireActiveUser(Long idUsuario, String inactiveMessage) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));
        if (!"Y".equals(usuario.getEstado())) {
            throw new BusinessException(inactiveMessage);
        }
        return usuario;
    }

    public void requireDiscente(Long idUsuario) {
        requireRole(idUsuario, "DISCENTE", MessageConstants.STUDENT_NOT_FOUND,
                MessageConstants.STUDENT_INACTIVE, MessageConstants.STUDENT_ROLE_REQUIRED);
    }

    public void requireDocente(Long idUsuario) {
        requireRole(idUsuario, "DOCENTE", MessageConstants.TEACHER_NOT_FOUND,
                MessageConstants.TEACHER_INACTIVE, MessageConstants.TEACHER_ROLE_REQUIRED);
    }

    private void requireRole(Long idUsuario, String role, String notFoundMessage,
                             String inactiveMessage, String roleMessage) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        if (!"Y".equals(usuario.getEstado())) {
            throw new BusinessException(inactiveMessage);
        }
        if (!usuarioRolRepository.existsActiveRole(idUsuario, role)) {
            throw new BusinessException(roleMessage);
        }
    }
}
