import { useState } from "react";

import "../css/Modal.css";
import "../css/Button.css";
import { createCuenta, updateCuenta } from "../Api/ApiCuenta";

const ModalCuenta = ({
  isOpen,
  onClose,
  content,
  caso,
  onDelete,
  cuentas,
  setCuentas,
  getCuentas,
}) => {
  const [correo, setCorreo] = useState("");

  const handleCreate = async (e) => {
    e.preventDefault();
    try {
      const cuenta = { correo };

      await createCuenta(cuenta);

      setCuentas([...cuentas, cuenta]);
      await getCuentas();
      handleClose();
    } catch (error) {
      console.error("Error creando cuenta ", error);
    }
  };
  const handleUpdate = async (e) => {
    console.log(cuentas);
    e.preventDefault();
    try {
      
      const editada = {correo};  
      const cuentaActualizada= await updateCuenta(content.id, editada);
    
      setCuentas(cuentas.map(cuenta => (cuenta.id === cuentaActualizada.id)? cuentaActualizada: cuenta));
      await getCuentas();
      setCorreo("");
      handleClose();
    } catch (error) {
      console.error("Error creando cuenta ", error);
    }
  };
  const handleClose =()=>{
    setCorreo("");
    onClose();
   
  };
  if (!isOpen) return null;

  switch (caso) {
    case "borrar":
      return (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={onClose}>
              &times;
            </span>
            <h2>Eliminar</h2>
            <p>
              ¿Desea eliminar la cuenta con correo {content.correo} e ID{" "}
              {content.id}?{" "}
            </p>
            <p>
              <i>Esta acción es permanente</i>
            </p>
            <footer>
              <button onClick={onClose} className="cancel">Cancelar</button>
              <button onClick={() => onDelete(content.id)} className="delete">Eliminar</button>
            </footer>
          </div>
        </div>
      );

    case "nuevo":
      return (
        <div>
          
          <div className="modal">
            <div className="modal-content">
              <span className="close" onClick={handleClose}>
                &times;
              </span>
              <h2>Agregar nueva cuenta de correo</h2>
              <div className="card-body">
                <form>
                  <div className="form-group mb-2">
                    <label className="form-label">
                      Correo de cuenta a crear
                    </label>
                    <br />
                    <input
                      type="email"
                      placeholder="cuenta@ejemplo.com"
                      name="correo"
                      className="form-control"
                      value={correo}
                      onChange={(e) => setCorreo(e.target.value)}
                    />
                  </div>

                  <button onClick={handleClose}
                  className="cancel">Cancelar</button>
                  <button
                    disabled={correo.length===0}
                    className="confirm"
                    onClick={(e) => handleCreate(e)}
                  >
                    Guardar
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      );
    case "editar":
      return (
        <div>
          
          <div className="modal">
            <div className="modal-content">
              <span className="close" onClick={onClose}>
                &times;
              </span>
              <h2>Editar {content.correo}</h2>
              <div className="card-body">
                <form>
                  <div className="form-group mb-2">
                    <label className="form-label">
                      Correo de cuenta a editar
                    </label>
                    <br />
                    <input
                      type="email"
                      placeholder="cuenta@ejemplo.com"
                      name="correo"
                      className="form-control"
                      defaultValue={content.correo}
                      onChange={(e) => setCorreo(e.target.value)}
                    />
                  </div>
                  
                  
                   
                  <button onClick={onClose}
                  className="cancel">Cancelar</button>
                  <button
                    className="confirm"
                    onClick={(e) => handleUpdate(e)}
                  >
                    Guardar
                  </button>
                  
                </form>
              </div>
            </div>
          </div>
        </div>
      );
    default:
  }

  return <div></div>;
};
export default ModalCuenta;
