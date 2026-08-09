package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.ActividadRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.ActividadResponse;

import java.util.List;

public interface ActividadService {

    RestResponse<ActividadResponse> registrar(ActividadRequest request);

    RestResponse<ActividadResponse> actualizar(Long idActividad, ActividadRequest request);

    RestResponse<List<ActividadResponse>> listar();

    RestResponse<List<ActividadResponse>> listarPorCurso(Long idCurso);

    RestResponse<Void> eliminar(Long idActividad);

}
