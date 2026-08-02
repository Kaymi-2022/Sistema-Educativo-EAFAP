package fap.SistemaGestionEducativa.dto.response.academico;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoResponse {

    private Long idCurso;

    private String nombre;

    private String descripcion;

    private Long idCategoria;

    private String categoria;

    private Long idDocente;

    private String docente;

    private Long idPeriodoAcademico;

    private String periodo;

    private String estado;

}
