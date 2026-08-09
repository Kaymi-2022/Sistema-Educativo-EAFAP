package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRolRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioRolResponse;

import java.util.List;

public interface UsuarioRolService {

    RestResponse<UsuarioRolResponse> asignarRol(UsuarioRolRequest request);

    RestResponse<List<UsuarioRolResponse>> listarRolesUsuario(Long idUsuario);

    RestResponse<Void> eliminarRol(Long idUsuarioRol, Long idRol);
}
