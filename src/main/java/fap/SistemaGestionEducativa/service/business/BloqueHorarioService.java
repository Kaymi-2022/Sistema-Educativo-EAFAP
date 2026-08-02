package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.BloqueHorarioRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.BloqueHorarioResponse;

import java.util.List;

public interface BloqueHorarioService {

    ApiResponse<BloqueHorarioResponse> registrar(BloqueHorarioRequest request);

    ApiResponse<BloqueHorarioResponse> actualizar(Long idBloque, BloqueHorarioRequest request);

    ApiResponse<List<BloqueHorarioResponse>> listar();

    ApiResponse<Void> eliminar(Long idBloque);

}
