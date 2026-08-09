package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.evaluacion.ResultadoCursoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;

import java.util.List;

public interface ResultadoCursoService {

    RestResponse<ResultadoCursoResponse> registrar(ResultadoCursoRequest request);

    RestResponse<ResultadoCursoResponse> obtenerPorId(Long idResultado);

    RestResponse<ResultadoCursoResponse> obtenerPorCursoxDiscente(Long idCurso, Long idDiscente);

    RestResponse<List<ResultadoCursoResponse>> listar();

    RestResponse<Void> eliminar(Long idResultado);

//    ApiResponse<ResultadoCursoResponse> obtenerResultado(Long idCurso, Long idEstudiante);
//
//    ApiResponse<List<ResultadoCursoResponse>> listarPorCurso(Long idCurso);
//
//    ApiResponse<List<ResultadoCursoResponse>> listarPorEstudiante(Long idEstudiante);
//
//    ApiResponse<Void> recalcularResultado(Long idCurso);
//
//    ApiResponse<ResultadoCursoResponse> obtenerPorCursoAndDiscente(Long idCurso, Long idDiscente);
}
