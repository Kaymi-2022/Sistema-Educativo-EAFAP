package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.evaluacion.NotaRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;

import java.util.List;

public interface NotaService {

    ApiResponse<NotaResponse> registrar(NotaRequest request);

    ApiResponse<NotaResponse> actualizar(Long idNota, NotaRequest request);

    ApiResponse<List<NotaResponse>> listar();

    ApiResponse<List<NotaResponse>> listarPorEvaluacion(Long idEvaluacion);

    ApiResponse<List<NotaResponse>> listarPorEstudiante(Long idEstudiante);

}