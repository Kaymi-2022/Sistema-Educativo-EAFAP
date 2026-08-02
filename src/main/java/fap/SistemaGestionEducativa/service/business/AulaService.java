package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.AulaRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.AulaResponse;

import java.util.List;

public interface AulaService {

    ApiResponse<AulaResponse> registrar(AulaRequest request);

    ApiResponse<AulaResponse> actualizar(Long idAula, AulaRequest request);

    ApiResponse<List<AulaResponse>> listar();

    ApiResponse<AulaResponse> obtenerPorId(Long idAula);

    ApiResponse<Void> eliminar(Long idAula);

}
