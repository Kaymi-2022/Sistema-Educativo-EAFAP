package fap.SistemaGestionEducativa.service.validadores;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Validaciones {

    private final UsuarioRepository repository;

    /**
     * Obtiene un usuario por su identificador.
     */
    public Usuario obtenerUsuario(Long idUsuario) {

        return repository.findById(idUsuario)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.USER_NOT_FOUND
                        ));

    }

    /**
     * Valida que no existan registros duplicados.
     */
    public void validarDuplicados(UsuarioRequest request) {

        if (repository.existsByDni(request.getDni())) {
            throw new DuplicateResourceException(
                    MessageConstants.USER_DNI_EXISTS
            );
        }

        if (repository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException(
                    MessageConstants.USERNAME_EXISTS
            );
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && repository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    MessageConstants.EMAIL_EXISTS
            );
        }

    }

}
