package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.AulaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.AulaResponse;

import java.util.List;

public interface AulaService {

    RestResponse<AulaResponse> registrar(AulaRequest request);

    RestResponse<AulaResponse> actualizar(Long idAula, AulaRequest request);

    RestResponse<List<AulaResponse>> listar();

    RestResponse<AulaResponse> obtenerPorId(Long idAula);

    RestResponse<Void> eliminar(Long idAula);

}
