package fap.SistemaGestionEducativa.repository.evaluacion;

import fap.SistemaGestionEducativa.model.evaluacion.ResultadoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultadoCursoRepository extends JpaRepository<ResultadoCurso, Long> {

    List<ResultadoCurso> findByDiscenteIdUsuario(Long idUsuario);

    Optional<ResultadoCurso> findByCursoIdCursoAndDiscenteIdUsuario(Long idCurso,Long idUsuario);

}
