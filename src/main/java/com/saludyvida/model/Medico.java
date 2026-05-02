package com.saludyvida.model;

public class Medico {
    private String cmp;
    private String nombre;
    private String especialidad;
    private String turno;

    public Medico() {}

    public Medico(String cmp, String nombre, String especialidad, String turno) {
        this.cmp = cmp;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.turno = turno;
    }

    public String getCmp() { return cmp; }
    public void setCmp(String cmp) { this.cmp = cmp; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }
}
