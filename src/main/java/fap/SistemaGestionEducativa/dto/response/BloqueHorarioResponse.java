package fap.SistemaGestionEducativa.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class BloqueHorarioResponse {

    private Long idBloque;

    private String horaInicio;

    private String horaFin;

    private String estado;
}
