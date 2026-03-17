import { useEffect, useState } from "react";
import { getpublicaciones } from "../service/publicacionesService";
import { getComentariosByPublicacion } from "../service/publicacionesService";
import "../styles/publicaciones.css";

export default function Publicaciones() {
    const [publicaciones, setPublicaciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [comentarios, setComentarios] = useState({});
    const [comentarioVisible, setComentarioVisible] = useState({});

    useEffect(() => {
        cargarPublicaciones();
    }, []);

    const cargarPublicaciones = async () => {
        try {
            const data = await getpublicaciones();
            setPublicaciones(data);
        } catch (error) {
            console.error("Error:", error);
        } finally {
            setLoading(false);
        }
    };

    const toggleComentarios = async (publicacionId) => {
        // Si ya están visibles, los ocultamos
        if (comentarioVisible[publicacionId]) {
            setComentarioVisible({
                ...comentarioVisible,
                [publicacionId]: false
            });
            return;
        }

        // Si no están cargados, los cargamos
        if (!comentarios[publicacionId]) {
            try {
                const data = await getComentariosByPublicacion(publicacionId);
                setComentarios({
                    ...comentarios,
                    [publicacionId]: data
                });
            } catch (error) {
                console.error("Error cargando comentarios:", error);
            }
        }

        // Mostramos los comentarios
        setComentarioVisible({
            ...comentarioVisible,
            [publicacionId]: true
        });
    };

    if (loading) return <div className="loading">Cargando publicaciones...</div>;

    return (
        <div className="publicaciones-container">
            <h1>Publicaciones</h1>
            <div className="publicaciones-lista">
                {publicaciones.map(pub => (
                    <div key={pub.id} className="publicacion-card">
                        <h2>{pub.titulo}</h2>
                        <p>{pub.contenido}</p>
                        <p>{pub.fechaCreacion}</p>
                        <small>Autor: {pub.autor}</small>
                        
                        <button 
                            className="btn-comentarios"
                            onClick={() => toggleComentarios(pub.id)}
                        >
                            {comentarioVisible[pub.id] ? 'Ocultar comentarios' : 'Ver comentarios'}
                        </button>

                        {comentarioVisible[pub.id] && (
                            <div className="comentarios-section">
                                <h4>Comentarios</h4>
                                {comentarios[pub.id]?.length > 0 ? (
                                    comentarios[pub.id].map(com => (
                                        <div key={com.id} className="comentario-item">
                                            <strong> ID Usuario: {com.id}:</strong>
                                            <p>{com.mensaje}</p>
                                            <small>{new Date(com.fechaCreacion).toLocaleDateString()}</small>
                                        </div>
                                    ))
                                ) : (
                                    <p>No hay comentarios</p>
                                )}
                            </div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
}