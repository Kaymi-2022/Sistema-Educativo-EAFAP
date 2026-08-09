
package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRolRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioRolResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.seguridad.UsuarioRolMapper;
import fap.SistemaGestionEducativa.model.seguridad.Rol;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.model.seguridad.UsuarioRol;
import fap.SistemaGestionEducativa.repository.seguridad.RolRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRolRepository;
import fap.SistemaGestionEducativa.service.business.UsuarioRolService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioRolServiceImpl implements UsuarioRolService {

    private final UsuarioRolRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioRolMapper mapper;

    @Override
    public RestResponse<UsuarioRolResponse> asignarRol(
            UsuarioRolRequest request) {

        Usuario usuario = obtenerUsuario(request.getIdUsuario());

        Rol rol = obtenerRol(request.getIdRol());

        validarUsuarioActivo(usuario);

        validarRolActivo(rol);

        validarAsignacionDuplicada(usuario, rol);

        UsuarioRol usuarioRol = new UsuarioRol();

        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        UsuarioRol usuarioRolGuardado =
                repository.save(usuarioRol);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.ROLE_ASSIGNED,
                mapper.toResponse(usuarioRolGuardado)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<UsuarioRolResponse>> listarRolesUsuario(
            Long idUsuario) {

        obtenerUsuario(idUsuario);

        List<UsuarioRol> lista =
                repository.findByUsuarioIdUsuario(idUsuario);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponseList(lista)
        );
    }

    @Override
    public RestResponse<Void> eliminarRol(Long idUsuario, Long idRol) {

        UsuarioRol usuarioRol = obtenerUsuarioRol(idUsuario, idRol);

        repository.delete(usuarioRol);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.ROLE_REMOVED,
                null
        );
    }

    /**
     * Obtiene un usuario por su identificador.
     */
    private Usuario obtenerUsuario(Long idUsuario) {

        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));
    }

    /**
     * Obtiene un rol por su identificador.
     */
    private Rol obtenerRol(Long idRol) {

        return rolRepository.findById(idRol)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessageConstants.ROLE_NOT_FOUND));
    }

    /**
     * Verifica que el usuario esté activo.
     */
    private void validarUsuarioActivo(Usuario usuario) {

        if (!"Y".equals(usuario.getEstado())) {

            throw new BusinessException(MessageConstants.USER_INACTIVE);
        }
    }

    /**
     * Verifica que el rol esté activo.
     */
    private void validarRolActivo(Rol rol) {

        if (!"Y".equals(rol.getEstado())) {

            throw new BusinessException(MessageConstants.ROLE_INACTIVE);
        }
    }

    /**
     * Verifica que la relación Usuario-Rol
     * no exista previamente.
     */
    private void validarAsignacionDuplicada(
            Usuario usuario,
            Rol rol) {

        if (repository.existsByUsuarioAndRol(usuario, rol)) {

            throw new DuplicateResourceException(MessageConstants.ROLE_ALREADY_ASSIGNED);
        }
    }

    /**
     * Obtiene una asignación específica
     * de usuario y rol.
     */
    private UsuarioRol obtenerUsuarioRol(Long idUsuario, Long idRol) {

        return repository.findByUsuarioIdUsuarioAndRolIdRol(idUsuario, idRol)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.ROLE_ASSIGNMENT_NOT_FOUND));
    }
}