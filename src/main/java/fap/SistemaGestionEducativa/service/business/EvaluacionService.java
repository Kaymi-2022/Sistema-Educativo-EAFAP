package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.evaluacion.EvaluacionRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.EvaluacionResponse;

import java.util.List;

public interface EvaluacionService {

    ApiResponse<EvaluacionResponse> registrar(EvaluacionRequest request);

    ApiResponse<EvaluacionResponse> actualizar(Long idEvaluacion, EvaluacionRequest request);

    ApiResponse<EvaluacionResponse> obtenerPorId(Long idEvaluacion);

    ApiResponse<List<EvaluacionResponse>> listar();

    ApiResponse<List<EvaluacionResponse>> listarPorCurso(Long idCurso);

    ApiResponse<Void> eliminar(Long idEvaluacion);

}
