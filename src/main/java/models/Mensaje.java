package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Mensaje implements Serializable {

    private Long id;
    private Long remitenteId;
    private String remitenteUsername;
    private Long destinatarioId;
    private String contenido;
    private LocalDateTime fechaEnvio;

    public Mensaje() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(Long remitenteId) {
        this.remitenteId = remitenteId;
    }

    public String getRemitenteUsername() {
        return remitenteUsername;
    }

    public void setRemitenteUsername(String remitenteUsername) {
        this.remitenteUsername = remitenteUsername;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
