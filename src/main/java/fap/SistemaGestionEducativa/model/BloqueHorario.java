package fap.SistemaGestionEducativa.model;

import jakarta.persistence.*;

@Entity
@Table(name = "BLOQUE_HORARIO")
public class BloqueHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bloque")
    @SequenceGenerator(name = "SEQ_BLOQUE_HORARIO", sequenceName = "SEQ_BLOQUE", allocationSize = 1)
    @Column(name = "ID_BLOQUE")
    private Long idBloque;

    @Column(name = "HORA_INICIO", nullable = false)
    private String horaInicio;

    @Column(name = "HORA_FIN", nullable = false)
    private String horaFin;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}
