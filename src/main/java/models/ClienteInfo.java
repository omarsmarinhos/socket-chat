package models;

import java.io.Serializable;
import java.util.Objects;

public class ClienteInfo implements Serializable {

    private Long id;
    private String ip;
    private String uuid;
    private String username;
    private Integer status;
    private String password;
    private String nombres;
    private String apellidos;
    private String fechaNacimiento;
    private String numCelular;

    public ClienteInfo() {
    }

    public ClienteInfo(String ip, String uuid, String username, Integer status, String password, String nombres, String apellidos, String fechaNacimiento, String numCelular) {
        this.ip = ip;
        this.uuid = uuid;
        this.username = username;
        this.status = status;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.numCelular = numCelular;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteInfo that = (ClienteInfo) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(username, that.username);
    }

    @Override
    public String toString() {
        return "ClienteInfo{" +
                "id='" + id + '\'' +
                ",ip='" + ip + '\'' +
                ", uuid='" + uuid + '\'' +
                ", username='" + username + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", numCelular='" + numCelular + '\'' +
                '}';
    }
}