package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.SemanaAcademicaRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.SemanaAcademicaResponse;

import java.util.List;

public interface SemanaAcademicaService {

    ApiResponse<SemanaAcademicaResponse> registrar(SemanaAcademicaRequest request);

    ApiResponse<SemanaAcademicaResponse> actualizar(Long idSemana, SemanaAcademicaRequest request);

    ApiResponse<List<SemanaAcademicaResponse>> listar();

    ApiResponse<Void> eliminar(Long idSemana);

}
