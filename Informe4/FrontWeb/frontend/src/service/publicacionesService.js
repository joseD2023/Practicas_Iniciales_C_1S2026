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
export const filtrarPublicaciones = async (tipo, valor) => {

  let url = "http://localhost:8080/api/publicaciones/detalle/filtrado?";

  if (tipo === "curso") {
    console.log("Tipo: ", tipo, " ", "Valor: ", valor);
    url += `curso=${valor}`;
} else if (tipo === "catedratico") {
    url += `catedratico=${valor}`;
    console.log("Tipo: ", tipo, " ", "Valor: ", valor);

} else if (tipo === "idCurso") {
    url += `idCurso=${valor}`;
    console.log("Tipo: ", tipo, " ", "Valor: ", valor);  

} else if (tipo === "idCatedratico") {
    url += `idCatedratico=${valor}`;
    console.log("Tipo: ", tipo, " ", "Valor: ", valor);
  }

  const response = await fetch(url, {
    credentials: "include" 
  });

  console.log("El filtro de la respuesta del backend es: ", response)
  const data = await response.json(); 
  console.log(data)

  return data;
};