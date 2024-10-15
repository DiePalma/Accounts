import { useEffect, useState } from "react";

import ModalSuscripcion from "../Modals/ModalSuscripcion";
import apiRegistro, { updateEstado } from "../Api/ApiRegistro";
import apiTipoCuenta from "../Api/ApiTipoCuenta";
import apiCuenta from "../Api/ApiCuenta";
import "../css/Table.css";

export default function Suscripcion() {
  const [suscripciones, setSuscripciones] = useState([]);
  const [contenido, setContenido] = useState(null);
  const [opcion, setOpcion] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [tiposCuenta, setTiposCuenta] = useState([]);
  const [estadoSeleccionado, setEstadoSeleccionado] = useState("");
  const [tipoCuentaSeleccionado, setTipoCuentaSeleccionado] = useState("");
  const [suscripcionesFiltradas, setSuscripcionesFiltradas] = useState([]);
  const [cuentas, setCuentas] = useState([]);
  
  const openModal = (suscripcion, opcion) => {
    setContenido(suscripcion);
    setShowModal(true);
    setOpcion(opcion);
  };

  const closeModal = () => {
    setShowModal(false);
    setContenido(null);
  };

  const getTiposCuenta = async () => {
    try {
      const response = await apiTipoCuenta.get("");

      setTiposCuenta(response.data);
    } catch (error) {
      console.error("Error obteniendo lista de plataformas", error);
    }
  };
  useEffect(() => {
    getTiposCuenta();
  }, []);

  const getCuentas = async () => {
    try {
      const response = await apiCuenta.get("");

      setCuentas(response.data);
    } catch (error) {
      console.error("Error obteniendo cuentas", error);
    }
  };
  useEffect(() => {
    getCuentas();
  }, []);
  const getSuscripciones = async () => {
    try {
      const response = await apiRegistro.get("");

      setSuscripciones(response.data);
      setSuscripcionesFiltradas(response.data);
    } catch (error) {
      console.error("Error obteniendo suscripciones", error);
    }
  };
  useEffect(() => {
    getSuscripciones();
  }, []);

  const handleEstadoChange = (event) => {
    setEstadoSeleccionado(event.target.value);
  };

  const handleTipoCuentaChange = (event) => {
    setTipoCuentaSeleccionado(event.target.value);
  };
  const handleEstadoUpdate =async (event, suscripcion)=>{
    event.preventDefault();

    const cambio=event.target.value;

     await updateEstado(suscripcion.id, cambio);
     getSuscripciones();
  }

  useEffect(() => {
    let filtrado = suscripciones;
    if (estadoSeleccionado) {
      filtrado = filtrado.filter(
        (suscripcion) => suscripcion.estado === estadoSeleccionado
      );
    }
    if (tipoCuentaSeleccionado) {
      filtrado = filtrado.filter(
        (suscripcion) =>
          suscripcion.tipoCuenta.nombre === tipoCuentaSeleccionado
      );
    }
    setSuscripcionesFiltradas(filtrado);
  }, [estadoSeleccionado, tipoCuentaSeleccionado, suscripciones]);

  if (!suscripciones) return null;
  return (
    <div>
      <h2>Estado de cuentas registradas</h2>
      <button className="add" onClick={() => openModal([], "nuevo")}>Nueva</button>
      {suscripcionesFiltradas.length > 0 ? (
        <div className="table-container" >
        <div className="table-wrapper">
        <table border="1">
          <thead>
            <tr>
              {/* <th>id</th> */}
              <th>Cuenta</th>
              <th>
                Tipo
                <br />
                {
                  <select id="tipos" onChange={handleTipoCuentaChange}>
                    <option value="">Cualquiera</option>
                    {tiposCuenta.map((tipo) => (
                      <option key={tipo.id} value={tipo.nombre}>
                        {tipo.nombre}
                      </option>
                    ))}
                  </select>
                }
              </th>
              <th>
                Estado
                <br />
                <select id="estados" onChange={handleEstadoChange}>
                  <option value="">Cualquiera</option>
                  <option value="Disponible">Disponible</option>
                  <option value="En uso">En uso</option>
                  <option value="Bloqueada">Bloqueada</option>
                </select>
              </th>
              <th>Cambiar<br/>Estado</th>
            </tr>
          </thead>
          <tbody>
            {suscripcionesFiltradas.map((suscripcion) => {
              return (
                <tr key={suscripcion.id}>
                  {/* <td>{suscripcion.id}</td> */}
                  <td>{suscripcion.cuenta.correo}</td>
                  <td>{suscripcion.tipoCuenta.nombre}</td>
                  <td>{suscripcion.estado}</td>
                  <td>
                  <select id="estados" 
                  onChange={(e)=>{
                    if (!(e.target.value==="Seleccione")) {
                      handleEstadoUpdate(e, suscripcion);
                    }
                  } 
                  }
                  /*onChange={(e)=>handleEstadoUpdate(e, suscripcion)}*/>
                  <option value="Seleccione">Seleccione</option>
                  <option value="Disponible">Disponible</option>
                  <option value="En uso">En uso</option>
                  <option value="Bloqueada">Bloqueada</option>
                </select>
                    </td>
                </tr>
              );
            })}
          </tbody>
        </table>
        </div>
        </div>
      ) : (
        <p>
          No se encontraron suscripciones que coincidan con los criterios
          solicitados.
         <br/><button className="Button" style={{background:"#FFFFFF"}}onClick={handleEstadoChange} value={""}>Refrescar</button>
        </p>
        
      )}
      
      <ModalSuscripcion
        isOpen={showModal}
        onClose={closeModal}
        content={contenido}
        caso={opcion}
        cuentas={cuentas}
        setCuentas={setCuentas}
        getCuentas={getCuentas}
        tiposCuenta={tiposCuenta}
        setTiposCuenta={setTiposCuenta}
        getTiposCuenta={getTiposCuenta}
        getSuscripciones={getSuscripciones}
        setSuscripciones={setSuscripciones}
        suscripciones={suscripciones}
      />
    </div>
  );
}
