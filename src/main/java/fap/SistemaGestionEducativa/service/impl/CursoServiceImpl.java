package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.CursoRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.CursoResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.DuplicateResourceException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.CursoMapper;
import fap.SistemaGestionEducativa.model.academico.Categoria;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.academico.PeriodoAcademico;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.CategoriaRepository;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.repository.academico.PeriodoAcademicoRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.CursoService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    private final CursoMapper mapper;

    @Override
    public RestResponse<CursoResponse> registrar(CursoRequest request) {

        Categoria categoria = obtenerCategoria(request.getIdCategoria());

        Usuario docente = obtenerDocente(request.getIdDocente());
        PeriodoAcademico periodoAcademico = obtenerPeriodoAcademico(request.getIdPeriodoAcademico());

        validarCategoriaActiva(categoria);
        validarDocenteActivo(docente);
        validarPeriodoActivo(periodoAcademico);
        validarCursoDuplicado(request);

        Curso curso = mapper.toEntity(request);

        curso.setCategoria(categoria);
        curso.setDocente(docente);
        curso.setPeriodoAcademico(periodoAcademico);
        curso.setEstado("Y");

        Curso cursoGuardado = repository.save(curso);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.COURSE_CREATED,
                mapper.toResponse(cursoGuardado)
        );
    }

    @Override
    public RestResponse<CursoResponse> actualizar(Long idCurso, CursoRequest request) {

        Curso curso = obtenerCurso(idCurso);

        Categoria categoria = obtenerCategoria(request.getIdCategoria());

        Usuario docente = obtenerDocente(request.getIdDocente());
        PeriodoAcademico periodoAcademico = obtenerPeriodoAcademico(request.getIdPeriodoAcademico());

        validarCategoriaActiva(categoria);
        validarDocenteActivo(docente);
        validarPeriodoActivo(periodoAcademico);

        validarCursoDuplicadoActualizar(curso, request);

        mapper.updateEntity(request, curso);

        curso.setCategoria(categoria);
        curso.setDocente(docente);
        curso.setPeriodoAcademico(periodoAcademico);

        Curso cursoActualizado = repository.save(curso);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.COURSE_UPDATED,
                mapper.toResponse(cursoActualizado)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<CursoResponse> obtenerPorId(Long idCurso) {

        Curso curso = obtenerCurso(idCurso);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponse(curso)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<CursoResponse>> listar() {

        List<Curso> cursos = repository.findAllByEstado("Y");

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponseList(cursos)
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idCurso) {

        Curso curso =
                obtenerCurso(idCurso);

        validarCursoActivo(curso);

        curso.setEstado("N");

        repository.save(curso);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.COURSE_DELETED,
                null
        );
    }

    private Curso obtenerCurso(Long idCurso) {

        return repository.findById(idCurso)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.COURSE_NOT_FOUND
                        ));
    }

    private Categoria obtenerCategoria(Long idCategoria) {

        return categoriaRepository
                .findById(idCategoria)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.CATEGORY_NOT_FOUND
                        ));
    }

    private Usuario obtenerDocente(Long idDocente) {

        return usuarioRepository
                .findById(idDocente)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.TEACHER_NOT_FOUND
                        ));
    }

    private PeriodoAcademico obtenerPeriodoAcademico(Long idPeriodoAcademico){
        return periodoAcademicoRepository
                .findById(idPeriodoAcademico)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageConstants.PERIOD_NOT_FOUND
                        ));
    }

    private void validarCategoriaActiva(Categoria categoria) {

        if (!"Y".equals(categoria.getEstado())) {

            throw new BusinessException(
                    MessageConstants.CATEGORY_INACTIVE
            );
        }
    }

    private void validarDocenteActivo(Usuario docente) {

        if (!"Y".equals(docente.getEstado())) {

            throw new BusinessException(
                    MessageConstants.TEACHER_INACTIVE
            );
        }
    }

    private void validarCursoActivo(Curso curso) {

        if (!"Y".equals(curso.getEstado())) {

            throw new BusinessException(
                    MessageConstants.COURSE_INACTIVE
            );
        }
    }

    private void validarCursoDuplicado(
            CursoRequest request) {

        PeriodoAcademico periodoAcademico = obtenerPeriodoAcademico(request.getIdPeriodoAcademico());

        if (repository.existsByNombreIgnoreCaseAndPeriodoAcademicoAndEstado(request.getNombre(), periodoAcademico, "Y")) {

            throw new DuplicateResourceException(
                    MessageConstants.COURSE_ALREADY_EXISTS
            );
        }
    }

    private void validarCursoDuplicadoActualizar(
            Curso curso,
            CursoRequest request) {

        PeriodoAcademico periodoAcademico = obtenerPeriodoAcademico(request.getIdPeriodoAcademico());

        boolean mismoCurso = curso.getNombre().equalsIgnoreCase(request.getNombre())
                && Objects.equals(curso.getPeriodoAcademico().getIdPeriodo(), request.getIdPeriodoAcademico());

        if (!mismoCurso
                && repository
                .existsByNombreIgnoreCaseAndPeriodoAcademicoAndEstado(request.getNombre(), periodoAcademico, "Y")) {

            throw new DuplicateResourceException(
                    MessageConstants.COURSE_ALREADY_EXISTS
            );
        }
    }

    private void validarPeriodoActivo(PeriodoAcademico periodoAcademico) {

        if (!"Y".equals(periodoAcademico.getEstado())) {

            throw new BusinessException(
                    MessageConstants.PERIOD_INACTIVE
            );
        }
    }
}