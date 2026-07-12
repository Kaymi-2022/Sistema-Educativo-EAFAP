package fap.SistemaGestionEducativa.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PeriodoAcademicoResponse {

    private Long idPeriodo;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String estado;

}
