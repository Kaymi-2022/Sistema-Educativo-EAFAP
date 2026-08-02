package fap.SistemaGestionEducativa.dto.response.seguridad;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRolResponse {

    private Long idUsuario;
    private String usuario;
    private Long idRol;
    private String rol;

}
