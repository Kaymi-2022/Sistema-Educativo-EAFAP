package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.evaluacion.NotaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;

import fap.SistemaGestionEducativa.mapper.evaluacion.NotaMapper;
import fap.SistemaGestionEducativa.model.evaluacion.Evaluacion;
import fap.SistemaGestionEducativa.model.evaluacion.Nota;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.CursoDiscenteRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.EvaluacionRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.NotaRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.NotaService;
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
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
    private final EvaluacionRepository evaluacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoDiscenteRepository cursoDiscenteRepository;
    private final NotaMapper mapper;

    /**
     * Registra una nota para un estudiante.
     */
    @Override
    public RestResponse<NotaResponse> registrar(
            NotaRequest request) {

        Evaluacion evaluacion = obtenerEvaluacion(request.getIdEvaluacion());

        Usuario estudiante = obtenerEstudiante(request.getIdDiscente());

        validarEvaluacionActiva(evaluacion);

        validarEstudianteActivo(estudiante);

        validarCalificacion(
                request.getCalificacion()
        );

        validarEstudianteMatriculado(
                evaluacion,
                estudiante
        );

        validarNotaDuplicada(
                evaluacion,
                estudiante
        );

        Nota nota = mapper.toEntity(request);

        nota.setEvaluacion(evaluacion);
        nota.setEstudiante(estudiante);
        nota.setCalificacion(request.getCalificacion());
        nota.setObservacion(request.getObservacion());
        nota.setEstado("Y");

        Nota notaGuardada = repository.save(nota);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.GRADE_CREATED,
                mapper.toResponse(notaGuardada)
        );
    }

    /**
     * Actualiza una nota existente.
     */
    @Override
    public RestResponse<NotaResponse> actualizar(
            Long idNota,
            NotaRequest request) {

        Nota nota = obtenerNota(idNota);

        Evaluacion evaluacion = obtenerEvaluacion(request.getIdEvaluacion());

        Usuario estudiante = obtenerEstudiante(request.getIdDiscente());

        validarNotaActiva(nota);

        validarEvaluacionActiva(evaluacion);

        validarEstudianteActivo(estudiante);

        validarCalificacion(
                request.getCalificacion()
        );

        validarEstudianteMatriculado(
                evaluacion,
                estudiante
        );

        validarNotaDuplicadaActualizar(
                nota,
                evaluacion,
                estudiante
        );

        nota.setEvaluacion(evaluacion);
        nota.setEstudiante(estudiante);
        nota.setCalificacion(request.getCalificacion());
        nota.setObservacion(request.getObservacion());

        Nota notaActualizada =
                repository.save(nota);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.GRADE_UPDATED,
                mapper.toResponse(notaActualizada)
        );
    }

    /**
     * Obtiene una nota por ID.
     */
    @Transactional(readOnly = true)
    @Override
    public RestResponse<NotaResponse> obtenerPorId(Long idNota) {

        Nota nota = obtenerNota(idNota);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponse(nota)
        );
    }

    /**
     * Lista todas las notas activas.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<NotaResponse>> listar() {

        List<NotaResponse> notas = repository.findAllByEstado("Y")
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                notas);
    }

    /**
     * Elimina lógicamente una nota.
     */
    @Override
    public RestResponse<Void> eliminar(Long idNota) {

        Nota nota =
                obtenerNota(idNota);

        validarNotaActiva(nota);

        nota.setEstado("N");

        repository.save(nota);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.GRADE_DELETED,
                null
        );
    }

    /**
     * Busca una nota por ID.
     */
    private Nota obtenerNota(Long idNota) {

        return repository.findById(idNota)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.GRADE_NOT_FOUND
                        ));
    }

    /**
     * Busca una evaluación por ID.
     */
    private Evaluacion obtenerEvaluacion(Long idEvaluacion) {

        return evaluacionRepository.findById(idEvaluacion)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.EVALUATION_NOT_FOUND
                        ));
    }

    /**
     * Busca al estudiante por ID.
     */
    private Usuario obtenerEstudiante(Long idDiscente) {

        return usuarioRepository.findById(idDiscente)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.STUDENT_NOT_FOUND
                        ));
    }

    /**
     * Valida que la evaluación esté activa.
     */
    private void validarEvaluacionActiva(Evaluacion evaluacion) {

        if (!"Y".equals(evaluacion.getEstado())) {

            throw new BusinessException(
                    MessageConstants.EVALUATION_INACTIVE
            );
        }
    }

    /**
     * Valida que el estudiante esté activo.
     */
    private void validarEstudianteActivo(Usuario estudiante) {

        if (!"Y".equals(estudiante.getEstado())) {

            throw new BusinessException(
                    MessageConstants.STUDENT_INACTIVE
            );
        }
    }

    /**
     * Valida el rango de calificación.
     */
    private void validarCalificacion(BigDecimal calificacion) {

        if (calificacion == null) {

            throw new BusinessException(
                    "La calificación es obligatoria."
            );
        }

        if (calificacion.compareTo(BigDecimal.ZERO) < 0
                || calificacion.compareTo(
                new BigDecimal("20")) > 0) {

            throw new BusinessException(
                    "La calificación debe estar entre 0 y 20."
            );
        }
    }

    /**
     * Verifica que el estudiante esté matriculado
     * en el curso de la evaluación.
     */
    private void validarEstudianteMatriculado(Evaluacion evaluacion, Usuario estudiante) {

        Long idCurso =
                evaluacion.getCurso().getIdCurso();

        boolean matriculado =
                cursoDiscenteRepository
                        .existsByCursoIdCursoAndEstudianteIdUsuarioAndEstado(
                                idCurso,
                                estudiante.getIdUsuario(),
                                "Y"
                        );

        if (!matriculado) {

            throw new BusinessException(
                    MessageConstants.STUDENT_NOT_ENROLLED
            );
        }
    }

    /**
     * Evita registrar dos notas activas
     * para el mismo estudiante y evaluación.
     */
    private void validarNotaDuplicada(
            Evaluacion evaluacion,
            Usuario estudiante) {

        boolean existe =
                repository
                        .existsByEvaluacionIdEvaluacionAndEstudianteIdUsuarioAndEstado(
                                evaluacion.getIdEvaluacion(),
                                estudiante.getIdUsuario(),
                                "Y"
                        );

        if (existe) {

            throw new DuplicateResourceException(
                    MessageConstants.GRADE_ALREADY_EXISTS
            );
        }
    }

    /**
     * Valida duplicidad durante una actualización,
     * excluyendo la nota que se está modificando.
     */
    private void validarNotaDuplicadaActualizar(
            Nota nota,
            Evaluacion evaluacion,
            Usuario estudiante) {

        boolean mismaNota = nota.getEvaluacion().getIdEvaluacion().equals(evaluacion.getIdEvaluacion()) &&
                nota.getEstudiante().getIdUsuario().equals(estudiante.getIdUsuario());

        if (!mismaNota && repository.existsByEvaluacionIdEvaluacionAndEstudianteIdUsuarioAndEstado(
                        evaluacion.getIdEvaluacion(),
                        estudiante.getIdUsuario(),
                        "Y"
                )) {

            throw new DuplicateResourceException(
                    MessageConstants.GRADE_ALREADY_EXISTS
            );
        }
    }

    /**
     * Valida que la nota esté activa.
     */
    private void validarNotaActiva(
            Nota nota) {

        if (!"Y".equals(nota.getEstado())) {

            throw new BusinessException(
                    MessageConstants.GRADE_INACTIVE
            );
        }
    }
}