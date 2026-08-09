package fap.SistemaGestionEducativa.service.impl;


import fap.SistemaGestionEducativa.dto.request.academico.CategoriaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CategoriaResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.CategoriaMapper;
import fap.SistemaGestionEducativa.model.academico.Categoria;
import fap.SistemaGestionEducativa.repository.academico.CategoriaRepository;
import fap.SistemaGestionEducativa.service.business.CategoriaService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    /**
     * Registra una nueva categoría.
     */
    @Override
    public RestResponse<CategoriaResponse> registrar(CategoriaRequest request) {

        validarCategoriaDuplicada(request);

        Categoria categoria = categoriaMapper.toEntity(request);

        categoria.setEstado("Y");

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.CATEGORY_CREATED,
                categoriaMapper.toResponse(categoriaGuardada)
        );
    }

    /**
     * Actualiza una categoría existente.
     */
    @Override
    public RestResponse<CategoriaResponse> actualizar(Long idCategoria, CategoriaRequest request) {

        Categoria categoria = obtenerCategoria(idCategoria);

        validarCategoriaDuplicadaActualizar(categoria, request);

        categoriaMapper.updateEntity(request, categoria);

        Categoria categoriaActualizada =
                categoriaRepository.save(categoria);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.CATEGORY_UPDATED,
                categoriaMapper.toResponse(categoriaActualizada)
        );
    }

    /**
     * Obtiene una categoría por su identificador.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<CategoriaResponse> obtenerPorId(Long idCategoria) {

        Categoria categoria = obtenerCategoria(idCategoria);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                categoriaMapper.toResponse(categoria)
        );
    }

    /**
     * Lista todas las categorías activas.
     */

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<CategoriaResponse>> listar() {

        List<Categoria> categorias = categoriaRepository.findAllByEstado("Y");

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                categoriaMapper.toResponseList(categorias)
        );
    }

    /**
     *  Elimina una categoría por su identificador.
     * @param idCategoria
     * @return
     */

    @Override
    public RestResponse<Void> eliminar(Long idCategoria) {

        Categoria categoria = obtenerCategoria(idCategoria);

        validarCategoriaActiva(categoria);

        categoria.setEstado("N");

        categoriaRepository.save(categoria);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.CATEGORY_DELETED,
                null
        );
    }

    private void validarCategoriaActiva(Categoria categoria) {

        if (!"Y".equals(categoria.getEstado())) {

            throw new BusinessException(
                    MessageConstants.CATEGORY_INACTIVE
            );
        }
    }

    /**
     * Obtiene una categoría por su identificador.
     */
    private Categoria obtenerCategoria(Long idCategoria) {

        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessageConstants.CATEGORY_NOT_FOUND));
    }

    /**
     * Valida que no exista otra categoría
     * con el mismo nombre.
     */
    private void validarCategoriaDuplicada(CategoriaRequest request) {

        boolean existe = categoriaRepository.findAllByEstado("Y").stream()
                .anyMatch(categoria -> categoria.getNombre() != null
                        && categoria.getNombre().equalsIgnoreCase(request.getNombre()));

        if (existe) {

            throw new DuplicateResourceException(
                    MessageConstants.CATEGORY_ALREADY_EXISTS
            );
        }
    }

    /**
     * Valida duplicidad cuando se actualiza
     * una categoría.
     */
    private void validarCategoriaDuplicadaActualizar(Categoria categoria, CategoriaRequest request) {

        boolean mismoNombre = categoria.getNombre().equalsIgnoreCase(request.getNombre());

        boolean existe = categoriaRepository.findAllByEstado("Y").stream()
                .anyMatch(categoriaActual -> categoriaActual.getNombre() != null
                        && categoriaActual.getNombre().equalsIgnoreCase(request.getNombre())
                        && !categoriaActual.getIdCategoria().equals(categoria.getIdCategoria()));

        if (!mismoNombre && existe) {

            throw new DuplicateResourceException(
                    MessageConstants.CATEGORY_ALREADY_EXISTS
            );
        }
    }
}
