package fap.SistemaGestionEducativa.service.impl;


import fap.SistemaGestionEducativa.dto.request.seguridad.RolRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.RolResponse;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.seguridad.RolMapper;
import fap.SistemaGestionEducativa.model.seguridad.Rol;
import fap.SistemaGestionEducativa.repository.seguridad.RolRepository;
import fap.SistemaGestionEducativa.service.business.RolService;
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
public class RolServiceImpl implements RolService {

    private final RolRepository repository;
    private final RolMapper rolMapper;

    @Override
    public RestResponse<RolResponse> registrar(RolRequest request) {

        validarRolDuplicado(request);

        Rol rol = rolMapper.toEntity(request);
        rol.setEstado("Y");

        Rol rolGuardado = repository.save(rol);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.ROLE_CREATED,
                rolMapper.toResponse(rolGuardado)
        );
    }

    @Override
    public RestResponse<RolResponse> actualizar(Long idRol,
                                                RolRequest request) {

        Rol rol = obtenerRol(idRol);

        validarRolDuplicadoActualizar(rol, request);

        rolMapper.updateEntity(request, rol);

        Rol rolActualizado = repository.save(rol);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.ROLE_UPDATED,
                rolMapper.toResponse(rolActualizado)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<RolResponse> obtenerPorId(Long idRol) {

        Rol rol = obtenerRol(idRol);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                rolMapper.toResponse(rol)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<RolResponse>> listar() {

        List<Rol> roles = repository.findAllByEstado("Y");

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                rolMapper.toResponseList(roles)
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idRol) {

        Rol rol = obtenerRol(idRol);

        rol.setEstado("N");

        repository.save(rol);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.ROLE_DELETED,
                null
        );
    }

    /**
     * Obtiene un rol por su identificador.
     */
    private Rol obtenerRol(Long idRol) {

        return repository.findById(idRol)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.ROLE_NOT_FOUND
                        ));
    }

    /**
     * Valida si ya existe un rol con el mismo nombre.
     */
    private void validarRolDuplicado(RolRequest request) {

        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(rol -> rol.getNombreRol() != null
                        && rol.getNombreRol().equalsIgnoreCase(request.getNombreRol()));

        if (existe) {

            throw new DuplicateResourceException(
                    MessageConstants.ROLE_ALREADY_EXISTS
            );
        }
    }

    /**
     * Valida duplicidad al actualizar.
     */
    private void validarRolDuplicadoActualizar(Rol rol,
                                               RolRequest request) {

        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(rolActual -> rolActual.getNombreRol() != null
                        && rolActual.getNombreRol().equalsIgnoreCase(request.getNombreRol())
                        && !rolActual.getIdRol().equals(rol.getIdRol()));

        if (!rol.getNombreRol().equalsIgnoreCase(request.getNombreRol())
                && existe) {

            throw new DuplicateResourceException(
                    MessageConstants.ROLE_ALREADY_EXISTS
            );
        }

    }

}