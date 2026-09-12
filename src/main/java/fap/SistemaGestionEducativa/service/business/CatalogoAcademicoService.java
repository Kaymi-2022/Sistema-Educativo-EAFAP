package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.UsuarioCatalogoResponse;

import java.util.List;

public interface CatalogoAcademicoService {
    RestResponse<List<UsuarioCatalogoResponse>> listarDocentes();
    RestResponse<List<UsuarioCatalogoResponse>> listarDiscentes();
}
