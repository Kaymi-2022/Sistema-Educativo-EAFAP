package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.evaluacion.NotaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;

import java.util.List;

public interface NotaService {

    RestResponse<NotaResponse> registrar(NotaRequest request);

    RestResponse<NotaResponse> actualizar(Long idNota, NotaRequest request);

    RestResponse<NotaResponse> obtenerPorId(Long idNota);

    RestResponse<List<NotaResponse>> listar();

//    ApiResponse<List<NotaResponse>> listarPorEvaluacion(Long idEvaluacion);
//
//    ApiResponse<List<NotaResponse>> listarPorEstudiante(Long idEstudiante);

    RestResponse<Void> eliminar(Long idNota);
}