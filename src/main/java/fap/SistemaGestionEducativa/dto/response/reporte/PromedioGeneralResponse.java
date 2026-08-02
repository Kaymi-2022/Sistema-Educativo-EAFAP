package fap.SistemaGestionEducativa.dto.response.reporte;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromedioGeneralResponse {

    private String codigoEstudiante;

    private String estudiante;

    private BigDecimal promedioGeneral;

    private Integer cursosAprobados;

    private Integer cursosDesaprobados;

}