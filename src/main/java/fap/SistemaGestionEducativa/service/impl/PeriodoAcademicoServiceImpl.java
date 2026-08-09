package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.PeriodoAcademicoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.PeriodoAcademicoResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.PeriodoAcademicoMapper;
import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import fap.SistemaGestionEducativa.repository.academico.PeriodoAcademicoRepository;
import fap.SistemaGestionEducativa.service.business.PeriodoAcademicoService;
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
public class PeriodoAcademicoServiceImpl implements PeriodoAcademicoService {

    private final PeriodoAcademicoRepository repository;
    private final PeriodoAcademicoMapper mapper;

    @Override
    public RestResponse<PeriodoAcademicoResponse> registrar(PeriodoAcademicoRequest request) {
        validarRangoFechas(request);
        validarDuplicado(request.getNombre(), null);

        PeriodoAcademico periodo = mapper.toEntity(request);
        periodo.setEstado("Y");

        PeriodoAcademico guardado = repository.save(periodo);
        return ResponseBuilder.success(ApiConstants.CREATED, MessageConstants.PERIOD_CREATED, mapper.toResponse(guardado));
    }

    @Override
    public RestResponse<PeriodoAcademicoResponse> actualizar(Long idPeriodo, PeriodoAcademicoRequest request) {
        PeriodoAcademico periodo = obtenerPeriodo(idPeriodo);
        validarRangoFechas(request);
        validarDuplicado(request.getNombre(), idPeriodo);

        periodo.setNombre(request.getNombre());
        periodo.setFechaInicio(request.getFechaInicio());
        periodo.setFechaFin(request.getFechaFin());

        PeriodoAcademico actualizado = repository.save(periodo);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.PERIOD_UPDATED, mapper.toResponse(actualizado));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<PeriodoAcademicoResponse> obtenerPorId(Long idPeriodo) {
        PeriodoAcademico periodo = obtenerPeriodo(idPeriodo);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapper.toResponse(periodo));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<PeriodoAcademicoResponse>> listar() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponseList(repository.findAllByEstado("Y"))
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idPeriodo) {
        PeriodoAcademico periodo = obtenerPeriodo(idPeriodo);
        validarActivo(periodo);
        periodo.setEstado("N");
        repository.save(periodo);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.PERIOD_DELETED, null);
    }

    private PeriodoAcademico obtenerPeriodo(Long idPeriodo) {
        return repository.findById(idPeriodo)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.PERIOD_NOT_FOUND));
    }

    private void validarDuplicado(String nombre, Long idPeriodoActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(periodo -> periodo.getNombre() != null
                        && periodo.getNombre().equalsIgnoreCase(nombre)
                        && (idPeriodoActual == null || !periodo.getIdPeriodo().equals(idPeriodoActual)));
        if (existe) {
            throw new BusinessException(MessageConstants.PERIOD_ALREADY_EXISTS);
        }
    }

    private void validarRangoFechas(PeriodoAcademicoRequest request) {
        if (request.getFechaInicio().isAfter(request.getFechaFin())) {
            throw new BusinessException(MessageConstants.PERIOD_INVALID_RANGE);
        }
    }

    private void validarActivo(PeriodoAcademico periodo) {
        if (!"Y".equals(periodo.getEstado())) {
            throw new BusinessException(MessageConstants.PERIOD_INACTIVE);
        }
    }
}
