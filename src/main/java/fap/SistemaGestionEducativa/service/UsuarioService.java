package fap.SistemaGestionEducativa.service;

import fap.SistemaGestionEducativa.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obtenerTodosLosUsuarios();
    Usuario obtenerUsuarioPorId(Long id);
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(Long id, Usuario usuario);
    void eliminarUsuario(Long id);
}
