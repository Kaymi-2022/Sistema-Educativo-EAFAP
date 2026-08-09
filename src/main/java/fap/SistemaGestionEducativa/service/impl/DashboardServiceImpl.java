package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.dashboard.*;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.model.academico.Categoria;
import fap.SistemaGestionEducativa.model.evaluacion.Evaluacion;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.*;
import fap.SistemaGestionEducativa.repository.evaluacion.EvaluacionRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRolRepository;
import fap.SistemaGestionEducativa.service.dashboard.DashboardService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRolRepository usuarioRolRepository;
    private final CursoRepository cursoRepository;
    private final CursoDiscenteRepository cursoDiscenteRepository;
    private final EvaluacionRepository evaluacionRepository;
    private final CategoriaRepository categoriaRepository;
    private final HorarioRepository horarioRepository;

    @Override
    public RestResponse<DashboardResponse> obtenerDashboard() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                construirDashboard()
        );
    }

    @Override
    public RestResponse<DashboardResponse> obtenerIndicadores() {
        DashboardResponse dashboard = construirDashboard();
        dashboard.setCategorias(List.of());
        dashboard.setCursos(List.of());
        dashboard.setEvaluacionesPendientes(List.of());
        dashboard.setHorarioHoy(List.of());
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, dashboard);
    }

    @Override
    public RestResponse<List<CursoDashboardResponse>> obtenerCursosActivos() {
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapCursosActivos());
    }

    @Override
    public RestResponse<List<ResumenCategoriaResponse>> obtenerCursosPorCategoria() {
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapCursosPorCategoria());
    }

    @Override
    public RestResponse<List<EvaluacionPendienteResponse>> obtenerEvaluacionesPendientes() {
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapEvaluacionesPendientes());
    }

    @Override
    public RestResponse<List<HorarioHoyResponse>> obtenerHorarioHoy() {
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapHorarioHoy());
    }

    private DashboardResponse construirDashboard() {
        List<CursoDashboardResponse> cursos = mapCursosActivos();
        return DashboardResponse.builder()
                .totalUsuarios((long) usuarioRepository.findAllByEstado("Y").size())
                .totalDocentes(contarUsuariosPorRol("DOCENTE"))
                .totalEstudiantes(contarUsuariosPorRol("DISCENTE"))
                .totalCursos((long) cursos.size())
                .totalMatriculas((long) cursoDiscenteRepository.findAllByEstado("Y").size())
                .totalEvaluaciones((long) evaluacionRepository.findAllByEstado("Y").size())
                .categorias(mapCursosPorCategoria())
                .cursos(cursos)
                .evaluacionesPendientes(mapEvaluacionesPendientes())
                .horarioHoy(mapHorarioHoy())
                .build();
    }

    private Long contarUsuariosPorRol(String nombreRol) {
        Set<Long> ids = usuarioRolRepository.findAll().stream()
                .filter(ur -> ur.getUsuario() != null && "Y".equals(ur.getUsuario().getEstado()))
                .filter(ur -> ur.getRol() != null && ur.getRol().getNombreRol() != null && ur.getRol().getNombreRol().equalsIgnoreCase(nombreRol))
                .map(ur -> ur.getUsuario().getIdUsuario())
                .collect(Collectors.toSet());
        return (long) ids.size();
    }

    private List<CursoDashboardResponse> mapCursosActivos() {
        List<Curso> cursos = cursoRepository.findAllByEstado("Y");
        return cursos.stream()
                .map(curso -> CursoDashboardResponse.builder()
                        .idCurso(curso.getIdCurso())
                        .curso(curso.getNombre())
                        .categoria(curso.getCategoria() != null ? curso.getCategoria().getNombre() : null)
                        .docente(formatearNombre(curso.getDocente()))
                        .totalEstudiantes((int) cursoDiscenteRepository.findAllByCursoAndEstado(curso, "Y").size())
                        .build())
                .toList();
    }

    private List<ResumenCategoriaResponse> mapCursosPorCategoria() {
        List<Categoria> categorias = categoriaRepository.findAllByEstado("Y");
        List<Curso> cursos = cursoRepository.findAllByEstado("Y");
        return categorias.stream()
                .map(categoria -> ResumenCategoriaResponse.builder()
                        .categoria(categoria.getNombre())
                        .totalCursos((int) cursos.stream()
                                .filter(curso -> curso.getCategoria() != null
                                        && curso.getCategoria().getIdCategoria().equals(categoria.getIdCategoria()))
                                .count())
                        .build())
                .toList();
    }

    private List<EvaluacionPendienteResponse> mapEvaluacionesPendientes() {
        LocalDate hoy = LocalDate.now();
        return evaluacionRepository.findAllByEstado("Y").stream()
                .filter(evaluacion -> evaluacion.getFecha() != null && !evaluacion.getFecha().isBefore(hoy))
                .sorted(Comparator.comparing(Evaluacion::getFecha))
                .map(evaluacion -> EvaluacionPendienteResponse.builder()
                        .idEvaluacion(evaluacion.getIdEvaluacion())
                        .evaluacion(evaluacion.getNombre())
                        .curso(evaluacion.getCurso() != null ? evaluacion.getCurso().getNombre() : null)
                        .fecha(evaluacion.getFecha())
                        .docente(evaluacion.getCurso() != null ? formatearNombre(evaluacion.getCurso().getDocente()) : null)
                        .build())
                .toList();
    }

    private List<HorarioHoyResponse> mapHorarioHoy() {
        LocalDate hoy = LocalDate.now();
        return horarioRepository.findAllByEstado("Y").stream()
                .filter(horario -> hoy.equals(horario.getFecha()))
                .sorted(Comparator.comparing(h -> h.getBloqueHorario() != null ? h.getBloqueHorario().getHoraInicio() : ""))
                .map(horario -> HorarioHoyResponse.builder()
                        .hora(horario.getBloqueHorario() != null
                                ? horario.getBloqueHorario().getHoraInicio() + " - " + horario.getBloqueHorario().getHoraFin()
                                : null)
                        .curso(horario.getActividad() != null && horario.getActividad().getCurso() != null
                                ? horario.getActividad().getCurso().getNombre()
                                : null)
                        .aula(horario.getAula() != null ? horario.getAula().getNombre() : null)
                        .docente(formatearNombre(horario.getDocente()))
                        .build())
                .toList();
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
