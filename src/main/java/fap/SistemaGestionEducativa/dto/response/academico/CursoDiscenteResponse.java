package fap.SistemaGestionEducativa.dto.response.academico;

import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDiscenteResponse {

    private Long idCursoDiscente;

    private Long idCurso;

    private String curso;

    private Long idDiscente;

    private String discente;

    private LocalDate fechaMatricula;

    private String estado;

}
