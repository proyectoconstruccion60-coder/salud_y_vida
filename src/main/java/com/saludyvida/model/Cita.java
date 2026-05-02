package com.saludyvida.model;

public class Cita {
    private int id;
    private String dniPaciente;
    private String paciente;
    private String medico;
    private String fechaHora;
    private String estado;
    private String prioridad;

    public Cita() {}

    public Cita(int id, String dniPaciente, String paciente, String medico,
                String fechaHora, String estado, String prioridad) {
        this.id = id;
        this.dniPaciente = dniPaciente;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.prioridad = prioridad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDniPaciente() { return dniPaciente; }
    public void setDniPaciente(String dniPaciente) { this.dniPaciente = dniPaciente; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public String getFechaHora() { return fechaHora; }
    public void setFechaHora(String fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getPrioridad() { return prioridad; }
    public void setPrioridad(String prioridad) { this.prioridad = prioridad; }
}
