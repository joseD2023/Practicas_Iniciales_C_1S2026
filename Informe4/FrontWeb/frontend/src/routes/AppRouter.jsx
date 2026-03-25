import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "../templates/Login";
import Publicaciones from "../templates/publicaciones"
import Register from "../templates/Register";
import Home from "../templates/home";
import MostrarUsuarios from "../templates/usuarios";
import MostrarCursos from "../templates/cursos";
import MostrarCatedraticos from "../templates/catedraticos";
import Layout from "../components/encapsular"
import Filtrar from "../templates/Filtrar";
import RecuperarContra from "../templates/recuperar";

export default function AppRouter() {

  return (

    <BrowserRouter>

      <Routes>

        {/* Login */}
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register/>} />
        <Route path="/recuperar-password" element={<RecuperarContra/>} />


        <Route element={< Layout />}>

           <Route path="/home" element={<Home />} />
          {/*publicaciones */}
           <Route path="/publicaciones" element={<Publicaciones />} />
           
           {/*Mostrar los usuarios en el Sistema */}
           <Route path="/usuarios" element={<MostrarUsuarios/>} />
           <Route path="/cursos" element={<MostrarCursos/>} />
           <Route path="/catedraticos" element={<MostrarCatedraticos />} />

        </Route>

      </Routes>

    </BrowserRouter>

  );

}
