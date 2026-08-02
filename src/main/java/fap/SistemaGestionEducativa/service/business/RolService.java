package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.seguridad.RolRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.RolResponse;

import java.util.List;

public interface RolService {

    ApiResponse<RolResponse> registrar(RolRequest request);

    ApiResponse<RolResponse> actualizar(Long idRol, RolRequest request);

    ApiResponse<RolResponse> obtenerPorId(Long idRol);

    ApiResponse<List<RolResponse>> listar();

    ApiResponse<Void> eliminar(Long idRol);

}
