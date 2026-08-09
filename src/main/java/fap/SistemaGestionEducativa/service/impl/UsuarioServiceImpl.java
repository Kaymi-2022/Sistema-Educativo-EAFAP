package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.seguridad.UsuarioMapper;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.UsuarioService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    /*
     * Obtiene un resistro
     */

    @Override
    public RestResponse<UsuarioResponse> registrar(UsuarioRequest request) {

        validarDuplicados(request);

        Usuario usuario = usuarioMapper.toEntity(request);

        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado("Y");

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.USER_CREATED,
                usuarioMapper.toResponse(usuarioGuardado)
        );

    }

    /*
     * Actualiza un registro
     */

    @Override
    public RestResponse<UsuarioResponse> actualizar(Long idUsuario, UsuarioRequest request) {
        Usuario usuario = obtenerUsuario(idUsuario);
        validarDuplicadosActualizar(usuario, request);
        usuarioMapper.updateEntity(request, usuario);
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        Usuario updatedUsuario = usuarioRepository.save(usuario);
        UsuarioResponse response = usuarioMapper.toResponse(updatedUsuario);
        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.USER_UPDATED,
                response
        );
    }

    /*
    * Se Obtiene un registro por su identificador
    *
    */

    @Override
    @Transactional
    public RestResponse<UsuarioResponse> obtenerPorId(Long idUsuario) {

        Usuario usuario = obtenerUsuario(idUsuario);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                usuarioMapper.toResponse(usuario)
        );

    }

    /**
    * Lista todos los usuarios.
    */

    @Override
    public RestResponse<List<UsuarioResponse>> listar() {

        List<Usuario> usuarioList = usuarioRepository.findAllByEstado("Y");
        List<UsuarioResponse> responses = usuarioList.stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                responses
        );
    }

    /*
     * Elimina un usuario por su identificador.
     * @param idUsuario el identificador del usuario a eliminar
     * @return la respuesta con el resultado de la operación
     */

    @Override
    public RestResponse<Void> eliminar(Long idUsuario) {
        Usuario usuario = obtenerUsuario(idUsuario);
        usuario.setEstado("N");
        usuarioRepository.save(usuario);
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.USER_DELETED,
                null
        );
    }

    /**
     * Obtiene un usuario por su identificador.
     * @param idUsuario el identificador del usuario a obtener
     * @return el usuario encontrado
     * @throws ResourceNotFoundException si el usuario no existe
     */
    public Usuario obtenerUsuario(Long idUsuario) {

        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.USER_NOT_FOUND
                        ));

    }

    /**
     * Valida que no existan registros duplicados.
     * @param request el objeto de solicitud del usuario a validar
     * @throws DuplicateResourceException si ya existe un usuario con el mismo DNI, nombre de usuario o correo electrónico
     */
    public void validarDuplicados(UsuarioRequest request) {

        if (usuarioRepository.existsByDni(request.getDni())) {
            throw new DuplicateResourceException(
                    MessageConstants.USER_DNI_EXISTS
            );
        }

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException(
                    MessageConstants.USERNAME_EXISTS
            );
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && usuarioRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    MessageConstants.EMAIL_EXISTS
            );
        }

    }

    /**
     * Valida que no existan registros duplicados al actualizar un usuario.
     * @param usuario el usuario a actualizar
     * @param request el objeto de solicitud del usuario a validar
     * @throws DuplicateResourceException si ya existe un usuario con el mismo DNI, nombre de usuario o correo electrónico
     */

    private void validarDuplicadosActualizar(Usuario usuario,
                                             UsuarioRequest request) {

        if (!usuario.getDni().equals(request.getDni())
                && usuarioRepository.existsByDni(request.getDni())) {

            throw new DuplicateResourceException(
                    MessageConstants.USER_DNI_EXISTS
            );
        }

        if (!usuario.getUsername().equals(request.getUsername())
                && usuarioRepository.existsByUsername(request.getUsername())) {

            throw new DuplicateResourceException(
                    MessageConstants.USERNAME_EXISTS
            );
        }

        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && !request.getEmail().equalsIgnoreCase(usuario.getEmail())
                && usuarioRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    MessageConstants.EMAIL_EXISTS
            );
        }

    }
}
