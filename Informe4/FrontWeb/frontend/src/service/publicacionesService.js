//aqui vamos a consumir la api de publicaciones 

const API_URL = "http://localhost:8080/api/publicaciones"; 

export const getpublicaciones = async () =>{
    const response = await fetch(API_URL,{
        method: "GET", 
        credentials: "include"
    }); 

    if(!response.ok){
        throw new Error("Error al obtener las publicaciones"); 

    }

    return await response.json();
}



export const getComentariosByPublicacion = async (publicacionId) => {
    const response = await fetch(`${API_URL}/${publicacionId}/comentarios`, {
        method: "GET",
        credentials: "include"
    });

    if (!response.ok) {
        throw new Error("Error al obtener comentarios");
    }

    return await response.json();
};