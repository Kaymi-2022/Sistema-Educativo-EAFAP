package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.CursoDiscente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoDiscenteRepository extends JpaRepository<CursoDiscente, Long> {
    List<CursoDiscente> findByUsuarioEstudianteIdUsuario(Long idUsuario);
    List<CursoDiscente> findByCursoIdCurso(Long idCurso);
    boolean existsByCursoIdCursoAndUsuarioEstudianteIdUsuario(Long idCurso,Long idUsuario
    );
}
