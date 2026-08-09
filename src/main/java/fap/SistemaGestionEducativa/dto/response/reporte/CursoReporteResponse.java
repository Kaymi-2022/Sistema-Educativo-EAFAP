package fap.SistemaGestionEducativa.dto.response.reporte;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoReporteResponse {

    private Long idCurso;

    private String curso;

    private String categoria;

    private String docente;

    private String periodoAcademico;

    private BigDecimal promedioFinal;

    private String estadoAprobacion;

    private List<NotaDetalleResponse> evaluaciones;

}
