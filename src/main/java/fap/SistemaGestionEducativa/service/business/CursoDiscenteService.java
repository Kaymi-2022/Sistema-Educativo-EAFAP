package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.MatriculaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoDiscenteResponse;

import java.util.List;

public interface CursoDiscenteService {

    RestResponse<CursoDiscenteResponse> registrar(MatriculaRequest request);

    RestResponse<List<CursoDiscenteResponse>> listar();

    RestResponse<CursoDiscenteResponse> obtenerPorId(Long idCursoDiscente);

    RestResponse<Void> eliminar(Long idCursoDiscente);
}