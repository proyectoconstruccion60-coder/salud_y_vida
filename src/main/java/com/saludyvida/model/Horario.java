package com.saludyvida.model;

public class Horario {
    private int id;
    private String medico;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private String sede;

    public Horario() {}

    public Horario(int id, String medico, String diaSemana,
                   String horaInicio, String horaFin, String sede) {
        this.id = id;
        this.medico = medico;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.sede = sede;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }
}
