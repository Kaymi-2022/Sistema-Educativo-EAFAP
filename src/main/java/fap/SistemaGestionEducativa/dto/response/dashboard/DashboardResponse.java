package fap.SistemaGestionEducativa.dto.response.dashboard;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    // Indicadores
    private Long totalUsuarios;
    private Long totalDocentes;
    private Long totalEstudiantes;
    private Long totalCursos;
    private Long totalMatriculas;
    private Long totalEvaluaciones;

    // Gráfico de cursos por categoría
    private List<ResumenCategoriaResponse> categorias;

    // Tabla de cursos activos
    private List<CursoDashboardResponse> cursos;

    // Evaluaciones próximas
    private List<EvaluacionPendienteResponse> evaluacionesPendientes;

    // Horario del día
    private List<HorarioHoyResponse> horarioHoy;

}
