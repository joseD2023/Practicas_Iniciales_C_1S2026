package org.example.practicaweb.Repository;

import org.example.practicaweb.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {
}
