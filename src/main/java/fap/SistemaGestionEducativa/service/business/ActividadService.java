package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.ActividadRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.ActividadResponse;

import java.util.List;

public interface ActividadService {

    ApiResponse<ActividadResponse> registrar(ActividadRequest request);

    ApiResponse<ActividadResponse> actualizar(Long idActividad, ActividadRequest request);

    ApiResponse<List<ActividadResponse>> listar();

    ApiResponse<List<ActividadResponse>> listarPorCurso(Long idCurso);

    ApiResponse<Void> eliminar(Long idActividad);

}
