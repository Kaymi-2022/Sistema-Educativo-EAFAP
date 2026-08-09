package fap.SistemaGestionEducativa.model.academico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "SEMANA_ACADEMICA")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SemanaAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_semana_academica")
    @SequenceGenerator(name = "seq_semana_academica", sequenceName = "SEQ_SEMANA_ACADEMICA", allocationSize = 1)
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