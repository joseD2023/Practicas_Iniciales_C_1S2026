package org.example.practicaweb.Service;
import java.util.ArrayList;
import org.example.practicaweb.Model.Usuario;
import org.example.practicaweb.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String registroAcademicoStr) throws UsernameNotFoundException {
        try {
            // Convertir String a int
            int registroAcademico = Integer.parseInt(registroAcademicoStr);

            Usuario user = userRepository.findByRegistroAcademico(registroAcademico);

            if (user == null) {
                throw new UsernameNotFoundException("Usuario no encontrado con registro: " + registroAcademico);
            }

            return new org.springframework.security.core.userdetails.User(
                    String.valueOf(user.getRegistroAcademico()), // Convertir a String
                    user.getPassword(),
                    new ArrayList<>()
            );
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Registro académico inválido: " + registroAcademicoStr);
        }
    }
}
