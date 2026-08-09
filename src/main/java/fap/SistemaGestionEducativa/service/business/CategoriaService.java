package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.CategoriaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CategoriaResponse;

import java.util.List;

public interface CategoriaService {

    RestResponse<CategoriaResponse> registrar(CategoriaRequest request);

    RestResponse<CategoriaResponse> actualizar(Long idCategoria, CategoriaRequest request);

    RestResponse<CategoriaResponse> obtenerPorId(Long idCategoria);

    RestResponse<List<CategoriaResponse>> listar();

    RestResponse<Void> eliminar(Long idCategoria);

}
