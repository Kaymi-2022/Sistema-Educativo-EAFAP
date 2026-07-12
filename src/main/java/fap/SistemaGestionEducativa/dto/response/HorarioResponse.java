package fap.SistemaGestionEducativa.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import fap.SistemaGestionEducativa.model.*;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class HorarioResponse {

    private Long idHorario;

    private String diaSemana;

    private LocalDate fecha;

    private Long idSemana;
    private String semana;

    private Long idAula;
    private String aula;

    private Long idBloqueHorario;
    private String bloqueHorario;

    private Long idActividad;
    private String actividad;

    private Long idUsuario;
    private String docente;

    private String estado;
}
