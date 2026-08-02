package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AulaRequest {

    @NotBlank(message = "Nombre obligatorio")
    private String nombre;

    @Positive(message = "La capacidad debe ser mayor que cero")
    private Integer capacidad;

    @Size(max = 100)
    private String ubicacion;
}
