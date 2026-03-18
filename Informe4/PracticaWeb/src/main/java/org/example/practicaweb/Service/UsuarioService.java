package org.example.practicaweb.Service;


import org.example.practicaweb.Model.RecuperarPassword;
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

    public Usuario getUsuarioRegistroAcademico(Integer id){
        return usuarioRepository.findByRegistroAcademico(id);
    }


    /*ahora vamos a hacer como recuperar contraseña simulada para el usuario*/

    public boolean recuperarPasswordOlvidado(RecuperarPassword datos){
        System.out.println("Datos: " + datos.getRegistroAcademico() + " " +
                datos.getCorreoElectronico() + " " + datos.getPassword());

        for(Usuario u : usuarioRepository.findAll()){
            if(u.getRegistroAcademico().equals(datos.getRegistroAcademico()) &&
            u.getCorreo().equals(datos.getCorreoElectronico())){
                System.out.println("Encontramos al Usuario: " + u.getNombre());
                u.setPassword(passwordEncoder.encode(datos.getPassword()));
                usuarioRepository.save(u);
                return true;
            }
        }
        return false;
    }

}
