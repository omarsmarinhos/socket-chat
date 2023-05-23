package repositories;

import Vista.ClienteInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientesRepositoryImpl implements ClientesRespository {

    @Override
    public List<ClienteInfo> getClientes() {
        List<ClienteInfo> clientes = new ArrayList<>();

        try (Connection conn = Conexion.getInstance()) {
            try (Statement statement = conn.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes")) {

                    while (resultSet.next()) {
                        ClienteInfo cliente = getClienteInfo(resultSet);
                        clientes.add(cliente);
                    }

                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }

        return clientes;
    }

    @Override
    public void guardar(ClienteInfo clienteInfo) {
        try (Connection conn = Conexion.getInstance();
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO clientes (ip, username, password, status, nombres, apellidos, fecha_nacimiento, num_celular) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, clienteInfo.getIp());
            statement.setString(2, clienteInfo.getUsername());
            statement.setString(3, clienteInfo.getPassword());
            statement.setInt(4, 1);
            statement.setString(5, clienteInfo.getNombres());
            statement.setString(6, clienteInfo.getApellidos());
            statement.setString(6, clienteInfo.getFechaNacimiento());
            statement.setString(7, clienteInfo.getNumCelular());

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

        try (Connection conn = Conexion.getInstance();
             PreparedStatement statement = conn.prepareStatement(
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
    public void actualizarEstado(Integer estado) {

    }

    private static ClienteInfo getClienteInfo(ResultSet resultSet) throws SQLException {
        String ip = resultSet.getString("ip");
        String uuid = resultSet.getString("uuid");
        String username = resultSet.getString("username");
        String password = resultSet.getString("username");
        Integer status = resultSet.getInt("username");
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
