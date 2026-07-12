package fap.SistemaGestionEducativa.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioRolRequest {

    @NotNull(message = "El ID del usuario no puede ser nulo.")
    private Long idUsuario;

    @NotNull(message = "El ID del rol no puede ser nulo.")
    private Long idRol;
}
