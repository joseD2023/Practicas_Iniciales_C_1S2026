package org.example.practicaweb.Controller;
import jakarta.validation.Valid;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Repository.UsuarioRepository;
import org.example.practicaweb.Service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

@RestController
@RequestMapping(("/api/usuarios"))
public class ControllerUsuario {

    private final UsuarioService usuarioService;

    public ControllerUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*Aqui vamos indicar que a traves de nuestros endpoints un Usuario pueda mostrarse o
    * hacer diferentes acciones*/

    @GetMapping()
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioById(@PathVariable Long id){
        Usuario encontrarUsuario = usuarioService.getUsuarioById(id);
        if(encontrarUsuario == null){
            return ResponseEntity.notFound().build(); //si no existe notFound 404

        }
        return ResponseEntity.ok(encontrarUsuario);
    }

    @GetMapping("/mi-usuario")
    public ResponseEntity<?> obtenerMiInformacion(){
        Usuario encontrado =  usuarioService.findByMyUser();
        if(encontrado == null){
            return ResponseEntity.notFound().build(); // 404

        }
        return ResponseEntity.ok(encontrado);
    }


    @GetMapping("/registro/{registro}")
    public Usuario obtenerPorRegistro(@PathVariable int registro){
        return usuarioService.getUsuarioRegistroAcademico(registro); //me trae la informacion del usuario
    }

    @PostMapping()
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario nuevoUsuario){
        Usuario nuevo = null;
        try {
            nuevo = usuarioService.registrar(nuevoUsuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }



    //para actualizar utilizando autenticacion
    @PutMapping("/me")
    public ResponseEntity<Usuario> modificarUsuario(@RequestBody Usuario actualizarUsuario){
        return ResponseEntity.ok(usuarioService.actualizarUsuario(actualizarUsuario));

    }

}
