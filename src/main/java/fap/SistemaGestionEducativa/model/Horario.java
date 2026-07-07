package fap.SistemaGestionEducativa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "HORARIO")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_horario")
    @SequenceGenerator(name = "seq_horario", sequenceName = "SEQ_HORARIO", allocationSize = 1)
    @Column(name = "ID_HORARIO")
    private Long idHorario;

    @Column(name = "DIA_SEMANA", nullable = false)
    private String diaSemana;

    @Column(name = "FECHA", nullable = false)
    @JsonFormat()
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "ID_SEMANA", nullable = false)
    private SemanaAcademica semanaAcademica;

    @ManyToOne
    @JoinColumn(name = "ID_AULA", nullable = false)
    private Aula aula;

    @ManyToOne
    @JoinColumn(name = "ID_BLOQUE", nullable = false)
    private BloqueHorario bloqueHorario;

    @ManyToOne
    @JoinColumn(name = "ID_ACTIVIDAD", nullable = false)
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "ID_INSTRUCTOR", nullable = false)
    private Usuario instructor;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}