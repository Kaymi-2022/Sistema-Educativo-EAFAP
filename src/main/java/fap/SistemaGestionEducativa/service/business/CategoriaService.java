package fap.SistemaGestionEducativa.service.business;

import fap.SistemaGestionEducativa.dto.request.academico.CategoriaRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CategoriaResponse;

import java.util.List;

public interface CategoriaService {

    ApiResponse<CategoriaResponse> registrar(CategoriaRequest request);

    ApiResponse<CategoriaResponse> actualizar(Long idCategoria, CategoriaRequest request);

    ApiResponse<CategoriaResponse> obtenerPorId(Long idCategoria);

    ApiResponse<List<CategoriaResponse>> listar();

    ApiResponse<Void> eliminar(Long idCategoria);

}
