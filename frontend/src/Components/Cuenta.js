import '../css/Table.css';
import '../css/Button.css';
import React, { useEffect, useState } from "react";
import ModalCuenta from "../Modals/ModalCuenta";
import { deleteCuenta, fetchCuentas } from "../Api/ApiCuenta";


export default function Cuenta() {
const [cuentas, setCuentas] = useState([]);
const [page, setPage]= useState(0);
const [totalPages, setTotalPages]= useState(0);
const [showModal, setShowModal] = useState(false);
const [contenido, setContenido] =useState(null);
const [opcion, setOpcion] = useState('');
const pageSize=2;



  const openModal = (cuenta, opcion) => {
    
    setContenido(cuenta);
    setShowModal(true);
    setOpcion(opcion);
  };

  const closeModal = () => {
    setShowModal(false);
    setContenido(null);
  };



    const getCuentas= async(page)=>{
      try{
        const data = await fetchCuentas(page, pageSize);
        setCuentas(data.content);
        setTotalPages(data.totalPages)
        
      }catch(error){
        console.error('Error obteniendo cuentas', error)
      }
    }


  useEffect(()=>{
    getCuentas(page);
  }, [page]);

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

  const handlePreviousPage = () => {
    if (page > 0) {
        setPage(page - 1);
    }
};

const handleNextPage = () => {
    if (page < totalPages - 1) {
        setPage(page + 1);
    }
};
 
    
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
      <br/>
      <button onClick={handlePreviousPage} disabled={page === 0}>Anterior</button>
      <button onClick={handleNextPage} disabled={page===totalPages-1}>Siguiente</button>
      
      </div>
      </div>
      <ModalCuenta isOpen={showModal} onClose={closeModal} content={contenido} caso={opcion} onDelete={handleDelete} cuentas={cuentas} setCuentas={setCuentas} getCuentas={getCuentas}/>
    </div>
  );
}
