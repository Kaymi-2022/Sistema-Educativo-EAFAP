package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.MatriculaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoDiscenteResponse;
import fap.SistemaGestionEducativa.mapper.academico.CursoDiscenteMapper;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.academico.CursoDiscente;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.CursoDiscenteRepository;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.CursoDiscenteService;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CursoDiscenteServiceImpl implements CursoDiscenteService {

    private final CursoDiscenteRepository repository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoDiscenteMapper mapper;

    /**
     * Registra la matrícula de un estudiante en un curso.
     */
    @Override
    public RestResponse<CursoDiscenteResponse> registrar(MatriculaRequest request) {

        Curso curso = obtenerCurso(request.getIdCurso());

        Usuario estudiante = obtenerEstudiante(request.getIdDiscente());

        validarCursoActivo(curso);

        validarEstudianteActivo(estudiante);

        validarMatriculaDuplicada(curso, estudiante);

        CursoDiscente matricula = mapper.toEntity(request);

        matricula.setCurso(curso);
        matricula.setEstudiante(estudiante);
        matricula.setFechaMatricula(LocalDate.now());
        matricula.setEstado("Y");

        CursoDiscente matriculaGuardada = repository.save(matricula);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.ENROLLMENT_CREATED,
                mapper.toResponse(matriculaGuardada)
        );
    }

    /**
     * Obtiene una matrícula por ID.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<CursoDiscenteResponse> obtenerPorId(Long idCursoDiscente) {

        CursoDiscente matricula = obtenerMatricula(idCursoDiscente);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponse(matricula)
        );
    }

    /**
     * Lista todas las matrículas activas.
     */
    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<CursoDiscenteResponse>> listar() {

        List<CursoDiscente> matriculas = repository.findAllByEstado("Y");

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponseList(matriculas)
        );
    }

    /**
     * Elimina lógicamente una matrícula.
     */
    @Override
    public RestResponse<Void> eliminar(Long idCursoDiscente) {

        CursoDiscente matricula = obtenerMatricula(idCursoDiscente);

        validarMatriculaActiva(matricula);

        matricula.setEstado("N");

        repository.save(matricula);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.ENROLLMENT_DELETED,
                null
        );
    }

    /**
     * Obtiene el curso.
     */
    private Curso obtenerCurso(Long idCurso) {

        return cursoRepository.findById(idCurso)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.COURSE_NOT_FOUND
                        ));
    }

    /**
     * Obtiene el estudiante.
     */
    private Usuario obtenerEstudiante(Long idDiscente) {

        return usuarioRepository.findById(idDiscente)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.STUDENT_NOT_FOUND
                        ));
    }

    /**
     * Obtiene una matrícula.
     */
    private CursoDiscente obtenerMatricula(Long idCursoDiscente) {

        return repository.findById(idCursoDiscente)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.ENROLLMENT_NOT_FOUND
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
    private void validarEstudianteActivo(Usuario estudiante) {

        if (!"Y".equals(estudiante.getEstado())) {

            throw new BusinessException(
                    MessageConstants.STUDENT_INACTIVE
            );
        }
    }

    /**
     * Verifica que el estudiante no esté
     * matriculado previamente en el curso.
     */
    private void validarMatriculaDuplicada(Curso curso, Usuario estudiante) {

        if (repository.existsByCursoAndEstudianteAndEstado(
                curso,
                estudiante,
                "Y")) {

            throw new DuplicateResourceException(
                    MessageConstants.ENROLLMENT_ALREADY_EXISTS
            );
        }
    }

    /**
     * Valida que la matrícula esté activa.
     */
    private void validarMatriculaActiva(CursoDiscente matricula) {

        if (!"Y".equals(matricula.getEstado())) {

            throw new BusinessException(
                    MessageConstants.ENROLLMENT_INACTIVE
            );
        }
    }
}