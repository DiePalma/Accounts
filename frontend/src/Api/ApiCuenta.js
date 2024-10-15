import axios from "axios";


const apiCuenta = axios.create({
    baseURL : "http://localhost:8080/cuenta",
    headers : {
        'Content-Type': 'application/json'
    }
});

export const deleteCuenta = async (id) =>{
    try {
        await apiCuenta.delete(`/${id}`);
        
      } catch (error) {
        console.error('Error eliminando ', error);
        throw error;
      }
}

export const createCuenta = async (cuenta) =>{
  try {
    const response = await apiCuenta.post(`/nueva`, cuenta);
   // console.log(response.data)
    return response.data;
  } catch (error) {
    console.error ('Error creando la cuenta', error);
    throw error;
  }
}
export const updateCuenta = async (id, cuenta) =>{
  try {
    const response = await apiCuenta.put(`/${id}`, cuenta);
   // console.log(response.data)
    return response.data;
  } catch (error) {
    console.error ('Error editando la cuenta', error);
    throw error;
  }
}


export default apiCuenta;