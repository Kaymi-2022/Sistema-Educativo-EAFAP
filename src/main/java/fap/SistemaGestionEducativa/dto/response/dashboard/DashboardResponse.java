package fap.SistemaGestionEducativa.dto.response.dashboard;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(name = "DashboardResponse", description = "Resumen principal del sistema")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    // Indicadores
    @Schema(example = "10")
    private Long totalUsuarios;
    @Schema(example = "2")
    private Long totalDocentes;
    @Schema(example = "8")
    private Long totalEstudiantes;
    @Schema(example = "4")
    private Long totalCursos;
    @Schema(example = "15")
    private Long totalMatriculas;
    @Schema(example = "20")
    private Long totalEvaluaciones;

    // Gráfico de cursos por categoría
    @Schema(description = "Listado de categorías")
    private List<ResumenCategoriaResponse> categorias;

    // Tabla de cursos activos
    @Schema(description = "Listado de cursos activos")
    private List<CursoDashboardResponse> cursos;

    // Evaluaciones próximas
    @Schema(description = "Evaluaciones próximas")
    private List<EvaluacionPendienteResponse> evaluacionesPendientes;

    // Horario del día
    @Schema(description = "Horario del día")
    private List<HorarioHoyResponse> horarioHoy;

}
