package fap.SistemaGestionEducativa.dto.request;

import fap.SistemaGestionEducativa.model.Curso;
import fap.SistemaGestionEducativa.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CursoDiscenteRequest {

    @NotBlank(message = "El curso no puede estar vacío")
    private Curso curso;

    @NotBlank(message = "El usuario estudiante no puede estar vacío")
    private Usuario usuarioEstudiante;

    @NotNull(message = "La fecha de matrícula no puede ser nula")
    private LocalDate fechaMatricula;
}
