package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.HorarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.HorarioResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.HorarioMapper;
import fap.SistemaGestionEducativa.model.academico.*;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.academico.*;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.HorarioService;
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
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository repository;
    private final SemanaAcademicaRepository semanaRepository;
    private final AulaRepository aulaRepository;
    private final BloqueHorarioRepository bloqueRepository;
    private final ActividadRepository actividadRepository;
    private final UsuarioRepository usuarioRepository;
    private final HorarioMapper mapper;

    @Override
    public RestResponse<HorarioResponse> registrar(HorarioRequest request) {
        SemanaAcademica semana = obtenerSemanaActiva(request.getIdSemana());
        Aula aula = obtenerAulaActiva(request.getIdAula());
        BloqueHorario bloque = obtenerBloqueActivo(request.getIdBloque());
        Actividad actividad = obtenerActividadActiva(request.getIdActividad());
        Usuario docente = obtenerDocenteActivo(request.getIdInstructor());

        validarConflictoHorario(request.getFecha(), bloque.getIdBloque(), aula.getIdAula(), docente.getIdUsuario(), null);

        Horario horario = mapper.toEntity(request);
        horario.setSemanaAcademica(semana);
        horario.setAula(aula);
        horario.setBloqueHorario(bloque);
        horario.setActividad(actividad);
        horario.setDocente(docente);
        horario.setEstado("Y");

        Horario guardado = repository.save(horario);
        return ResponseBuilder.success(ApiConstants.CREATED, MessageConstants.SCHEDULE_CREATED, mapper.toResponse(guardado));
    }

    @Override
    public RestResponse<HorarioResponse> actualizar(Long idHorario, HorarioRequest request) {
        Horario horario = obtenerHorario(idHorario);

        SemanaAcademica semana = obtenerSemanaActiva(request.getIdSemana());
        Aula aula = obtenerAulaActiva(request.getIdAula());
        BloqueHorario bloque = obtenerBloqueActivo(request.getIdBloque());
        Actividad actividad = obtenerActividadActiva(request.getIdActividad());
        Usuario docente = obtenerDocenteActivo(request.getIdInstructor());

        if (!Objects.equals(horario.getFecha(), request.getFecha())
                || !Objects.equals(horario.getBloqueHorario().getIdBloque(), bloque.getIdBloque())
                || !Objects.equals(horario.getAula().getIdAula(), aula.getIdAula())) {
            validarConflictoAula(request.getFecha(), bloque.getIdBloque(), aula.getIdAula(), idHorario);
        }

        if (!Objects.equals(horario.getFecha(), request.getFecha())
                || !Objects.equals(horario.getBloqueHorario().getIdBloque(), bloque.getIdBloque())
                || !Objects.equals(horario.getDocente().getIdUsuario(), docente.getIdUsuario())) {
            validarConflictoDocente(request.getFecha(), bloque.getIdBloque(), docente.getIdUsuario(), idHorario);
        }

        horario.setDiaSemana(request.getDiaSemana());
        horario.setFecha(request.getFecha());
        horario.setSemanaAcademica(semana);
        horario.setAula(aula);
        horario.setBloqueHorario(bloque);
        horario.setActividad(actividad);
        horario.setDocente(docente);

        Horario actualizado = repository.save(horario);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SCHEDULE_UPDATED, mapper.toResponse(actualizado));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<HorarioResponse> obtenerPorId(Long idHorario) {
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapper.toResponse(obtenerHorario(idHorario)));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<HorarioResponse>> listar() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByEstado("Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<HorarioResponse>> listarPorDocente(Long idDocente) {
        obtenerDocenteActivo(idDocente);
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByDocenteIdUsuarioAndEstado(idDocente, "Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<HorarioResponse>> listarPorCurso(Long idCurso) {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByActividadCursoIdCursoAndEstado(idCurso, "Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<HorarioResponse>> listarPorSemana(Long idSemana) {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllBySemanaAcademicaIdSemanaAndEstado(idSemana, "Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idHorario) {
        Horario horario = obtenerHorario(idHorario);
        validarActivo(horario);
        horario.setEstado("N");
        repository.save(horario);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SCHEDULE_DELETED, null);
    }

    private Horario obtenerHorario(Long idHorario) {
        return repository.findById(idHorario)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.SCHEDULE_NOT_FOUND));
    }

    private SemanaAcademica obtenerSemanaActiva(Long idSemana) {
        SemanaAcademica semana = semanaRepository.findById(idSemana)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.WEEK_NOT_FOUND));
        if (!"Y".equals(semana.getEstado())) {
            throw new BusinessException(MessageConstants.WEEK_INACTIVE);
        }
        return semana;
    }

    private Aula obtenerAulaActiva(Long idAula) {
        Aula aula = aulaRepository.findById(idAula)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.AULA_NOT_FOUND));
        if (!"Y".equals(aula.getEstado())) {
            throw new BusinessException(MessageConstants.AULA_INACTIVE);
        }
        return aula;
    }

    private BloqueHorario obtenerBloqueActivo(Long idBloque) {
        BloqueHorario bloque = bloqueRepository.findById(idBloque)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.BLOCK_NOT_FOUND));
        if (!"Y".equals(bloque.getEstado())) {
            throw new BusinessException(MessageConstants.BLOCK_INACTIVE);
        }
        return bloque;
    }

    private Actividad obtenerActividadActiva(Long idActividad) {
        Actividad actividad = actividadRepository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.ACTIVITY_NOT_FOUND));
        if (!"Y".equals(actividad.getEstado())) {
            throw new BusinessException(MessageConstants.ACTIVITY_INACTIVE);
        }
        if (actividad.getCurso() == null || !"Y".equals(actividad.getCurso().getEstado())) {
            throw new BusinessException(MessageConstants.COURSE_INACTIVE);
        }
        return actividad;
    }

    private Usuario obtenerDocenteActivo(Long idDocente) {
        Usuario docente = usuarioRepository.findById(idDocente)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.TEACHER_NOT_FOUND));
        if (!"Y".equals(docente.getEstado())) {
            throw new BusinessException(MessageConstants.TEACHER_INACTIVE);
        }
        return docente;
    }

    private void validarConflictoHorario(java.time.LocalDate fecha, Long idBloque, Long idAula, Long idDocente, Long idHorarioActual) {
        validarConflictoAula(fecha, idBloque, idAula, idHorarioActual);
        validarConflictoDocente(fecha, idBloque, idDocente, idHorarioActual);
    }

    private void validarConflictoAula(java.time.LocalDate fecha, Long idBloque, Long idAula, Long idHorarioActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(horario -> horario.getIdHorario() != null
                        && (idHorarioActual == null || !horario.getIdHorario().equals(idHorarioActual))
                        && fecha.equals(horario.getFecha())
                        && horario.getBloqueHorario() != null
                        && horario.getAula() != null
                        && horario.getBloqueHorario().getIdBloque().equals(idBloque)
                        && horario.getAula().getIdAula().equals(idAula));
        if (existe) {
            throw new BusinessException(MessageConstants.SCHEDULE_CONFLICT);
        }
    }

    private void validarConflictoDocente(java.time.LocalDate fecha, Long idBloque, Long idDocente, Long idHorarioActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(horario -> horario.getIdHorario() != null
                        && (idHorarioActual == null || !horario.getIdHorario().equals(idHorarioActual))
                        && fecha.equals(horario.getFecha())
                        && horario.getBloqueHorario() != null
                        && horario.getDocente() != null
                        && horario.getBloqueHorario().getIdBloque().equals(idBloque)
                        && horario.getDocente().getIdUsuario().equals(idDocente));
        if (existe) {
            throw new BusinessException(MessageConstants.SCHEDULE_CONFLICT);
        }
    }

    private void validarActivo(Horario horario) {
        if (!"Y".equals(horario.getEstado())) {
            throw new BusinessException(MessageConstants.SCHEDULE_INACTIVE);
        }
    }
}
