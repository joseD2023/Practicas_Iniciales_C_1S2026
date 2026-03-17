package org.example.practicaweb.Service;


import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar usuario
    public Usuario registrar(Usuario usuario) throws Exception {
        if(usuarioRepository.findByNombre(usuario.getNombre()) != null){
            throw new Exception("Usuario ya Existe");
        }
        // Codificar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

}
