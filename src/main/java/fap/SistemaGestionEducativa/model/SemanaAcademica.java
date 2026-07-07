package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "SEMANA_ACADEMICA")
public class SemanaAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_semana")
    @SequenceGenerator(name = "seq_semana", sequenceName = "SEQ_SEMANA", allocationSize = 1)
    @Column(name = "ID_SEMANA")
    private Long idSemana;

    @Column(name = "NUMERO_SEMANA", nullable = false)
    private Integer numeroSemana;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "FECHA_INICIO", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}