package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.evaluacion.ResultadoCursoResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.CursoReporteResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.NotaDetalleResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.PromedioGeneralResponse;
import fap.SistemaGestionEducativa.dto.response.reporte.ReporteAcademicoResponse;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.evaluacion.Nota;
import fap.SistemaGestionEducativa.model.evaluacion.ResultadoCurso;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.NotaRepository;
import fap.SistemaGestionEducativa.repository.evaluacion.ResultadoCursoRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.reporte.ReporteService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final NotaRepository notaRepository;
    private final ResultadoCursoRepository resultadoCursoRepository;

    @Override
    public RestResponse<ReporteAcademicoResponse> obtenerHistorialAcademico(String codigoEstudiante) {
        Usuario estudiante = obtenerEstudiante(codigoEstudiante);
        List<CursoReporteResponse> cursos = obtenerCursosDelEstudiante(estudiante);
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                ReporteAcademicoResponse.builder()
                        .codigoEstudiante(codigoEstudiante)
                        .dni(estudiante.getDni())
                        .estudiante(formatearNombre(estudiante))
                        .email(estudiante.getEmail())
                        .cursos(cursos)
                        .promedioGeneral(calcularPromedioGeneral(cursos))
                        .totalCursos(cursos.size())
                        .cursosAprobados(contarAprobados(cursos))
                        .cursosDesaprobados(contarDesaprobados(cursos))
                        .build()
        );
    }

    @Override
    public RestResponse<List<NotaDetalleResponse>> obtenerNotas(String codigoEstudiante) {
        Usuario estudiante = obtenerEstudiante(codigoEstudiante);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapNotas(estudiante));
    }

    @Override
    public RestResponse<List<ResultadoCursoResponse>> obtenerResultados(String codigoEstudiante) {
        Usuario estudiante = obtenerEstudiante(codigoEstudiante);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapResultados(estudiante));
    }

    @Override
    public RestResponse<PromedioGeneralResponse> obtenerPromedioGeneral(String codigoEstudiante) {
        Usuario estudiante = obtenerEstudiante(codigoEstudiante);
        List<CursoReporteResponse> cursos = obtenerCursosDelEstudiante(estudiante);
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                PromedioGeneralResponse.builder()
                        .codigoEstudiante(codigoEstudiante)
                        .estudiante(formatearNombre(estudiante))
                        .promedioGeneral(calcularPromedioGeneral(cursos))
                        .cursosAprobados(contarAprobados(cursos))
                        .cursosDesaprobados(contarDesaprobados(cursos))
                        .build()
        );
    }

    @Override
    public RestResponse<CursoReporteResponse> obtenerCurso(String codigoEstudiante, Long idCurso) {
        Usuario estudiante = obtenerEstudiante(codigoEstudiante);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, construirCursoReporte(estudiante, idCurso));
    }

    private Usuario obtenerEstudiante(String codigoEstudiante) {
        Optional<Usuario> usuario = usuarioRepository.findByUsernameIgnoreCase(codigoEstudiante);
        if (usuario.isEmpty()) {
            usuario = usuarioRepository.findByDni(codigoEstudiante);
        }
        return usuario.orElseThrow(() -> new ResourceNotFoundException(MessageConstants.STUDENT_NOT_FOUND));
    }

    private List<ResultadoCursoResponse> mapResultados(Usuario estudiante) {
        return resultadoCursoRepository.findAllByEstudianteIdUsuarioAndEstado(estudiante.getIdUsuario(), "Y").stream()
                .map(this::toResultadoResponse)
                .toList();
    }

    private ResultadoCursoResponse toResultadoResponse(ResultadoCurso resultado) {
        return ResultadoCursoResponse.builder()
                .idResultado(resultado.getIdResultado())
                .idCurso(resultado.getCurso() != null ? resultado.getCurso().getIdCurso() : null)
                .curso(resultado.getCurso() != null ? resultado.getCurso().getNombre() : null)
                .idDiscente(resultado.getEstudiante() != null ? resultado.getEstudiante().getIdUsuario() : null)
                .discente(formatearNombre(resultado.getEstudiante()))
                .promedioFinal(resultado.getPromedioFinal())
                .estadoAprobacion(resultado.getEstadoAprobacion())
                .fechaCierre(resultado.getFechaCierre())
                .estado(resultado.getEstado())
                .build();
    }

    private List<NotaDetalleResponse> mapNotas(Usuario estudiante) {
        return notaRepository.findAllByEstudianteIdUsuarioAndEstado(estudiante.getIdUsuario(), "Y").stream()
                .map(this::toNotaDetalleResponse)
                .toList();
    }

    private NotaDetalleResponse toNotaDetalleResponse(Nota nota) {
        return NotaDetalleResponse.builder()
                .idEvaluacion(nota.getEvaluacion() != null ? nota.getEvaluacion().getIdEvaluacion() : null)
                .evaluacion(nota.getEvaluacion() != null ? nota.getEvaluacion().getNombre() : null)
                .tipo(nota.getEvaluacion() != null ? nota.getEvaluacion().getTipo() : null)
                .peso(nota.getEvaluacion() != null && nota.getEvaluacion().getPeso() != null ? nota.getEvaluacion().getPeso().intValue() : 0)
                .fecha(nota.getEvaluacion() != null ? nota.getEvaluacion().getFecha() : null)
                .nota(nota.getCalificacion())
                .observacion(nota.getObservacion())
                .build();
    }

    private List<CursoReporteResponse> obtenerCursosDelEstudiante(Usuario estudiante) {
        return resultadoCursoRepository.findAllByEstudianteIdUsuarioAndEstado(estudiante.getIdUsuario(), "Y").stream()
                .map(resultado -> construirCursoReporte(estudiante, resultado.getCurso().getIdCurso()))
                .toList();
    }

    private CursoReporteResponse construirCursoReporte(Usuario estudiante, Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.COURSE_NOT_FOUND));

        List<NotaDetalleResponse> evaluaciones = notaRepository
                .findAllByEvaluacionCursoIdCursoAndEstudianteIdUsuarioAndEstado(idCurso, estudiante.getIdUsuario(), "Y")
                .stream()
                .map(this::toNotaDetalleResponse)
                .toList();

        Optional<ResultadoCurso> resultado = resultadoCursoRepository
                .findByCursoIdCursoAndEstudianteIdUsuarioAndEstado(idCurso, estudiante.getIdUsuario(), "Y");

        return CursoReporteResponse.builder()
                .idCurso(curso.getIdCurso())
                .curso(curso.getNombre())
                .categoria(curso.getCategoria() != null ? curso.getCategoria().getNombre() : null)
                .docente(curso.getDocente() != null ? formatearNombre(curso.getDocente()) : null)
                .periodoAcademico(curso.getPeriodoAcademico() != null ? curso.getPeriodoAcademico().getNombre() : null)
                .promedioFinal(resultado.map(ResultadoCurso::getPromedioFinal).orElse(null))
                .estadoAprobacion(resultado.map(ResultadoCurso::getEstadoAprobacion).orElse(null))
                .evaluaciones(evaluaciones)
                .build();
    }

    private BigDecimal calcularPromedioGeneral(List<CursoReporteResponse> cursos) {
        if (cursos.isEmpty()) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal suma = BigDecimal.ZERO;
        int cantidad = 0;
        for (CursoReporteResponse curso : cursos) {
            if (curso.getPromedioFinal() != null) {
                suma = suma.add(curso.getPromedioFinal());
                cantidad++;
            }
        }
        if (cantidad == 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return suma.divide(BigDecimal.valueOf(cantidad), 2, RoundingMode.HALF_UP);
    }

    private int contarAprobados(List<CursoReporteResponse> cursos) {
        return (int) cursos.stream().filter(curso -> "A".equalsIgnoreCase(curso.getEstadoAprobacion())).count();
    }

    private int contarDesaprobados(List<CursoReporteResponse> cursos) {
        return (int) cursos.stream().filter(curso -> "D".equalsIgnoreCase(curso.getEstadoAprobacion())).count();
    }

    private String formatearNombre(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        String nombres = usuario.getNombres() != null ? usuario.getNombres() : "";
        String apellidos = usuario.getApellidos() != null ? usuario.getApellidos() : "";
        return (nombres + " " + apellidos).trim();
    }
}
