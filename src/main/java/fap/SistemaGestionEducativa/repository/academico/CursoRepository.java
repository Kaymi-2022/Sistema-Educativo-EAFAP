package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    List<Curso> findByCategoriaIdCategoria(Long idCategoria);

    List<Curso> findByUsuarioDocenteIdUsuario(Long idUsuario);

    List<Curso> findByPeriodoAcademicoIdPeriodo(Long idPeriodo);

    List<Curso> findByEstado(String estado);
}
