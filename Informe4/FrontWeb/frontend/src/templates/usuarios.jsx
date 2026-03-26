import { useLocation } from "react-router-dom";
import { useEffect, useState } from "react";
import { perfilUsuario, cursosPerfiles, actualizarPerfil } from "../service/usuariosService";
import "../styles/perfil.css"

export default function Perfil(){

    const { state } = useLocation();
    const registro = state?.registro;

    const [usuario, setUsuario] = useState(null);
    const [cursos, setCursos] = useState([]);

    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [correo, setCorreo] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {

        if(!registro) return;

        const cargarDatos = async () => {
            try {
                const user = await perfilUsuario(registro);
                const cursosData = await cursosPerfiles(registro);

                setUsuario(user);
                setCursos(cursosData);

            } catch (error) {
                console.log(error);
            }
        };

        cargarDatos();

    }, [registro]);



    const guardarCambios = async () => {

        const datos = {
            nombre: nombre,
            apellido: apellido,
            correo: correo,
            password: password
        };

        try {
            const actualizado = await actualizarPerfil(datos);
            setUsuario(actualizado);
            alert("Estos Datos Unicamente te Afectaran a ti y no a otro usuario :)")
            alert("Usuario actualizado");

        } catch (error) {
            console.log(error);
        }
    };


    return (
        <div>

            <h3>Editar Perfil Usuario Logueado</h3>



            <form className="form-usuarios-perfil" onSubmit={(e) => {
                e.preventDefault();
                guardarCambios();
            }}>

                <input  className="controls"
                    type="text"
                    placeholder="Nombre"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                />

                <input  className="controls"
                    type="text"
                    placeholder="Apellido"
                    value={apellido}
                    onChange={(e) => setApellido(e.target.value)}
                />

                <input className="controls"
                    type="email"
                    placeholder="Correo"
                    value={correo}
                    onChange={(e) => setCorreo(e.target.value)}
                />

                <input  className="controls"
                    type="password"
                    placeholder="Password"
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button type="submit">
                    Guardar Cambios
                </button>

            </form>




            <h2>Resultados de Perfiles: </h2>


            {usuario && (
                <div className="card-usuario-perfil">
                    <h2>{usuario.nombre} {usuario.apellido}</h2>
                    <p>Registro: {usuario.registroAcademico}</p>
                    <p>Total Créditos: {usuario.totalCreditos}</p>
                </div>
            )}

            {cursos.map(c => (
                <div key={c.id} className="curso-card">
                    <p>{c.nombreCursos}</p>
                    <p>Créditos: {c.creditos}</p>
                </div>
            ))}

        </div>
    );
}