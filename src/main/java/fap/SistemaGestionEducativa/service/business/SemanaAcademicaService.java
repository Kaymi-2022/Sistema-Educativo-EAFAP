package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.SemanaAcademicaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.SemanaAcademicaResponse;

import java.util.List;

public interface SemanaAcademicaService {

    RestResponse<SemanaAcademicaResponse> registrar(SemanaAcademicaRequest request);

    RestResponse<SemanaAcademicaResponse> actualizar(Long idSemana, SemanaAcademicaRequest request);

    RestResponse<List<SemanaAcademicaResponse>> listar();

    RestResponse<Void> eliminar(Long idSemana);

}
