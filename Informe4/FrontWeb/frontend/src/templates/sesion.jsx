import { useNavigate } from "react-router-dom";
import { cerrarSesion } from "../service/authService";
import "../styles/sesion.css"


export default function Sesion(){

    const navigate = useNavigate()

    const handleSesion = async() =>{
        await cerrarSesion(); 
        navigate("/")
    }

    return (

        <div className="sesion">
          <li>
              <button onClick={handleSesion}>
                Cerrar Sesion
              </button>
           </li>
        </div>



    )
}