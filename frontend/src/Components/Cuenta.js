import '../css/Table.css';
import '../css/Button.css';
import React, { useEffect, useState } from "react";
import ModalCuenta from "../Modals/ModalCuenta";
import apiCuenta, { deleteCuenta } from "../Api/ApiCuenta";


export default function Cuenta() {
const [cuentas, setCuentas] = useState([]);

const [showModal, setShowModal] = useState(false);
const [contenido, setContenido] =useState(null);
const [opcion, setOpcion] = useState('');



  const openModal = (cuenta, opcion) => {
    
    setContenido(cuenta);
    setShowModal(true);
    setOpcion(opcion);
  };

  const closeModal = () => {
    setShowModal(false);
    setContenido(null);
  };



    const getCuentas= async()=>{
      try{
        const response = await apiCuenta.get("");
        setCuentas(response.data);
        
      }catch(error){
        console.error('Error obteniendo cuentas', error)
      }
    }


  useEffect(()=>{
    getCuentas();
  }, []);

  const handleDelete = async (id) =>{
    try {
      await deleteCuenta(id);
      setCuentas(cuentas.filter(cuenta => cuenta.id !== id));
      closeModal();
      console.log('eliminada la cuenta con id', id)
    } catch (error) {
      console.error('Error eliminando ', error);
    }
  }

 
    
  if (!cuentas) return null;

 

  return (
    <div>
      <>
      <h2>Cuentas de correo electrónico registradas</h2>
      <button className="add" onClick={()=>openModal([], 'nuevo') }>Nueva</button>
      </>
      <div className="table-container">
      <div className='table-wrapper'>
      <table border="1" >
        <thead>
          <tr>
            {/* <th>id</th> */}
            <th>correo</th>
            <th>acciones</th>
          </tr>
        </thead>
        <tbody>
          {cuentas.map((cuenta) => {
            return (
              <tr key={cuenta.id}>
                {/* <td>{cuenta.id}</td> */}
                <td>{cuenta.correo}</td>
                <td>
                <button className='edit' onClick={()=>openModal(cuenta, 'editar') }>Editar</button>
                <button className="delete" onClick={()=>openModal(cuenta, 'borrar')}>Eliminar</button>
                
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
      </div>
      </div>
      <ModalCuenta isOpen={showModal} onClose={closeModal} content={contenido} caso={opcion} onDelete={handleDelete} cuentas={cuentas} setCuentas={setCuentas} getCuentas={getCuentas}/>
    </div>
  );
}
