package repositories;

import Vista.ClienteInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientesRepositoryImpl implements ClientesRespository {

    public Connection getConnection() throws SQLException {
        return Conexion.getInstance();
    }

    @Override
    public List<ClienteInfo> getClientes() {
        List<ClienteInfo> clientes = new ArrayList<>();

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM clientes " +
                             "WHERE status = 1")) {

            while (resultSet.next()) {
                ClienteInfo cliente = getClienteInfo(resultSet);
                clientes.add(cliente);
            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return clientes;
    }

    @Override
    public void guardar(ClienteInfo clienteInfo) {
        try (PreparedStatement statement = getConnection().prepareStatement(
                "INSERT INTO clientes (ip, uuid, username, password, status, nombres, apellidos, fecha_nacimiento, num_celular) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, clienteInfo.getIp());
            statement.setString(2, clienteInfo.getUuid());
            statement.setString(3, clienteInfo.getUsername());
            statement.setString(4, clienteInfo.getPassword());
            statement.setInt(5, 2);
            statement.setString(6, clienteInfo.getNombres());
            statement.setString(7, clienteInfo.getApellidos());
            statement.setString(8, clienteInfo.getFechaNacimiento());
            statement.setString(9, clienteInfo.getNumCelular());

            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Usuario agregado correctamente");
            } else {
                System.out.println("No se pudo agregar el usuario");
            }

        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    @Override
    public Optional<ClienteInfo> buscarPorUUID(String uuid) {
        Optional<ClienteInfo> clienteInfoOptional = Optional.empty();

        try (PreparedStatement statement = getConnection().prepareStatement(
                     "SELECT * FROM clientes " +
                             "WHERE uuid = ?")) {

            statement.setString(1, uuid);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    ClienteInfo clienteInfo = getClienteInfo(resultSet);
                    clienteInfoOptional = Optional.of(clienteInfo);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por nombre: " + e.getMessage());
        }

        return clienteInfoOptional;
    }

    @Override
    public ClienteInfo buscarPorUsername(String username) {
        ClienteInfo clienteInfo = null;

        try (PreparedStatement statement = getConnection().prepareStatement(
                     "SELECT * FROM clientes " +
                             "WHERE username = ?")) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    clienteInfo = getClienteInfo(resultSet);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por nombre: " + e.getMessage());
        }

        return clienteInfo;
    }

    @Override
    public void actualizarEstado(String uuid, Integer estado) {
        Optional<ClienteInfo> clienteInfoOptional = buscarPorUUID(uuid);

        if (clienteInfoOptional.isEmpty()) {
            throw new RuntimeException("Cliente con uuid " + uuid + " no existe");
        }

        try (PreparedStatement statement = getConnection().prepareStatement(
                     "UPDATE clientes SET status = ? WHERE uuid = ?")) {

            statement.setInt(1, estado);
            statement.setString(2, uuid);

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Estado del cliente actualzido");
            } else {
                System.out.println("No se puede actualizar el estado del cliente.");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del cliente.");
        }

    }

    @Override
    public Optional<ClienteInfo> login(String username, String password) {
        return Optional.ofNullable(buscarPorUsername(username))
                .filter(c -> c.getPassword().equals(password));
    }


    private static ClienteInfo getClienteInfo(ResultSet resultSet) throws SQLException {
        String ip = resultSet.getString("ip");
        String uuid = resultSet.getString("uuid");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Integer status = resultSet.getInt("status");
        String nombres = resultSet.getString("nombres");
        String apellidos = resultSet.getString("apellidos");
        String fechaNacimiento = resultSet.getString("fecha_nacimiento");
        String numCelular = resultSet.getString("num_celular");

        ClienteInfo cliente = new ClienteInfo();

        cliente.setIp(ip);
        cliente.setUuid(uuid);
        cliente.setUsername(username);
        cliente.setPassword(password);
        cliente.setStatus(status);
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setNombres(numCelular);

        return cliente;
    }

}
