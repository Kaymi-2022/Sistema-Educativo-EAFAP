package fap.SistemaGestionEducativa.dto.response;

import fap.SistemaGestionEducativa.model.Curso;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriaResponse {

    private Long idCategoria;

    private String nombre;

    private String descripcion;

    private String estado;

    private Long idCurso;
    private Curso curso;
}
