package org.example.practicaweb.Repository;

import org.example.practicaweb.Model.Catedratico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatedraticoReponsitory extends JpaRepository<Catedratico, Long> {

}
