package fap.SistemaGestionEducativa.dto.request.seguridad;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRolRequest {

    @NotNull
    private Long idUsuario;

    @NotNull
    private Long idRol;

}
