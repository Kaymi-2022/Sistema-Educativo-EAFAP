package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoAcademicoResponse {

    private Long idPeriodoAcademico;

    private String nombre;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String estado;

}
