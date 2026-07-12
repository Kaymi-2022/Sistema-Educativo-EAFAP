package fap.SistemaGestionEducativa.dto.response;

import lombok.Builder;

@Builder
public class UsuarioResponse {

    private Long idUsuario;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String estado;

}