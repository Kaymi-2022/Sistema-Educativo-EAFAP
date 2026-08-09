package fap.SistemaGestionEducativa.service.impl;

import fap.SistemaGestionEducativa.dto.request.academico.BloqueHorarioRequest;
import fap.SistemaGestionEducativa.dto.response.RestResponse;
import fap.SistemaGestionEducativa.dto.response.academico.BloqueHorarioResponse;
import fap.SistemaGestionEducativa.exception.BusinessException;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.academico.BloqueHorarioMapper;
import fap.SistemaGestionEducativa.model.academico.BloqueHorario;
import fap.SistemaGestionEducativa.repository.academico.BloqueHorarioRepository;
import fap.SistemaGestionEducativa.service.business.BloqueHorarioService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BloqueHorarioServiceImpl implements BloqueHorarioService {

    private final BloqueHorarioRepository repository;
    private final BloqueHorarioMapper mapper;

    @Override
    public RestResponse<BloqueHorarioResponse> registrar(BloqueHorarioRequest request) {
        validarRango(request);
        validarDuplicado(request.getHoraInicio(), request.getHoraFin(), null);

        BloqueHorario bloque = mapper.toEntity(request);
        bloque.setEstado("Y");

        BloqueHorario guardado = repository.save(bloque);
        return ResponseBuilder.success(ApiConstants.CREATED, MessageConstants.BLOCK_CREATED, mapper.toResponse(guardado));
    }

    @Override
    public RestResponse<BloqueHorarioResponse> actualizar(Long idBloque, BloqueHorarioRequest request) {
        BloqueHorario bloque = obtenerBloque(idBloque);
        validarRango(request);
        validarDuplicado(request.getHoraInicio(), request.getHoraFin(), idBloque);

        bloque.setHoraInicio(request.getHoraInicio());
        bloque.setHoraFin(request.getHoraFin());

        BloqueHorario actualizado = repository.save(bloque);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.BLOCK_UPDATED, mapper.toResponse(actualizado));
    }

    @Override
    @Transactional(readOnly = true)
    public RestResponse<List<BloqueHorarioResponse>> listar() {
        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                repository.findAllByEstado("Y").stream().map(mapper::toResponse).toList()
        );
    }

    @Override
    public RestResponse<Void> eliminar(Long idBloque) {
        BloqueHorario bloque = obtenerBloque(idBloque);
        validarActivo(bloque);
        bloque.setEstado("N");
        repository.save(bloque);
        return ResponseBuilder.success(ApiConstants.SUCCESS, MessageConstants.BLOCK_DELETED, null);
    }

    private BloqueHorario obtenerBloque(Long idBloque) {
        return repository.findById(idBloque)
                .orElseThrow(() -> new ResourceNotFoundException(MessageConstants.BLOCK_NOT_FOUND));
    }

    private void validarDuplicado(String horaInicio, String horaFin, Long idBloqueActual) {
        boolean existe = repository.findAllByEstado("Y").stream()
                .anyMatch(bloque -> bloque.getHoraInicio() != null
                        && bloque.getHoraFin() != null
                        && bloque.getHoraInicio().equals(horaInicio)
                        && bloque.getHoraFin().equals(horaFin)
                        && (idBloqueActual == null || !bloque.getIdBloque().equals(idBloqueActual)));
        if (existe) {
            throw new BusinessException(MessageConstants.BLOCK_ALREADY_EXISTS);
        }
    }

    private void validarRango(BloqueHorarioRequest request) {
        if (!LocalTime.parse(request.getHoraInicio()).isBefore(LocalTime.parse(request.getHoraFin()))) {
            throw new BusinessException(MessageConstants.BLOCK_INVALID_RANGE);
        }
    }

    private void validarActivo(BloqueHorario bloque) {
        if (!"Y".equals(bloque.getEstado())) {
            throw new BusinessException(MessageConstants.BLOCK_INACTIVE);
        }
    }
}
