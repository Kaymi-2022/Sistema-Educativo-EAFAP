package fap.SistemaGestionEducativa.service.Impl;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.seguridad.UsuarioMapper;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.UsuarioService;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    @Override
    public ApiResponse<UsuarioResponse> registrar(UsuarioRequest request) {
        // Validar si el usuario ya existe por username o dni
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            return ResponseBuilder.success(
                    ApiConstants.NO_CONTENT,
                    MessageConstants.USER_ALREADY_EXISTS,
                    null
            );
        }
        if (usuarioRepository.existsByDni(request.getDni())) {
            return ResponseBuilder.success(
                    ApiConstants.NO_CONTENT,
                    MessageConstants.USER_ALREADY_EXISTS,
                    null
            );
        }

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setEstado("N");
        Usuario savedUsuario = usuarioRepository.save(usuario);
        UsuarioResponse response = usuarioMapper.toResponse(savedUsuario);
        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.USER_CREATED,
                response
        );

    }

    @Override
    public ApiResponse<UsuarioResponse> actualizar(Long idUsuario, UsuarioRequest request) {
        // Validar si el usuario existe
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
                .orElseThrow(()-> new ResourceNotFoundException(MessageConstants.USER_NOT_FOUND));

        Usuario updatedUsuario = usuarioRepository.save(usuarioExistente);
        UsuarioResponse response = usuarioMapper.toResponse(updatedUsuario);
        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.USER_UPDATED,
                response
        );
    }

    @Override
    public ApiResponse<UsuarioResponse> obtenerPorId(Long idUsuario) {
        return null;
    }

    @Override
    public ApiResponse<List<UsuarioResponse>> listar() {
        return null;
    }

    @Override
    public ApiResponse<Void> eliminar(Long idUsuario) {
        return null;
    }
}
