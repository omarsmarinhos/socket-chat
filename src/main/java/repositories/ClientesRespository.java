package repositories;

import Vista.ClienteInfo;

import java.util.List;
import java.util.Optional;

public interface ClientesRespository {

    List<ClienteInfo> getClientes();
    void guardar(ClienteInfo clienteInfo);
    Optional<ClienteInfo> buscarPorUUID(String uuid);
    void actualizarEstado(Integer estado);

}
