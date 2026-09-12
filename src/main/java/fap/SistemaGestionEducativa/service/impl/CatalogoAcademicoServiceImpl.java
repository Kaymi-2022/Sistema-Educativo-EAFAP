package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.UsuarioCatalogoResponse;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRolRepository;
import fap.SistemaGestionEducativa.service.business.CatalogoAcademicoService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CatalogoAcademicoServiceImpl implements CatalogoAcademicoService {

    private final UsuarioRolRepository usuarioRolRepository;

    @Override
    public RestResponse<List<UsuarioCatalogoResponse>> listarDocentes() {
        return listarPorRol("DOCENTE");
    }

    @Override
    public RestResponse<List<UsuarioCatalogoResponse>> listarDiscentes() {
        return listarPorRol("DISCENTE");
    }

    private RestResponse<List<UsuarioCatalogoResponse>> listarPorRol(String rol) {
        List<UsuarioCatalogoResponse> usuarios = usuarioRolRepository.findActiveUsersByRole(rol).stream()
                .map(this::toResponse)
                .toList();
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, usuarios);
    }

    private UsuarioCatalogoResponse toResponse(Usuario usuario) {
        return UsuarioCatalogoResponse.builder()
                .idUsuario(usuario.getIdUsuario())
                .dni(usuario.getDni())
                .nombres(usuario.getNombres())
                .apellidos(usuario.getApellidos())
                .username(usuario.getUsername())
                .build();
    }
}
