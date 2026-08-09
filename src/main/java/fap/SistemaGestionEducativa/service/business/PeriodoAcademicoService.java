package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.PeriodoAcademicoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.PeriodoAcademicoResponse;

import java.util.List;

public interface PeriodoAcademicoService {

    RestResponse<PeriodoAcademicoResponse> registrar(PeriodoAcademicoRequest request);

    RestResponse<PeriodoAcademicoResponse> actualizar(Long idPeriodo, PeriodoAcademicoRequest request);

    RestResponse<PeriodoAcademicoResponse> obtenerPorId(Long idPeriodo);

    RestResponse<List<PeriodoAcademicoResponse>> listar();

    RestResponse<Void> eliminar(Long idPeriodo);

}
