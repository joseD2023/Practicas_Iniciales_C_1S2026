import NavBar from "./Navbar";
import { Outlet } from "react-router-dom";


//vamos a envolver los enpoints en nav tipo nav donde podemos utilizarlos para dirreccionar a otro enpoint si recarga la pagina
export default function Layout(){
    return (
        <>
        <NavBar />
        <Outlet />
        </>
    )
}

//basicamente es donde se van a redenrizas las paginas "hijas"