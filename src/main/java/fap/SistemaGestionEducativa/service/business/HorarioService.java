package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.HorarioRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.HorarioResponse;

import java.util.List;

public interface HorarioService {

    ApiResponse<HorarioResponse> registrar(HorarioRequest request);

    ApiResponse<HorarioResponse> actualizar(Long idHorario, HorarioRequest request);

    ApiResponse<HorarioResponse> obtenerPorId(Long idHorario);

    ApiResponse<List<HorarioResponse>> listar();

    ApiResponse<List<HorarioResponse>> listarPorDocente(Long idDocente);

    ApiResponse<List<HorarioResponse>> listarPorCurso(Long idCurso);

    ApiResponse<List<HorarioResponse>> listarPorSemana(Long idSemana);

    ApiResponse<Void> eliminar(Long idHorario);

}