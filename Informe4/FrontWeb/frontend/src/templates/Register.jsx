import { useState } from "react"; //herramienta de react que guarda informacion  ejemplo lo que un usuario escribe 
import { registrarUsuario } from "../service/authService";
import "../styles/register.css"

//vamos a crear el componente para usarlo en react 

export default function Register(){
    //Estados 
    const[registro, setRegistro] = useState(""); 
    const[password, setPassword] = useState("");
    const[nombre, setNombre] = useState(""); 
    const[apellido, setApellido] = useState(""); 
    const[correo, setCorreo] = useState(""); 
    const[error, setError] = useState(null); 
    /* valor actual - funcion para cambiarlo */



    const handleSubmit = async (e) =>{ //Esto es una función que se ejecuta cuando el usuario “envía” el formulario
        e.preventDefault(); //no recargues las pagina cuando se envia el formulario

        if(!registro.trim() || !password.trim() || !nombre.trim() || !apellido.trim() || !correo.trim()){
            setError("Campos Incompletos"); 
            return; 

        }

        try {
            const response = await registrarUsuario(Number(registro), nombre, apellido, correo, password);
            if(response.ok){
                navigator("/")

            }else{
                setError("No hay Respuesta del Backend")
            }
            
        } catch (error) {
            setError("Ocurrio un Error al Registrar Usuario 404"); 
        }
    }; 

    if(error){
        return (
            <div className="Errores">
                <p>Error: Campos Incompletos</p>
            </div>
        )
    }


    return (

        <div className="form-register">
            <h2>Registro Usuario USAC</h2>

            <form onSubmit={handleSubmit}>
                <input className="controls" type="text" placeholder="Registro Academico" value={registro} onChange={(e) => setRegistro(e.target.value)} />
                <input className="controls"  type="text" placeholder="Nombre" value={nombre} onChange={(e) => setNombre(e.target.value)} />
                <input className="controls"  type="text" placeholder="apellido" value={apellido} onChange={(e) => setApellido(e.target.value)} />
                <input className="controls"  type="email" placeholder="correo Electronico" value={correo} onChange={(e) => setCorreo(e.target.value)} />
                <input className="controls"  type="password" placeholder="Contraseña" value={password} onChange={(e) => setPassword(e.target.value)} />
                <button type="submit"> Registrar </button>
            </form>

        </div>

    )
}


