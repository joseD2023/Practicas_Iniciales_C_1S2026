package org.example.practicaweb.Repository;

import org.example.practicaweb.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
