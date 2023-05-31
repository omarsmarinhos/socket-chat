package repositories;

import models.Mensaje;
import models.PaginadorParams;

import java.util.List;

public interface MensajesRepository {

    void crearMensaje(Mensaje mensaje);
    List<Mensaje> getMensajes(Long clienteId1, Long clienteId2, PaginadorParams paginadorParams);

}
