package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.ActividadRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.ActividadResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.ActividadMapper;
import fap.SistemaGestionEducativa.model.academico.Actividad;
import fap.SistemaGestionEducativa.model.academico.Curso;
import fap.SistemaGestionEducativa.repository.academico.ActividadRepository;
import fap.SistemaGestionEducativa.repository.academico.CursoRepository;
import fap.SistemaGestionEducativa.service.business.ActividadService;
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
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository repository;
    private final CursoRepository cursoRepository;
    private final ActividadMapper mapper;

    @Override
    public RestResponse<ActividadResponse> registrar(ActividadRequest request) {
        Curso curso = obtenerCursoActivo(request.getIdCurso());
        validarDuplicado(request.getNombre(), curso.getIdCurso(), null);

        Actividad actividad = mapper.toEntity(request);
        actividad.setCurso(curso);
        actividad.setEstado("Y");

        Actividad guardada = repository.save(actividad);
        return ResponseBuilder.success(ApiConstants.CREATED, MessageConstants.ACTIVITY_CREATED, mapper.toResponse(guardada));
    }

    @Override
    public RestResponse<ActividadResponse> actualizar(Long idActividad, ActividadRequest request) {
        Actividad actividad = obtenerActividad(idActividad);
        Curso curso = obtenerCursoActivo(request.getIdCurso());
        validarDuplicado(request.getNombre(), curso.getIdCurso(), idActividad);

        actividad.setNombre(request.getNombre());
        actividad.setTipo(request.getTipo());
        actividad.setCurso(curso);

        Actividad actualizada = repository.save(actividad);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.ACTIVITY_UPDATED, mapper.toResponse(actualizada));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<ActividadResponse>> listar() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByEstado("Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<ActividadResponse>> listarPorCurso(Long idCurso) {
        obtenerCursoActivo(idCurso);
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByCursoIdCursoAndEstado(idCurso, "Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idActividad) {
        Actividad actividad = obtenerActividad(idActividad);
        validarActivo(actividad);
        actividad.setEstado("N");
        repository.save(actividad);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.ACTIVITY_DELETED, null);
    }

    private Actividad obtenerActividad(Long idActividad) {
        return repository.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.ACTIVITY_NOT_FOUND));
    }

    private Curso obtenerCursoActivo(Long idCurso) {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.COURSE_NOT_FOUND));
        if (!"Y".equals(curso.getEstado())) {
            throw new BusinessException(MessageConstants.COURSE_INACTIVE);
        }
        return curso;
    }

    private void validarDuplicado(String nombre, Long idCurso, Long idActividadActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(actividad -> actividad.getNombre() != null
                        && actividad.getNombre().equalsIgnoreCase(nombre)
                        && actividad.getCurso() != null
                        && actividad.getCurso().getIdCurso().equals(idCurso)
                        && !Objects.equals(idActividadActual, actividad.getIdActividad()));
        if (existe) {
            throw new BusinessException(MessageConstants.ACTIVITY_ALREADY_EXISTS);
        }
    }

    private void validarActivo(Actividad actividad) {
        if (!"Y".equals(actividad.getEstado())) {
            throw new BusinessException(MessageConstants.ACTIVITY_INACTIVE);
        }
    }
}
