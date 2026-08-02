package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRolRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioRolResponse;

import java.util.List;

public interface UsuarioRolService {

    ApiResponse<UsuarioRolResponse> asignarRol(UsuarioRolRequest request);

    ApiResponse<List<UsuarioRolResponse>> listarRolesUsuario(Long idUsuario);

    ApiResponse<Void> eliminarRol(Long idUsuarioRol);
}
