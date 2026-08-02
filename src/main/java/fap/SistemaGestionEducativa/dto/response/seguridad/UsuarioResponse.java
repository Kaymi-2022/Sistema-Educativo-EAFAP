package fap.SistemaGestionEducativa.dto.response.seguridad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long idUsuario;
    private String dni;
    private String nombres;
    private String apellidos;
    private String email;
    private String username;
    private String estado;

}