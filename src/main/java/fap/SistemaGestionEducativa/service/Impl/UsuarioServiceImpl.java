package fap.SistemaGestionEducativa.service.Impl;

import fap.SistemaGestionEducativa.model.Usuario;
import fap.SistemaGestionEducativa.repository.UsuarioRepository;
import fap.SistemaGestionEducativa.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public Usuario crearUsuario(@Valid Usuario usuario) {

        if(usuarioRepository.existsByDni(usuario.getDni())) {
            throw new RuntimeException("El DNI ya está registrado: " + usuario.getDni());
        }
        if(usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado: " + usuario.getEmail());
        }
        if(usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está registrado: " + usuario.getUsername());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Long id, @Valid Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con ID: " + id));

        usuarioExistente.setIdUsuario(usuario.getIdUsuario());
        usuarioExistente.setDni(usuario.getDni());
        usuarioExistente.setNombres(usuario.getNombres());
        usuarioExistente.setApellidos(usuario.getApellidos());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setEstado(usuario.getEstado());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuarioRepository.delete(usuario);
    }
}
