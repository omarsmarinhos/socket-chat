package repositories;

import models.Estado;
import models.ClienteInfo;
import models.Mensaje;
import models.PaginadorParams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MensajesRepositoryImpl implements MensajesRepository{

    public Connection getConnection() throws SQLException {
        return Conexion.getInstance();
    }

    @Override
    public void crearMensaje(Mensaje mensaje) {
        try (PreparedStatement statement = getConnection().prepareStatement(
                "INSERT INTO mensajes (remitente_id, destinatario_id, contenido, fecha_envio) " +
                        "VALUES (?, ?, ?, ?)")) {

            statement.setLong(1, mensaje.getRemitenteId());
            statement.setLong(2, mensaje.getDestinatarioId());
            statement.setString(3, mensaje.getContenido());

            LocalDateTime dateTime = LocalDateTime.now();
            String dateText = dateTime.toString();

            statement.setString(4, dateText);

            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Mensaje agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el mensaje");
            }

        } catch (SQLException e) {
            System.err.println("Error al agregar mensaje: " + e.getMessage());
        }
    }

    @Override
    public List<Mensaje> getMensajes(Long clienteId1, Long clienteId2, PaginadorParams paginadorParams) {

        int page = paginadorParams.getPage();
        int pageSize = paginadorParams.getPageSize();

        List<Mensaje> chatMensajes = new ArrayList<>();

        try (PreparedStatement statement = getConnection().prepareStatement(
                "SELECT m.*, c.username " +
                        "FROM mensajes as m " +
                        "JOIN clientes as c ON m.remitente_id = c.id " +
                        "WHERE (remitente_id = ? AND destinatario_id = ?) " +
                        "OR (remitente_id = ? AND destinatario_id = ?) " +
                        "ORDER BY fecha_envio LIMIT ? OFFSET ?"
        )) {
            statement.setLong(1, clienteId1);
            statement.setLong(2, clienteId2);
            statement.setLong(3, clienteId2);
            statement.setLong(4, clienteId1);
            statement.setInt(5, pageSize);
            statement.setInt(6, (page - 1) * pageSize);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Mensaje mensaje = getMensaje(resultSet);
                    chatMensajes.add(mensaje);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chatMensajes;
    }

    private static Mensaje getMensaje(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long remitenteId = resultSet.getLong("remitente_id");
        Long destinatarioId = resultSet.getLong("destinatario_id");
        String contenido = resultSet.getString("contenido");
        String fechaEnvio = resultSet.getString("fecha_envio");
        String remitenteUsername = resultSet.getString("username");

        LocalDateTime fecha = LocalDateTime.parse(fechaEnvio);

        Mensaje mensaje = new Mensaje();
        mensaje.setId(id);
        mensaje.setRemitenteId(remitenteId);
        mensaje.setDestinatarioId(destinatarioId);
        mensaje.setContenido(contenido);
        mensaje.setFechaEnvio(fecha);
        mensaje.setRemitenteUsername(remitenteUsername);

        return mensaje;
    }
}
