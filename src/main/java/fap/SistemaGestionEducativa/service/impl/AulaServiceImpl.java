package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.AulaRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.AulaResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.AulaMapper;
import fap.SistemaGestionEducativa.model.academico.Aula;
import fap.SistemaGestionEducativa.repository.academico.AulaRepository;
import fap.SistemaGestionEducativa.service.business.AulaService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AulaServiceImpl implements AulaService {

    private final AulaRepository repository;
    private final AulaMapper mapper;

    @Override
    public RestResponse<AulaResponse> registrar(AulaRequest request) {
        validarDuplicado(request.getNombre(), null);

        Aula aula = mapper.toEntity(request);
        aula.setEstado("Y");

        Aula guardada = repository.save(aula);
        return ResponseBuilder.success(ApiConstants.CREATED, MessageConstants.AULA_CREATED, mapper.toResponse(guardada));
    }

    @Override
    public RestResponse<AulaResponse> actualizar(Long idAula, AulaRequest request) {
        Aula aula = obtenerAula(idAula);
        validarDuplicado(request.getNombre(), idAula);

        aula.setNombre(request.getNombre());
        aula.setCapacidad(request.getCapacidad());
        aula.setUbicacion(request.getUbicacion());

        Aula actualizada = repository.save(aula);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.AULA_UPDATED, mapper.toResponse(actualizada));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<AulaResponse>> listar() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                mapper.toResponseList(repository.findAllByEstado("Y"))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<AulaResponse> obtenerPorId(Long idAula) {
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.SUCCESS, mapper.toResponse(obtenerAula(idAula)));
    }

    @Override
    public RestResponse<Void> eliminar(Long idAula) {
        Aula aula = obtenerAula(idAula);
        validarActivo(aula);
        aula.setEstado("N");
        repository.save(aula);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.AULA_DELETED, null);
    }

    private Aula obtenerAula(Long idAula) {
        return repository.findById(idAula)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.AULA_NOT_FOUND));
    }

    private void validarDuplicado(String nombre, Long idAulaActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(aula -> aula.getNombre() != null
                        && aula.getNombre().equalsIgnoreCase(nombre)
                        && (idAulaActual == null || !idAulaActual.equals(aula.getIdAula())));
        if (existe) {
            throw new BusinessException(MessageConstants.AULA_ALREADY_EXISTS);
        }
    }

    private void validarActivo(Aula aula) {
        if (!"Y".equals(aula.getEstado())) {
            throw new BusinessException(MessageConstants.AULA_INACTIVE);
        }
    }
}
