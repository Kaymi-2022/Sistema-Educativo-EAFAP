package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.CursoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoResponse;

import java.util.List;

public interface CursoService {

    RestResponse<CursoResponse> registrar(CursoRequest request);

    RestResponse<CursoResponse> actualizar(Long idCurso, CursoRequest request);

    RestResponse<CursoResponse> obtenerPorId(Long idCurso);

    RestResponse<List<CursoResponse>> listar();

    RestResponse<Void> eliminar(Long idCurso);

}
