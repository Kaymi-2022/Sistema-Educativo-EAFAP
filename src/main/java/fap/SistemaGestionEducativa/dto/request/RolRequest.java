package fap.SistemaGestionEducativa.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RolRequest {


    @NotBlank (message = "El nombre del rol es obligatorio.")
    private String nombreRol;
}
