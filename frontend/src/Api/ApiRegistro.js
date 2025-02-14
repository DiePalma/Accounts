import axios from "axios";


const apiSuscripcion = axios.create({
    baseURL : "http://localhost:8080/suscripcion",
    headers : {
        'Content-Type': 'application/json'
    }
});

export const fetchSuscripciones = async(page = 0, pageSize = 5)=>{
  try {
    const response = await apiSuscripcion.get("",{
      params:{
        page: page,
        size: pageSize
      }
    });
    return response.data;
  } catch (error) {
    console.error('Error obteniendo suscripciones paginadas ', error);
    throw error;
  }
}
export const createSuscripcion = async (suscripcion) =>{
  try {
   
    const cSus=suscripcion;

   
    const response = await apiSuscripcion.post(`/nueva`, cSus);
   
    return response.data;
  } catch (error) {
    console.error ('Error creando la suscripcion', error);
    throw error;
  }
}

export const updateEstado = async (id, cambio) =>{
  try {
    const response = await apiSuscripcion.put(`/actualizarEstado/${id}/${cambio}`);
   
    return response.data;
  } catch (error) {
    console.error ('Error editando la suscripcion', error);
    throw error;
  }
}


export default apiSuscripcion;