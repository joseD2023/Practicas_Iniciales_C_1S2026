package org.example.practicaweb.Repository;

import org.example.practicaweb.Model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

    List<Publicacion> findAllByOrderByFechaCreacionDesc();
}
