package fap.SistemaGestionEducativa.dto.response.dashboard;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumenCategoriaResponse {

    private String categoria;

    private Integer totalCursos;

}