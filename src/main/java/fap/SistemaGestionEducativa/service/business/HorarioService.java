package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.HorarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.HorarioResponse;

import java.util.List;

public interface HorarioService {

    RestResponse<HorarioResponse> registrar(HorarioRequest request);

    RestResponse<HorarioResponse> actualizar(Long idHorario, HorarioRequest request);

    RestResponse<HorarioResponse> obtenerPorId(Long idHorario);

    RestResponse<List<HorarioResponse>> listar();

    RestResponse<List<HorarioResponse>> listarPorDocente(Long idDocente);

    RestResponse<List<HorarioResponse>> listarPorCurso(Long idCurso);

    RestResponse<List<HorarioResponse>> listarPorSemana(Long idSemana);

    RestResponse<Void> eliminar(Long idHorario);

}