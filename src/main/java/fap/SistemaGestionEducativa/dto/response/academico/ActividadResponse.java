package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActividadResponse {

    private Long idActividad;

    private String nombre;

    private String tipo;

    private Long idCurso;

    private String curso;

    private String estado;

}