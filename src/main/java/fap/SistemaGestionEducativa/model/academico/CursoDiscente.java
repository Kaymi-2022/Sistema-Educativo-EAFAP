package fap.SistemaGestionEducativa.model.academico;

import fap.SistemaGestionEducativa.model.seguridad.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "CURSO_DISCENTE")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CursoDiscente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_curso_discente")
    @SequenceGenerator(name = "seq_curso_discente", sequenceName = "SEQ_CURSO_DISCENTE", allocationSize = 1)
    @Column(name = "ID_CURSO_DISCENTE")
    private Long idCursoDiscente;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_ESTUDIANTE")
    private Usuario estudiante;

    @Column(name = "FECHA_MATRICULA")
    private LocalDate fechaMatricula;

    @Column(name = "ESTADO", length = 1, nullable = false)
    private String estado = "Y";
}