package fap.SistemaGestionEducativa.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PeriodoAcademicoRequest {

    @NotBlank(message = "El nombre del periodo académico no puede estar vacío")
    private String nombre;

    @NotNull(message = "La fecha de inicio del periodo académico no puede ser nula")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin del periodo académico no puede ser nula")
    private LocalDate fechaFin;

    @Pattern(regexp = "Y|N", message = "El estado debe ser Y o N.")
    private String estado = "Y";

}
