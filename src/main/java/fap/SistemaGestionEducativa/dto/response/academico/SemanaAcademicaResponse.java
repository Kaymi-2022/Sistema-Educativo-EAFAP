package fap.SistemaGestionEducativa.dto.response.academico;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(name = "SemanaAcademicaResponse", description = "Datos de una semana académica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemanaAcademicaResponse {

    @Schema(example = "1")
    private Long idSemana;

    @Schema(example = "1")
    private Integer numeroSemana;

    @Schema(example = "Semana 1")
    private String descripcion;

    @Schema(example = "2026-09-01")
    private LocalDate fechaInicio;

    @Schema(example = "2026-09-07")
    private LocalDate fechaFin;

    @Schema(example = "Y")
    private String estado;

}