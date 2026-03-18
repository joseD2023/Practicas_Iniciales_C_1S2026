package org.example.practicaweb.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RecuperarPassword {
    private Integer registroAcademico;
    private String password;
    private String correoElectronico;

    public RecuperarPassword(String correoElectronico, String password, Integer registroAcademico) {
        this.correoElectronico = correoElectronico;
        this.password = password;
        this.registroAcademico = registroAcademico;
    }


}
