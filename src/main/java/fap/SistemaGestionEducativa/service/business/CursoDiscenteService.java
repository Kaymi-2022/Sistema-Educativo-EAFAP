package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.MatriculaRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoDiscenteResponse;

import java.util.List;

public interface CursoDiscenteService {

    ApiResponse<CursoDiscenteResponse> matricular(MatriculaRequest request);

    ApiResponse<List<CursoDiscenteResponse>> listar();

    ApiResponse<List<CursoDiscenteResponse>> listarPorCurso(Long idCurso);

    ApiResponse<List<CursoDiscenteResponse>> listarPorEstudiante(Long idEstudiante);

    ApiResponse<Void> retirar(Long idCursoDiscente);

}