package fap.SistemaGestionEducativa.dto.response.reporte;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteAcademicoResponse {

    private String codigoEstudiante;

    private String dni;

    private String estudiante;

    private String email;

    private List<CursoReporteResponse> cursos;

    private BigDecimal promedioGeneral;

    private Integer totalCursos;

    private Integer cursosAprobados;

    private Integer cursosDesaprobados;

}