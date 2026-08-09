package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    RestResponse<UsuarioResponse> registrar(UsuarioRequest request);

    RestResponse<UsuarioResponse> actualizar(Long idUsuario,
                                             UsuarioRequest request);

    RestResponse<UsuarioResponse> obtenerPorId(Long idUsuario);

    RestResponse<List<UsuarioResponse>> listar();

    RestResponse<Void> eliminar(Long idUsuario);
}
