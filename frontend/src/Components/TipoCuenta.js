import React, { useEffect, useState } from "react";
import ModalTipoCuenta from "../Modals/ModalTipoCuenta";
import { deleteTipoCuenta, fetchTiposCuenta } from "../Api/ApiTipoCuenta";
import "../css/Table.css";

export default function TipoCuenta() {
  const [tipos, setTipos] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [showModal, setShowModal] = useState(false);
  const [contenido, setContenido] = useState(null);
  const [opcion, setOpcion] = useState("");
  const pageSize = 2;

  const openModal = (tipo, opcion) => {
    setContenido(tipo);
    setShowModal(true);
    setOpcion(opcion);
  };

  const closeModal = () => {
    setShowModal(false);
    setContenido(null);
  };

  const getTipos = async (page) => {
    try {
      const data = await fetchTiposCuenta(page, pageSize);
      setTipos(data.content);
      setTotalPages(data.totalPages);
    } catch (error) {
      console.error("Error obteniendo lista de plataformas", error);
    }
  };
  useEffect(() => {
    getTipos(page);
  }, [page]);

  const handleDelete = async (id) => {
    try {
      await deleteTipoCuenta(id);
      setTipos(tipos.filter((tipo) => tipo.id !== id));
      closeModal();
      <span>eliminada la plataforma con id {id}</span>;
    } catch (error) {
      console.error("Error eliminando ", error);
    }
  };

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
  if (!tipos) return null;

  return (
    <div>
      <>
        <h2>Sitios Registrados</h2>
        <button className="add" onClick={() => openModal([], "nuevo")}>
          Nuevo
        </button>
      </>
      <div className="table-container">
        <div className="table-wrapper">
          <table border="1">
            <thead>
              <tr>
                {/* <th>id</th> */}
                <th>Nombre</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {tipos.map((tipo) => {
                return (
                  <tr key={tipo.id}>
                    {/* <td>{tipo.id}</td> */}
                    <td>{tipo.nombre}</td>
                    <td>
                      <button
                        className="edit"
                        onClick={() => openModal(tipo, "editar")}
                      >
                        Editar
                      </button>
                      <button
                        className="delete"
                        onClick={() => openModal(tipo, "borrar")}
                      >
                        Eliminar
                      </button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
          <br />
          <button onClick={handlePreviousPage} disabled={page === 0}>
            Anterior
          </button>
          <button onClick={handleNextPage} disabled={page === totalPages - 1}>
            Siguiente
          </button>
        </div>
      </div>
      <ModalTipoCuenta
        isOpen={showModal}
        onClose={closeModal}
        content={contenido}
        caso={opcion}
        onDelete={handleDelete}
        tipos={tipos}
        setTipos={setTipos}
        getTipos={getTipos}
      />
    </div>
  );
}
