/*aqui vamos a generar los perfiles de los usuarios -cursos aprobados y datos
aqui vamos a utilizar lo que son las busquedas entonces lo que debemos obtener es el numero de carnet del usuario */

// components/BuscadorUsuario.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function BuscadorUsuario(){

    const [registro, setRegistro] = useState("");
    const navigate = useNavigate();

    const handleBuscar = () => {
        if(!registro){
            alert("Ingrese un registro");
            return;
        }

        

        navigate("/perfil", { state: { registro } }); 
    }

    return (
        <div>
            <input
                type="text"
                placeholder="Buscar por registro"
                value={registro}
                onChange={(e)=> setRegistro(e.target.value)}
            />
            <button onClick={handleBuscar}>Buscar</button>
        </div>
    );
}