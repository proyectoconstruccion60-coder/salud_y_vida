package com.saludyvida.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Auditoria {
    private int id;
    private String usuario;
    private String accion;      // CREAR, EDITAR, ELIMINAR
    private String modulo;      // Pacientes, Médicos, Citas, etc.
    private String detalle;
    private String fechaHora;

    public Auditoria() {}

    public Auditoria(int id, String usuario, String accion,
                     String modulo, String detalle) {
        this.id = id;
        this.usuario = usuario;
        this.accion = accion;
        this.modulo = modulo;
        this.detalle = detalle;
        this.fechaHora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getAccion() { return accion; }
    public void setAccion(String accion) { this.accion = accion; }

    public String getModulo() { return modulo; }
    public void setModulo(String modulo) { this.modulo = modulo; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }
}
