package fap.SistemaGestionEducativa.service.Impl;

import fap.SistemaGestionEducativa.dto.request.seguridad.UsuarioRequest;
import fap.SistemaGestionEducativa.dto.response.ApiResponse;
import fap.SistemaGestionEducativa.dto.response.seguridad.UsuarioResponse;
import fap.SistemaGestionEducativa.exception.ResourceNotFoundException;
import fap.SistemaGestionEducativa.mapper.seguridad.UsuarioMapper;
import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import fap.SistemaGestionEducativa.repository.seguridad.UsuarioRepository;
import fap.SistemaGestionEducativa.service.business.UsuarioService;
import fap.SistemaGestionEducativa.service.validadores.Validaciones;
import fap.SistemaGestionEducativa.util.ApiConstants;
import fap.SistemaGestionEducativa.util.MessageConstants;
import fap.SistemaGestionEducativa.util.ResponseBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final Validaciones validaciones;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ApiResponse<UsuarioResponse> registrar(UsuarioRequest request) {

        validaciones.validarDuplicados(request);

        Usuario usuario = usuarioMapper.toEntity(request);

        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado("Y");

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return ResponseBuilder.success(
                ApiConstants.CREATED,
                MessageConstants.USER_CREATED,
                usuarioMapper.toResponse(usuarioGuardado)
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
    @Transactional
    public ApiResponse<UsuarioResponse> obtenerPorId(Long idUsuario) {

        Usuario usuario = validaciones.obtenerUsuario(idUsuario);

        return ResponseBuilder.success(
                ApiConstants.SUCCESS,
                MessageConstants.SUCCESS,
                usuarioMapper.toResponse(usuario)
        );

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
