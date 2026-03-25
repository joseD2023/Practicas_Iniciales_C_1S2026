package org.example.practicaweb.Repository;

import org.example.practicaweb.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*aqui usamos una interface para acceder a los metodos que SpringBoot
* me beneficia por usar JpaReposistory
* donde identifique mis modelos como tablas de la BD*/

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    /*Metodos:
    * Spring lo genera automaticamente:
    * save()
      findAll()
      findById()
      delete()*/

    /*si queremos agregar más metodos solo debemos declarar el tipo de funcion*/
    Usuario findByNombre(String Nombre);
    Usuario findByRegistroAcademico(int registroAcademico);  // ← Busca por int  // ← Agrega esto
    Optional<Usuario> findByRegistroAcademicoAndCorreo(Integer registroAcademico,String correo);


}
