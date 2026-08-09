package fap.SistemaGestionEducativa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Schema(name = "ApiResponse", description = "Respuesta estándar de la API")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class RestResponse<T>{

    private boolean success;

    @Schema(example = "200")
    private String code;

    @Schema(example = "Operación realizada correctamente.")
    private String message;

    @Schema(description = "Datos de la respuesta")
    private T data;

    @Builder.Default
    @Schema(example = "2026-08-09")
    private LocalDate timestamp = LocalDate.now();

}
