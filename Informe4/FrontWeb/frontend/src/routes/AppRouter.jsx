import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "../templates/Login";
import Publicaciones from "../templates/publicaciones"

export default function AppRouter() {

  return (

    <BrowserRouter>

      <Routes>

        {/* Login */}
        <Route path="/" element={<Login />} />

        {/*publicaciones */}
        <Route path="/publicaciones" element={<Publicaciones />} />


      </Routes>

    </BrowserRouter>

  );

}
