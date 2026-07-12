package fap.SistemaGestionEducativa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CursoRequest {

    @NotBlank(message = "El nombre del curso es obligatorio.")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
    private String nombre;

    @Size(max = 200, message = "La descripción no puede superar los 200 caracteres.")
    private String descripcion;

    @NotNull(message = "La categoría es obligatoria.")
    private Long idCategoria;

    @NotNull(message = "El docente es obligatorio.")
    private Long idUsuarioDocente;

    @NotNull(message = "El período académico es obligatorio.")
    private Long idPeriodo;

    @Pattern(regexp = "Y|N", message = "El estado debe ser Y o N.")
    private String estado = "Y";
}
