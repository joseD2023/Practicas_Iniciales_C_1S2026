
//aqui vamos a utilizar las rutas de cambios de paginas 
import { NavLink } from "react-router-dom";
import "../styles/nav.css"
import Sesion from "../templates/sesion";


export default function NavBar(){
    return (
        <header className="nav-header">
            <nav className="nav">

                <ul>
                    <li>
                        <NavLink
                        to={"/home"}
                        className={({isActive}) => (isActive? 'active' : undefined)}>Mi Perfil</NavLink>
                    </li>

                    <li>
                        <NavLink 
                        to={"/publicaciones"}
                        className={({isActive}) => (isActive? 'active': undefined)}>Publicaciones</NavLink>
                    </li>

                    <li>
                        <NavLink 
                        to={"/usuarios"} //esta parte se encuentra en route
                        className={({isActive}) => (isActive? 'active': undefined)}>
                            Usuarios
                        </NavLink>
                    </li>

                    <li>
                        <NavLink
                        to={"/cursos"}
                        className={({isActive}) => (isActive? 'active':undefined)}>
                            Cursos
                        </NavLink>
                    </li>

                    <li>
                        <NavLink
                        to={"/catedraticos"}
                        className={({isActive})=> (isActive? 'active':undefined)}>
                            Catedraticos
                        </NavLink>
                    </li>

                    <li>

                        <Sesion />
           

                    </li>
                </ul>
            </nav>
        </header>
    )

}