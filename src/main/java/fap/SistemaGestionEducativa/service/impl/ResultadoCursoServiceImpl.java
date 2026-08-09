package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.evaluacion.ResultadoCursoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.evaluacion.ResultadoCursoMapper;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.evaluacion.Nota;
import fap.SistemaGestionEducativa.model.evaluacion.ResultadoCurso;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.CursoDiscenteRepository;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.NotaRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.ResultadoCursoRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.ResultadoCursoService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResultadoCursoServiceImpl implements ResultadoCursoService {

    private final ResultadoCursoRepository repository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoDiscenteRepository cursoDiscenteRepository;
    private final NotaRepository notaRepository;
    private final ResultadoCursoMapper mapper;

    /**
     * Genera el resultado final de un estudiante
     * para un determinado curso.
     */
    @Override
    public RestResponse<ResultadoCursoResponse> registrar(ResultadoCursoRequest request) {

        Curso curso =
                obtenerCurso(request.getIdCurso());

        Usuario estudiante =
                obtenerEstudiante(request.getIdDiscente());

        validarCursoActivo(curso);

        validarEstudianteActivo(estudiante);

        validarMatricula(
                curso,
                estudiante
        );

        validarResultadoNoExiste(
                curso,
                estudiante
        );

        List<Nota> notas =
                obtenerNotas(
                        curso,
                        estudiante
                );

        validarNotas(notas);

        BigDecimal promedioFinal =
                calcularPromedioPonderado(notas);

        String estadoAprobacion =
                determinarEstadoAprobacion(
                        promedioFinal
                );

        ResultadoCurso resultado = mapper.toEntity(request);

        resultado.setCurso(curso);
        resultado.setEstudiante(estudiante);
        resultado.setPromedioFinal(promedioFinal);
        resultado.setEstadoAprobacion(estadoAprobacion);
        resultado.setFechaCierre(LocalDate.now());
        resultado.setEstado("Y");

        ResultadoCurso resultadoGuardado = repository.save(resultado);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.RESULT_CREATED,
                mapper.toResponse(resultadoGuardado)
        );
    }

    /**
     * Obtiene el resultado por ID.
     */
    @Transactional(readOnly = true)
    @Override
    public RestResponse<ResultadoCursoResponse> obtenerPorId(Long idResultado) {

        ResultadoCurso resultado = obtenerResultado(idResultado);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponse(resultado)
        );
    }

    /**
     * Obtiene el resultado de un estudiante
     * para un curso determinado.
     */
    @Override
    public RestResponse<ResultadoCursoResponse> obtenerPorCursoxDiscente(Long idCurso, Long idDiscente) {

        ResultadoCurso resultado =
                repository.findByCursoIdCursoAndEstudianteIdUsuarioAndEstado(idCurso, idDiscente, "Y")
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        MessageConstants.RESULT_NOT_FOUND
                                ));

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponse(resultado)
        );
    }

    /**
     * Lista todos los resultados activos.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<ResultadoCursoResponse>> listar() {

        List<ResultadoCursoResponse> resultados =repository.findAllByEstado("Y")
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                resultados);
    }

    /**
     * Elimina lógicamente un resultado.
     */
    @Override
    public RestResponse<Void> eliminar(Long idResultado) {

        ResultadoCurso resultado =
                obtenerResultado(idResultado);

        validarResultadoActivo(resultado);

        resultado.setEstado("N");

        repository.save(resultado);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.RESULT_DELETED,
                null
        );
    }

    /**
     * Busca un resultado por ID.
     */
    private ResultadoCurso obtenerResultado(Long idResultado) {

        return repository.findById(idResultado)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.RESULT_NOT_FOUND
                        ));
    }

    /**
     * Busca el curso.
     */
    private Curso obtenerCurso(Long idCurso) {

        return cursoRepository.findById(idCurso)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.COURSE_NOT_FOUND
                        ));
    }

    /**
     * Busca al estudiante.
     */
    private Usuario obtenerEstudiante(Long idDiscente) {

        return usuarioRepository.findById(idDiscente)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.STUDENT_NOT_FOUND
                        ));
    }

    /**
     * Valida que el curso esté activo.
     */
    private void validarCursoActivo(Curso curso) {

        if (!"Y".equals(curso.getEstado())) {

            throw new BusinessException(
                    MessageConstants.COURSE_INACTIVE
            );
        }
    }

    /**
     * Valida que el estudiante esté activo.
     */
    private void validarEstudianteActivo(
            Usuario estudiante) {

        if (!"Y".equals(estudiante.getEstado())) {

            throw new BusinessException(
                    MessageConstants.STUDENT_INACTIVE
            );
        }
    }

    /**
     * Verifica que el estudiante esté matriculado
     * en el curso.
     */
    private void validarMatricula(
            Curso curso,
            Usuario estudiante) {

        boolean matriculado =
                cursoDiscenteRepository
                        .existsByCursoIdCursoAndEstudianteIdUsuarioAndEstado(
                                curso.getIdCurso(),
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
     * Verifica que todavía no exista
     * un resultado para el estudiante y curso.
     */
    private void validarResultadoNoExiste(
            Curso curso,
            Usuario estudiante) {

        boolean existe =
                repository
                        .existsByCursoIdCursoAndEstudianteIdUsuarioAndEstado(
                                curso.getIdCurso(),
                                estudiante.getIdUsuario(),
                                "Y"
                        );

        if (existe) {

            throw new DuplicateResourceException(
                    MessageConstants.RESULT_ALREADY_EXISTS
            );
        }
    }

    /**
     * Obtiene las notas del estudiante
     * correspondientes al curso.
     */
    private List<Nota> obtenerNotas(
            Curso curso,
            Usuario estudiante) {

        return notaRepository
                .findAllByEvaluacionCursoIdCursoAndEstudianteIdUsuarioAndEstado(
                        curso.getIdCurso(),
                        estudiante.getIdUsuario(),
                        "Y"
                );
    }

    /**
     * Valida que existan notas antes
     * de cerrar el resultado.
     */
    private void validarNotas(
            List<Nota> notas) {

        if (notas == null || notas.isEmpty()) {

            throw new BusinessException(
                    MessageConstants.NO_GRADES_TO_CALCULATE
            );
        }
    }

    /**
     * Calcula el promedio ponderado.
     *
     * Formula:
     *
     * Σ (calificación × peso) / Σ peso
     */
    private BigDecimal calcularPromedioPonderado(
            List<Nota> notas) {

        BigDecimal sumaPonderada =
                BigDecimal.ZERO;

        BigDecimal sumaPesos =
                BigDecimal.ZERO;

        for (Nota nota : notas) {

            if (nota.getEvaluacion() == null
                    || nota.getEvaluacion().getPeso() == null
                    || nota.getCalificacion() == null) {

                throw new BusinessException(
                        MessageConstants.INVALID_GRADE_DATA
                );
            }

            BigDecimal peso =
                    nota.getEvaluacion().getPeso();

            BigDecimal calificacion =
                    nota.getCalificacion();

            sumaPonderada =
                    sumaPonderada.add(
                            calificacion.multiply(peso)
                    );

            sumaPesos =
                    sumaPesos.add(peso);
        }

        if (sumaPesos.compareTo(BigDecimal.ZERO) <= 0) {

            throw new BusinessException(
                    MessageConstants.INVALID_EVALUATION_WEIGHT
            );
        }

        return sumaPonderada
                .divide(
                        sumaPesos,
                        2,
                        RoundingMode.HALF_UP
                );
    }

    /**
     * Determina si el estudiante aprobó.
     *
     * En este ejemplo se considera aprobado
     * con promedio >= 11.
     */
    private String determinarEstadoAprobacion(
            BigDecimal promedioFinal) {

        if (promedioFinal.compareTo(
                new BigDecimal("11")) >= 0) {

            return "A";
        }

        return "D";
    }

    /**
     * Valida que el resultado esté activo.
     */
    private void validarResultadoActivo(
            ResultadoCurso resultado) {

        if (!"Y".equals(resultado.getEstado())) {

            throw new BusinessException(
                    MessageConstants.RESULT_INACTIVE
            );
        }
    }
}