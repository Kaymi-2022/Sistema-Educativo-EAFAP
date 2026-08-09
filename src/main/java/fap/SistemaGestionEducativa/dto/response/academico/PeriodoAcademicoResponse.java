package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(name = "PeriodoAcademicoResponse", description = "Datos de un período académico")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodoAcademicoResponse {

    @Schema(example = "1")
    private Long idPeriodoAcademico;

    @Schema(example = "2026-I")
    private String nombre;

    @Schema(example = "2026-03-01")
    private LocalDate fechaInicio;

    @Schema(example = "2026-07-31")
    private LocalDate fechaFin;

    @Schema(example = "Y")
    private String estado;

}
