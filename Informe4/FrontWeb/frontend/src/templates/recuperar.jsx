//vamos a trabar en la recuperacion de contraseña 

import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { recuperarPasswordUsuario, registrarUsuario } from "../service/authService";
import "../styles/recuperar.css"

export default function RecuperarContra() {
    const[registroAcademico, setRegistroAcademico] = useState(""); 
    const[correoElectronico, setCorreo] = useState(""); 
    const[password, setNuevaContra] = useState(""); 
    const[error, setError] = useState(null); 




    const handleSubmit = async(e) =>{
        e.preventDefault();

        if(!registroAcademico|| !correoElectronico || !password){
            setError("Campos Incompletos"); 
            return; 
        }

        try {

            const data = await  recuperarPasswordUsuario({registroAcademico, correoElectronico, password}); 

            if(data){
                
                navigator("/") //si todo cumple nos retorna al login
            }else{
                setError("Registro Academico o Correo Invalido")
                return; 
            }

        } catch (error) {
            setError("Ocurrio un Error al Recuperar Contraseña: 404")
        }

    }

    return(
        <div className="form-password">
            <h2>Recuperar Contraseña</h2>
            <form onSubmit={handleSubmit}>
                <input  className="controls" type="text" placeholder="Registro Academico" value={registroAcademico} onChange={(e)=> setRegistroAcademico(e.target.value)}/>

                <input className="controls" type="email"  placeholder="Correo Electronico" value={correoElectronico} onChange={(e)=>setCorreo(e.target.value)}/>

                <input className="controls" type="password" placeholder="Nueva Contraseña" value={password}  onChange={(e)=> setNuevaContra(e.target.value)}/>


                <button type="submit">Recuperar Contraseña</button>

                {error && (
                    <div style={{
                        background: "#2c2c2c",
                        color : "#fff",
                        padding : "15px", 
                        borderRadius: "10px", 
                        border : "1px solid #ff4d4d",
                        marginTop : "90px"
                        
                    }}>
                        <strong>Error :  {error}</strong>
                    </div>
                )}

            </form>
        </div>
    )
    
}