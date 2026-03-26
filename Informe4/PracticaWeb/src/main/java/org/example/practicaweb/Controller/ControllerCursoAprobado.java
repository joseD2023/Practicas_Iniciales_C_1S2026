package org.example.practicaweb.Controller;


import org.example.practicaweb.Model.CursoAprobado;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Service.CursoAprobadoService;
import org.example.practicaweb.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

@RestController
@RequestMapping("/api/cursos-aprobados")
public class ControllerCursoAprobado {
    private final CursoAprobadoService cursoAprobadoService;
    private final UsuarioService usuarioService;

    public ControllerCursoAprobado(CursoAprobadoService cursoAprobadoService, UsuarioService usuarioService ) {
        this.cursoAprobadoService = cursoAprobadoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public List<CursoAprobado> obtenerCursosAprobados(){
        return cursoAprobadoService.getAllCursosAprobados();
    }



    @PostMapping()
    public ResponseEntity<CursoAprobado> crearCursoAprobado(@RequestBody CursoAprobado cursoNuevo){
        CursoAprobado curso = cursoAprobadoService.createCursoAprobado(cursoNuevo);
        return ResponseEntity.ok(curso);
    }


    @GetMapping("/usuario/registro/{registro}")
    public List<CursoAprobado> obtenerCursosPorUsuarios(@PathVariable int registro){
        Usuario usuario = usuarioService.getUsuarioRegistroAcademico(registro);
        return cursoAprobadoService.cursosIdAprobados(usuario.getId());
    }


    @GetMapping("/mis-cursos")
    public List<CursoAprobado> obtenerCursosAprobadosId(){
        return cursoAprobadoService.cursosAprobadosId();
    }

}
