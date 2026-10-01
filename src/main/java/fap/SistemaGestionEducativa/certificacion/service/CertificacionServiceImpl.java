package fap.SistemaGestionEducativa.certificacion.service;

import fap.SistemaGestionEducativa.certificacion.dto.*;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.NotaResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.evaluacion.ResultadoCurso;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.CursoDiscenteRepository;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.NotaRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.ResultadoCursoRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRolRepository;
import fap.SistemaGestionEducativa.security.authorization.CourseOwnershipValidator;
import fap.SistemaGestionEducativa.security.authentication.CurrentUserService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificacionServiceImpl implements CertificacionService {
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final CursoDiscenteRepository matriculaRepository;
    private final ResultadoCursoRepository resultadoRepository;
    private final NotaRepository notaRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final CurrentUserService currentUserService;
    private final CourseOwnershipValidator ownershipValidator;

    @Override
    public RestResponse<AcademicRecordResponse> obtenerRecord(Long idDiscente, Long idCurso) {
        Curso curso = cursoActivo(idCurso);
        Usuario estudiante = estudianteActivo(idDiscente);
        autorizarEstudiante(estudiante, curso);
        validarMatricula(estudiante, curso);
        ResultadoCurso resultado = resultadoActivo(estudiante, curso);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, record(estudiante, curso, resultado));
    }

    @Override
    public RestResponse<AcademicCertificateResponse> obtenerCertificado(Long idDiscente, Long idCurso) {
        Curso curso = cursoActivo(idCurso);
        Usuario estudiante = estudianteActivo(idDiscente);
        autorizarEstudiante(estudiante, curso);
        validarMatricula(estudiante, curso);
        ResultadoCurso resultado = resultadoActivo(estudiante, curso);
        if (!"APROBADO".equalsIgnoreCase(resultado.getEstadoAprobacion())) {
            throw new BusinessException("El estudiante no cuenta con un resultado aprobado para emitir el certificado.");
        }
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, AcademicCertificateResponse.builder()
                .idDiscente(estudiante.getIdUsuario()).estudiante(nombre(estudiante)).dni(estudiante.getDni())
                .idCurso(curso.getIdCurso()).curso(curso.getNombre()).periodo(curso.getPeriodoAcademico().getNombre())
                .fechaInicio(curso.getPeriodoAcademico().getFechaInicio()).fechaTermino(curso.getPeriodoAcademico().getFechaFin())
                .promedioFinal(resultado.getPromedioFinal()).estado("APROBADO").fechaEmision(LocalDate.now()).build());
    }

    @Override
    public RestResponse<AcademicMeritResponse> obtenerMerito(Long idCurso) {
        Curso curso = cursoActivo(idCurso);
        if (currentUserService.hasRole("DOCENTE") && !currentUserService.hasRole("ADMIN")) ownershipValidator.requireTeacherOwns(curso);
        if (!currentUserService.hasRole("ADMIN") && !currentUserService.hasRole("DOCENTE")) throw new AccessDeniedException("El ranking no está disponible para este rol.");
        List<ResultadoCurso> validos = resultadoRepository.findAllByEstado("Y").stream()
                .filter(r -> r.getCurso() != null && idCurso.equals(r.getCurso().getIdCurso()))
                .filter(r -> r.getEstudiante() != null && "Y".equals(r.getEstudiante().getEstado()))
                .filter(r -> "Y".equals(r.getCurso().getEstado()) && r.getPromedioFinal() != null)
                .filter(r -> "APROBADO".equalsIgnoreCase(r.getEstadoAprobacion()))
                .filter(r -> matriculaRepository.existsByCursoIdCursoAndEstudianteIdUsuarioAndEstado(idCurso, r.getEstudiante().getIdUsuario(), "Y"))
                .filter(r -> usuarioRolRepository.existsActiveRole(r.getEstudiante().getIdUsuario(), "DISCENTE"))
                .toList();
        if (validos.isEmpty()) throw new BusinessException("No existen resultados académicos válidos para determinar el mérito.");
        BigDecimal maximo = validos.stream().map(ResultadoCurso::getPromedioFinal).max(Comparator.naturalOrder()).orElseThrow();
        List<StudentMeritResponse> primeros = validos.stream().filter(r -> r.getPromedioFinal().compareTo(maximo) == 0)
                .map(r -> StudentMeritResponse.builder().idDiscente(r.getEstudiante().getIdUsuario()).estudiante(nombre(r.getEstudiante()))
                        .dni(r.getEstudiante().getDni()).promedioFinal(r.getPromedioFinal()).build()).toList();
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, AcademicMeritResponse.builder()
                .idCurso(curso.getIdCurso()).curso(curso.getNombre()).periodo(curso.getPeriodoAcademico().getNombre())
                .promedioMaximo(maximo).empate(primeros.size() > 1).estudiantesPrimerPuesto(primeros).build());
    }

    private AcademicRecordResponse record(Usuario e, Curso c, ResultadoCurso r) {
        List<NotaResponse> notas = notaRepository.findAllByEvaluacionCursoIdCursoAndEstudianteIdUsuarioAndEstado(c.getIdCurso(), e.getIdUsuario(), "Y")
                .stream().map(n -> NotaResponse.builder().idNota(n.getIdNota()).idEvaluacion(n.getEvaluacion().getIdEvaluacion())
                        .evaluacion(n.getEvaluacion().getNombre()).idDiscente(e.getIdUsuario()).discente(nombre(e))
                        .calificacion(n.getCalificacion()).observacion(n.getObservacion()).estado(n.getEstado()).build()).toList();
        return AcademicRecordResponse.builder().idDiscente(e.getIdUsuario()).estudiante(nombre(e)).dni(e.getDni()).idCurso(c.getIdCurso())
                .curso(c.getNombre()).periodo(c.getPeriodoAcademico().getNombre()).fechaInicio(c.getPeriodoAcademico().getFechaInicio())
                .fechaTermino(c.getPeriodoAcademico().getFechaFin()).evaluaciones(notas).promedioFinal(r.getPromedioFinal())
                .estadoFinal(r.getEstadoAprobacion()).build();
    }
    private Curso cursoActivo(Long id) { Curso c = cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.COURSE_NOT_FOUND)); if (!"Y".equals(c.getEstado())) throw new BusinessException(MessageConstants.COURSE_INACTIVE); return c; }
    private Usuario estudianteActivo(Long id) { Usuario e = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.STUDENT_NOT_FOUND)); if (!"Y".equals(e.getEstado()) || !usuarioRolRepository.existsActiveRole(id, "DISCENTE")) throw new BusinessException(MessageConstants.STUDENT_ROLE_REQUIRED); return e; }
    private ResultadoCurso resultadoActivo(Usuario e, Curso c) { return resultadoRepository.findByCursoIdCursoAndEstudianteIdUsuarioAndEstado(c.getIdCurso(), e.getIdUsuario(), "Y").filter(r -> r.getPromedioFinal() != null).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.RESULT_NOT_FOUND)); }
    private void validarMatricula(Usuario e, Curso c) { if (!matriculaRepository.existsByCursoIdCursoAndEstudianteIdUsuarioAndEstado(c.getIdCurso(), e.getIdUsuario(), "Y")) throw new BusinessException(MessageConstants.STUDENT_NOT_ENROLLED); }
    private void autorizarEstudiante(Usuario e, Curso c) { if (currentUserService.hasRole("ADMIN")) return; if (currentUserService.hasRole("DOCENTE")) { ownershipValidator.requireTeacherOwns(c); return; } currentUserService.requireOwnStudent(e.getIdUsuario()); }
    private String nombre(Usuario u) { return ((u.getNombres() == null ? "" : u.getNombres()) + " " + (u.getApellidos() == null ? "" : u.getApellidos())).trim(); }
}
