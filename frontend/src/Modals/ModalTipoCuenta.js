import { useState } from "react";
import { createTipoCuenta,  updateTipoCuenta } from "../Api/ApiTipoCuenta";
import "../css/Modal.css";

const ModalTipoCuenta = ({
  isOpen,
  onClose,
  content,
  caso,
  onDelete,
  tipos,
  setTipos,
  getTipos,
}) => {
  const [nombre, setNombre] = useState("");
  
  
  const handleCreate = async (e) => {
    try {
      e.preventDefault();
      const tipo = { nombre };

      await createTipoCuenta(tipo);

      setTipos([...tipos, tipo]);
      await getTipos();
      handleClose();
    } catch (error) {
      console.error("Error creando plataforma ", error);
    }
  };
  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      const editada = { nombre };
      const tipoActualizado = await updateTipoCuenta(content.id, editada);

      setTipos(
        tipos.map((tipo) =>
          tipo.id === tipoActualizado.id ? tipoActualizado : tipo
        )
      );
      await getTipos();
      setNombre("");
      handleClose();
    } catch (error) {
      console.error("Error creando cuenta ", error);
    }
  };
  const handleClose =()=>{
    setNombre("");
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
            <p>¿Desea eliminar la plataforma {content.nombre}? </p>
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
    case "editar":
      return (
        <div>
          <div className="modal">
            <div className="modal-content">
              <span className="close" onClick={handleClose}>
                &times;
              </span>
              <h2>Editar {content.nombre}</h2>
              <div className="card-body">
                <form>
                  <div className="form-group mb-2">
                    <label className="form-label">
                      Edición del nombre de la plataforma
                    </label>
                    <br />
                    <input
                      type="text"
                      placeholder={content.nombre}
                      name="nombre"
                      className="form-control"
                      defaultValue={content.nombre}
                      //value={content.nombre}
                      onChange={(e) => setNombre(e.target.value)}
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
    case "nuevo":
      return (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={handleClose}>
              &times;
            </span>
            <h2>Agregar nueva plataforma</h2>
            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <label className="form-label">
                    Plataforma a agregar
                  </label>
                  <br />
                  <input
                    type="text"
                    placeholder="Netflix"
                    name="nombre"
                    className="form-control"
                    value={nombre}
                    onChange={(e) => setNombre(e.target.value)}
                  />
                </div>

                <button onClick={handleClose}
                className="cancel">Cancelar</button>
                <button
                  className="confirm"
                  disabled={nombre.length===0}
                  onClick={(e) => handleCreate(e)}
                >
                  Guardar
                </button>
              </form>
            </div>
          </div>
        </div>
      );
    
    default:
  }

  return <div></div>;
};
export default ModalTipoCuenta;
