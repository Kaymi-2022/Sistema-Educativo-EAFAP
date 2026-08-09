package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.seguridad.RolRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.RolResponse;

import java.util.List;

public interface RolService {

    RestResponse<RolResponse> registrar(RolRequest request);

    RestResponse<RolResponse> actualizar(Long idRol, RolRequest request);

    RestResponse<RolResponse> obtenerPorId(Long idRol);

    RestResponse<List<RolResponse>> listar();

    RestResponse<Void> eliminar(Long idRol);

}
