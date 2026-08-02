package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.PeriodoAcademicoRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.PeriodoAcademicoResponse;

import java.util.List;

public interface PeriodoAcademicoService {

    ApiResponse<PeriodoAcademicoResponse> registrar(PeriodoAcademicoRequest request);

    ApiResponse<PeriodoAcademicoResponse> actualizar(Long idPeriodo, PeriodoAcademicoRequest request);

    ApiResponse<PeriodoAcademicoResponse> obtenerPorId(Long idPeriodo);

    ApiResponse<List<PeriodoAcademicoResponse>> listar();

    ApiResponse<Void> eliminar(Long idPeriodo);

}
