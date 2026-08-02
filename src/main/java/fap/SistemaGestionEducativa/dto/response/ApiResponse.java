package fap.SistemaGestionEducativa.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse <T>{

    private boolean success;

    private String code;

    private String message;

    private T data;

    @Builder.Default
    private LocalDate timestamp = LocalDate.now();

}
