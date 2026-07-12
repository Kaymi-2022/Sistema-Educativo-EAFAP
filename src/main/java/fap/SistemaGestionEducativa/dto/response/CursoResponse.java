package fap.SistemaGestionEducativa.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CursoResponse {



    private Long idCurso;

    private String nombre;

    private String descripcion;

    private Long idCategoria;

    private String categoria;

    private Long idUsuarioDocente;

    private String docente;

    private Long idPeriodo;

    private String periodoAcademico;

    private String estado;
}
