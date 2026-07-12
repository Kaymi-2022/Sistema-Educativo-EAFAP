package fap.SistemaGestionEducativa.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RolUsuarioResponse {

    private Long idUsuario;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String estadoUsuario;

    private Long idRol;
    private String nombreRol;
    private String estadoRol;


}
