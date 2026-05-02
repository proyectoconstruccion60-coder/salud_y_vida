package com.saludyvida.model;

public class Paciente {
    private String dni;
    private String nombre;
    private String celular;
    private String correo;

    public Paciente() {}

    public Paciente(String dni, String nombre, String celular, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
    }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
