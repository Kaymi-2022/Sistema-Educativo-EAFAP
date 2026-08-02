package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloqueHorarioResponse {

    private Long idBloque;

    private String horaInicio;

    private String horaFin;

    private String estado;

}