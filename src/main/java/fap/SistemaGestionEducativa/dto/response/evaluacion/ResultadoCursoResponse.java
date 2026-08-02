package fap.SistemaGestionEducativa.dto.response.evaluacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoCursoResponse {

    private Long idResultado;

    private Long idCurso;

    private String curso;

    private Long idDiscente;

    private String discente;

    private BigDecimal promedioFinal;

    private String estadoAprobacion;

    private LocalDate fechaCierre;

    private String estado;

}
