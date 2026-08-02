package fap.SistemaGestionEducativa.dto.response.dashboard;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioHoyResponse {

    private String hora;

    private String curso;

    private String aula;

    private String docente;

}