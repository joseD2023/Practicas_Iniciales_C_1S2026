import { useState } from "react";
import { filtrarPublicaciones } from "../service/publicacionesService";

export default function BuscadorAvanzado({ cursos, catedraticos, setPublicaciones }) {

    const [curso, setCurso] = useState("");
    const [catedratico, setCatedratico] = useState("");

    const buscar = async () => {
        try {
            const data = await filtrarPublicaciones({
                curso,
                catedratico
            });

            // 🔥 aquí actualizas la lista principal
            setPublicaciones(data);

        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div style={{ marginBottom: "20px" }}>
            <h2>Buscar</h2>

            <input
                type="text"
                placeholder="Curso"
                value={curso}
                onChange={(e) => setCurso(e.target.value)}
            />

            <input
                type="text"
                placeholder="Catedrático"
                value={catedratico}
                onChange={(e) => setCatedratico(e.target.value)}
            />

            <button onClick={buscar}>
                Filtrar
            </button>
        </div>
    );
}