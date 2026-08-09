package fap.SistemaGestionEducativa.service.impl;


import fap.SistemaGestionEducativa.dto.request.evaluacion.EvaluacionRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.EvaluacionResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.evaluacion.EvaluacionMapper;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.evaluacion.Evaluacion;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.EvaluacionRepository;
import fap.SistemaGestionEducativa.service.business.EvaluacionService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository repository;
    private final CursoRepository cursoRepository;
    private final EvaluacionMapper mapper;

    /**
     * Registra una evaluación.
     */
    @Override
    public RestResponse<EvaluacionResponse> registrar(EvaluacionRequest request) {

        Curso curso = obtenerCurso(request.getIdCurso());

        validarCursoActivo(curso);

        validarEvaluacionDuplicada(request);

        Evaluacion evaluacion = mapper.toEntity(request);

        evaluacion.setCurso(curso);
        evaluacion.setEstado("Y");

        Evaluacion evaluacionGuardada = repository.save(evaluacion);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.EVALUATION_CREATED,
                mapper.toResponse(evaluacionGuardada)
        );
    }

    /**
     * Actualiza una evaluación.
     */
    @Override
    public RestResponse<EvaluacionResponse> actualizar(
            Long idEvaluacion,
            EvaluacionRequest request) {

        Evaluacion evaluacion =
                obtenerEvaluacion(idEvaluacion);

        Curso curso =
                obtenerCurso(request.getIdCurso());

        validarCursoActivo(curso);

        validarEvaluacionDuplicadaActualizar(
                evaluacion,
                request
        );

        evaluacion.setNombre(request.getNombre());
        evaluacion.setTipo(request.getTipo());
        evaluacion.setPeso(BigDecimal.valueOf(request.getPeso()));
        evaluacion.setFecha(request.getFecha());
        evaluacion.setCurso(curso);

        Evaluacion evaluacionActualizada =
                repository.save(evaluacion);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.EVALUATION_UPDATED,
                mapper.toResponse(evaluacionActualizada)
        );
    }

    /**
     * Obtiene una evaluación por ID.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<EvaluacionResponse> obtenerPorId(Long idEvaluacion) {

        Evaluacion evaluacion = obtenerEvaluacion(idEvaluacion);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponse(evaluacion)
        );
    }

    /**
     * Lista evaluaciones activas.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<EvaluacionResponse>> listar() {

        List<EvaluacionResponse> evaluaciones = repository.findAllByEstado("Y")
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                evaluaciones);
    }

    /**
     * Elimina lógicamente una evaluación.
     */
    @Override
    public RestResponse<Void> eliminar(
            Long idEvaluacion) {

        Evaluacion evaluacion =
                obtenerEvaluacion(idEvaluacion);

        validarEvaluacionActiva(evaluacion);

        evaluacion.setEstado("N");

        repository.save(evaluacion);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.EVALUATION_DELETED,
                null
        );
    }

    private Evaluacion obtenerEvaluacion(
            Long idEvaluacion) {

        return repository.findById(idEvaluacion)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.EVALUATION_NOT_FOUND
                        ));
    }

    private Curso obtenerCurso(
            Long idCurso) {

        return cursoRepository.findById(idCurso)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.COURSE_NOT_FOUND
                        ));
    }

    private void validarCursoActivo(
            Curso curso) {

        if (!"Y".equals(curso.getEstado())) {

            throw new BusinessException(
                    MessageConstants.COURSE_INACTIVE
            );
        }
    }


    private void validarEvaluacionDuplicada(
            EvaluacionRequest request) {

        if (repository
                .existsByCursoIdCursoAndNombreIgnoreCaseAndEstado(
                        request.getIdCurso(),
                        request.getNombre(),
                        "Y")) {

            throw new DuplicateResourceException(
                    MessageConstants.EVALUATION_ALREADY_EXISTS
            );
        }
    }

    private void validarEvaluacionDuplicadaActualizar(
            Evaluacion evaluacion,
            EvaluacionRequest request) {

        boolean mismaEvaluacion =
                evaluacion.getNombre()
                        .equalsIgnoreCase(request.getNombre())
                        && evaluacion.getCurso()
                        .getIdCurso()
                        .equals(request.getIdCurso());

        if (!mismaEvaluacion
                && repository
                .existsByCursoIdCursoAndNombreIgnoreCaseAndEstado(
                        request.getIdCurso(),
                        request.getNombre(),
                        "Y")) {

            throw new DuplicateResourceException(
                    MessageConstants.EVALUATION_ALREADY_EXISTS
            );
        }
    }

    private void validarEvaluacionActiva(
            Evaluacion evaluacion) {

        if (!"Y".equals(evaluacion.getEstado())) {

            throw new BusinessException(
                    MessageConstants.EVALUATION_INACTIVE
            );
        }
    }
}