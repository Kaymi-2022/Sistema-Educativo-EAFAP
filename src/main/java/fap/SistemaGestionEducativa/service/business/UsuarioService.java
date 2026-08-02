package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    ApiResponse<UsuarioResponse> registrar(UsuarioRequest request);

    ApiResponse<UsuarioResponse> actualizar(Long idUsuario,
                                            UsuarioRequest request);

    ApiResponse<UsuarioResponse> obtenerPorId(Long idUsuario);

    ApiResponse<List<UsuarioResponse>> listar();

    ApiResponse<Void> eliminar(Long idUsuario);
}
