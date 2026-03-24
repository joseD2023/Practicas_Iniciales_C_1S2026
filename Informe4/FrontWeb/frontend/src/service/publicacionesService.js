/* vamos a consumir la api de publicaciones 
vamos a traer las publicaciones vamos a poder crear publicaciones como ver las publicaciones 
-crear publicaciones 
-buscar publicaciones por id 
-eliminar publicacion etc. 

*/


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



export const getComentariosByPublicacion = async (id) => { // esto nos va a servir para buscar publicaciones
    const response = await fetch(`${API_URL}/${id}/comentarios`, {
        method: "GET",
        credentials: "include"
    });

    if (!response.ok) {
        throw new Error("Error al obtener comentarios");
    }

    return await response.json();
};




export const crearPublicacionesNuevas = async(nuevaPublicacion) =>{
    try {

        console.log("Lo que nos envio el Frontend para Backend:", nuevaPublicacion)

        const response = await fetch(`${API_URL}`, {
            method: "POST", 
            credentials: "include", 
            headers : {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(nuevaPublicacion)
        }); 

        if(!response.ok){
            throw new Error("Error al Crear Publicacion");
            
        }

        return await response.json(); //retornamos en un formato json 
        
    } catch (error) {
        console.log("Error: ", error)
        
    }

}


//ahora vamos a ver como funciona lo de filtrar publicaciones 

export const filtrarPublicaciones = async ({ curso, catedratico, idCurso, idCatedratico }) => {
    let url = "/detalle/filtrado?";

    if (curso) url += `curso=${curso}&`;
    if (catedratico) url += `catedratico=${catedratico}&`;
    if (idCurso) url += `idCurso=${idCurso}&`;
    if (idCatedratico) url += `idCatedratico=${idCatedratico}&`;

    // quitar último &
    url = url.endsWith("&") ? url.slice(0, -1) : url;

    const response = await fetch(url,{
        method:"GET",
        credentials:"include"
    });
    if (!response.ok) throw new Error("Error al filtrar");
    const data = await response.json();
    return data;
};