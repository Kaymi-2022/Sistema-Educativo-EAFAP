package fap.SistemaGestionEducativa.dto.response.dashboard;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDashboardResponse {

    private Long idCurso;

    private String curso;

    private String categoria;

    private String docente;

    private Integer totalEstudiantes;

}