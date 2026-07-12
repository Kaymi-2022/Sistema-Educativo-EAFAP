package fap.SistemaGestionEducativa.dto.response;

import fap.SistemaGestionEducativa.model.Curso;
import fap.SistemaGestionEducativa.model.Usuario;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@Builder
public class CursoDiscenteResponse {

    private Long idCursoDiscente;

    private Curso curso;

    private Usuario usuarioEstudiante;

    private LocalDate fechaMatricula;

    private String estado = "Y";
}
