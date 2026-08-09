package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.evaluacion.EvaluacionRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.EvaluacionResponse;

import java.util.List;

public interface EvaluacionService {

    RestResponse<EvaluacionResponse> registrar(EvaluacionRequest request);

    RestResponse<EvaluacionResponse> actualizar(Long idEvaluacion, EvaluacionRequest request);

    RestResponse<EvaluacionResponse> obtenerPorId(Long idEvaluacion);

    RestResponse<List<EvaluacionResponse>> listar();

    //ApiResponse<List<EvaluacionResponse>> listarPorCurso(Long idCurso);

    RestResponse<Void> eliminar(Long idEvaluacion);

}
