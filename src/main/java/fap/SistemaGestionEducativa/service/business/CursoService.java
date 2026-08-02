package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.CursoRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoResponse;

import java.util.List;

public interface CursoService {

    ApiResponse<CursoResponse> registrar(CursoRequest request);

    ApiResponse<CursoResponse> actualizar(Long idCurso, CursoRequest request);

    ApiResponse<CursoResponse> obtenerPorId(Long idCurso);

    ApiResponse<List<CursoResponse>> listar();

    ApiResponse<List<CursoResponse>> listarPorCategoria(Long idCategoria);

    ApiResponse<List<CursoResponse>> listarPorDocente(Long idDocente);

    ApiResponse<Void> eliminar(Long idCurso);

}
