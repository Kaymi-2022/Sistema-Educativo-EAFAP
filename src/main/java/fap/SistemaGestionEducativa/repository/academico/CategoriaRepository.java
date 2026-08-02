package fap.SistemaGestionEducativa.repository.academico;

import fap.SistemaGestionEducativa.model.academico.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    boolean existsByNombre (String nombre);
    List<Categoria> findByEstado(String estado);
}
