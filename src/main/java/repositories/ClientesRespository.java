package repositories;

import models.ClienteInfo;

import java.util.List;
import java.util.Optional;

public interface ClientesRespository {

    List<ClienteInfo> getClientes();
    void guardar(ClienteInfo clienteInfo);
    Optional<ClienteInfo> buscarPorUUID(String uuid);
    ClienteInfo buscarPorUsername(String username);
    void actualizarEstado(String uuid, Integer estado);
    //quitar después
    Optional<ClienteInfo> login(String username, String password);


}
