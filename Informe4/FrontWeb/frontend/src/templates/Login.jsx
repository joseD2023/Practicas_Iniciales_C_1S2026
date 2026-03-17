import { useState } from "react";
import { login } from "../service/authService";
import "../styles/Login.css"
import { useNavigate } from "react-router-dom";

export default function Login() {

  const [registroAcademico, setRegistro] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

const handleSubmit = async (e) => {
    e.preventDefault();

    try {
        const res = await login(registroAcademico, password);
        console.log("Login exitoso:", res);
        
        // Pequeña pausa para asegurar que la cookie se guarde
        setTimeout(() => {
            navigate("/publicaciones");
        }, 100);
        
    } catch (error) {
        console.error("Error en login:", error);
    }
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

    </section>





  );
}
