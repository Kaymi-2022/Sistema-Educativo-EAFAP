package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.SemanaAcademicaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.SemanaAcademicaResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.SemanaAcademicaMapper;
import fap.SistemaGestionEducativa.model.academico.SemanaAcademica;
import fap.SistemaGestionEducativa.repository.academico.SemanaAcademicaRepository;
import fap.SistemaGestionEducativa.service.business.SemanaAcademicaService;
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
public class SemanaAcademicaServiceImpl implements SemanaAcademicaService {

    private final SemanaAcademicaRepository repository;
    private final SemanaAcademicaMapper mapper;

    @Override
    public RestResponse<SemanaAcademicaResponse> registrar(SemanaAcademicaRequest request) {
        validarRangoFechas(request);
        validarDuplicado(request.getNumeroSemana(), null);

        SemanaAcademica semana = mapper.toEntity(request);
        semana.setEstado("Y");

        SemanaAcademica guardada = repository.save(semana);
        return ResponseBuilder.success(ApiConstants.CREATED, MessageConstants.WEEK_CREATED, mapper.toResponse(guardada));
    }

    @Override
    public RestResponse<SemanaAcademicaResponse> actualizar(Long idSemana, SemanaAcademicaRequest request) {
        SemanaAcademica semana = obtenerSemana(idSemana);
        validarRangoFechas(request);
        validarDuplicado(request.getNumeroSemana(), idSemana);

        semana.setNumeroSemana(request.getNumeroSemana());
        semana.setDescripcion(request.getDescripcion());
        semana.setFechaInicio(request.getFechaInicio());
        semana.setFechaFin(request.getFechaFin());

        SemanaAcademica actualizada = repository.save(semana);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.WEEK_UPDATED, mapper.toResponse(actualizada));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<SemanaAcademicaResponse>> listar() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByEstado("Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idSemana) {
        SemanaAcademica semana = obtenerSemana(idSemana);
        validarActivo(semana);
        semana.setEstado("N");
        repository.save(semana);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.WEEK_DELETED, null);
    }

    private SemanaAcademica obtenerSemana(Long idSemana) {
        return repository.findById(idSemana)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.WEEK_NOT_FOUND));
    }

    private void validarDuplicado(Integer numeroSemana, Long idSemanaActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(semana -> semana.getNumeroSemana() != null
                        && semana.getNumeroSemana().equals(numeroSemana)
                        && (idSemanaActual == null || !semana.getIdSemana().equals(idSemanaActual)));
        if (existe) {
            throw new BusinessException(MessageConstants.WEEK_ALREADY_EXISTS);
        }
    }

    private void validarRangoFechas(SemanaAcademicaRequest request) {
        if (request.getFechaInicio().isAfter(request.getFechaFin())) {
            throw new BusinessException(MessageConstants.WEEK_INVALID_RANGE);
        }
    }

    private void validarActivo(SemanaAcademica semana) {
        if (!"Y".equals(semana.getEstado())) {
            throw new BusinessException(MessageConstants.WEEK_INACTIVE);
        }
    }
}
