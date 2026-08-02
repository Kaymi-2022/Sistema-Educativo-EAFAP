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
public class SemanaAcademicaResponse {

    private Long idSemana;

    private Integer numeroSemana;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String estado;

}