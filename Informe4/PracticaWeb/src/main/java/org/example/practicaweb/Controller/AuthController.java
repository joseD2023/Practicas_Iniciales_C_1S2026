package org.example.practicaweb.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.practicaweb.Model.RecuperarPassword;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Service.UserDetailsServiceImpl;
import org.example.practicaweb.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsuarioService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Registro
    @PostMapping("/register")
    public String register(@RequestBody Usuario user) throws Exception {
        userService.registrar(user);
        return "Usuario registrado correctamente";
    }



    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user, HttpServletRequest request) throws Exception {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getRegistroAcademico(),
                            user.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        } catch (BadCredentialsException e) {
            //respuesta HTTP 401 usuario no autorizado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o Contraseña Incorrecta");
        }
        //respuesta HTTP 200 bien
        return ResponseEntity.ok("Login exitoso");
    }


    @PostMapping ("/recuperar-password")
    public ResponseEntity<?> obtenerPasswordNew(@RequestBody RecuperarPassword recuperarPassword){
        if(userService.recuperarPasswordOlvidado(recuperarPassword)){
            return ResponseEntity.ok("Contraseña Actualizada Correctamente");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos Incorrectos");
    }
}
