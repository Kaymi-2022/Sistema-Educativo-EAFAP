package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;

import java.util.List;

public interface ResultadoCursoService {

    ApiResponse<List<ResultadoCursoResponse>> listar();

    ApiResponse<ResultadoCursoResponse> obtenerResultado(Long idCurso, Long idEstudiante);

    ApiResponse<List<ResultadoCursoResponse>> listarPorCurso(Long idCurso);

    ApiResponse<List<ResultadoCursoResponse>> listarPorEstudiante(Long idEstudiante);

    ApiResponse<Void> recalcularResultado(Long idCurso);

}
