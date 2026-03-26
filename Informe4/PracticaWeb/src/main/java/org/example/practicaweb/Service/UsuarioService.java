package org.example.practicaweb.Service;


import org.example.practicaweb.Model.RecuperarPassword;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public Usuario findByMyUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String registroAcademico = authentication.getName();
        Usuario usuario = usuarioRepository.findByRegistroAcademico(Integer.parseInt(registroAcademico));
        return usuario;
    }

    public Usuario actualizarUsuario(Usuario actualizar){
        //para que pueda actualizar uno a uno entonces

        //recordemos para que tenga id debe esta autenticado y asi usar el id correctamente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String registroAcademico = authentication.getName();
        Usuario usuario = usuarioRepository.findByRegistroAcademico(Integer.parseInt(registroAcademico)); //el usuario que esta autenticado lo vamos a poder modificar

        if(!usuario.getNombre().isEmpty() && !actualizar.getNombre().isEmpty()){
            usuario.setNombre(actualizar.getNombre());
        }

        if(!usuario.getApellido().isEmpty() && !actualizar.getApellido().isEmpty()){
            usuario.setApellido(actualizar.getApellido());
        }

        if (!usuario.getCorreo().isEmpty() && !actualizar.getCorreo().isEmpty()){
            usuario.setCorreo(actualizar.getCorreo());
        }

        if (!usuario.getNombre().isEmpty() && !actualizar.getNombre().isEmpty()){
            usuario.setPassword(passwordEncoder.encode(actualizar.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }



}
