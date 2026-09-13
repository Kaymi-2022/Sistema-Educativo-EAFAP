package fap.SistemaGestionEducativa.service.security;

import fap.SistemaGestionEducativa.model.academico.Curso;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseOwnershipValidator {
    private final CurrentUserService currentUserService;

    public void requireTeacherOwns(Curso curso) {
        if (currentUserService.hasRole("ADMIN")) return;
        if (!currentUserService.hasRole("DOCENTE") || curso.getDocente() == null
                || !curso.getDocente().getIdUsuario().equals(currentUserService.requireCurrentUser().getIdUsuario())) {
            throw new AccessDeniedException("El docente no tiene acceso al curso solicitado.");
        }
    }
}
