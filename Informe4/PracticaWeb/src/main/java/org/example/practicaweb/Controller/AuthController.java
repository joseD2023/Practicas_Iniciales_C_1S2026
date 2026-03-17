package org.example.practicaweb.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Service.UserDetailsServiceImpl;
import org.example.practicaweb.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String login(@RequestBody Usuario user, HttpServletRequest request) throws Exception {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getRegistroAcademico(),
                            user.getPassword()
                    )
            );

            // Esto es crucial
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Guardar explícitamente en sesión
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        } catch (BadCredentialsException e) {
            throw new Exception("Usuario o contraseña incorrecta");
        }
        return "Login exitoso";
    }
}
