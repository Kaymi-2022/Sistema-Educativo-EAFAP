package fap.SistemaGestionEducativa.dto.request.academico;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BloqueHorarioRequest {

    @NotBlank
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$")
    private String horaInicio;

    @NotBlank
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$")
    private String horaFin;

}
