package org.example.practicaweb.Repository;

import org.example.practicaweb.Model.CursoAprobado;
import org.example.practicaweb.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoAprobadoRepository extends JpaRepository<CursoAprobado, Long> {

    List<CursoAprobado> findByUsuario(Usuario usuario);
}
