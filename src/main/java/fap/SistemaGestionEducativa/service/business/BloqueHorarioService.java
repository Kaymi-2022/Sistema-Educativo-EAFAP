package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.BloqueHorarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.BloqueHorarioResponse;

import java.util.List;

public interface BloqueHorarioService {

    RestResponse<BloqueHorarioResponse> registrar(BloqueHorarioRequest request);

    RestResponse<BloqueHorarioResponse> actualizar(Long idBloque, BloqueHorarioRequest request);

    RestResponse<List<BloqueHorarioResponse>> listar();

    RestResponse<Void> eliminar(Long idBloque);

}
