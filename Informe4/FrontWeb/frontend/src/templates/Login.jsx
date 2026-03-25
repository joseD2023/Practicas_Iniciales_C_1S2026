import { useState } from "react";
import { login } from "../service/authService";
import "../styles/Login.css"
import { useNavigate } from "react-router-dom";

export default function Login() {

  const [registroAcademico, setRegistro] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);

  //guardamos lo que escribe los usuarios en los enpoints 

  const navigate = useNavigate(); //sirve para cambiar de pagina 

const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      if(!password || !registroAcademico){
        setError("Campos Incompletos")
        return; 
      }
      const res = await login(registroAcademico, password);

      if(res.ok){ //usamo el http OK 201 del backend 
        navigate("/home")
      }else{
        console.log("No peude Ingresar con usuarios Invalidos")
        setError("Usuario Invalido")
        return; 
      }

        
    } catch (error) {
        console.error("Error en login:", error);
    }; 
 };


  return (


    <section className="form-login">
      <h3>Login Usuario</h3>

      <form onSubmit={handleSubmit}>
      <input className="controls"
        placeholder="Registro Académico" value={registroAcademico} onChange={(e)=>setRegistro(e.target.value)}/>
      <input className="controls" type="password" placeholder="Password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
      <button type="submit">Login</button>
    </form>


    <br />

    <button className="btn-register" onClick={() => navigate("/register")}>Registrar</button>

    {error && (
      <div style={{
        background: "#2c2c2c",
        color : "#fff",
        padding : "15px", 
        borderRadius: "10px", 
        border : "1px solid #ff4d4d", 
        marginTop : "90px"

      }}>
        <strong>Error :    </strong>  
        {error}
      </div>

    )}

    <br />
    <br />
  
    <a onClick={() => navigate("/recuperar-password")}> ¿Has olvidado tu Contraseña?</a>


    </section>







  );
}
