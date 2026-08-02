package fap.SistemaGestionEducativa.dto.request.seguridad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolRequest {

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 30)
    private String nombreRol;
}
