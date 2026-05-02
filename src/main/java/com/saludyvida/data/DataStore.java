package com.saludyvida.data;

import com.saludyvida.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio en memoria — SIN BASE DE DATOS y SIN DATA HARDCODEADA.
 * Las listas comienzan VACÍAS. El usuario llena los datos desde la UI.
 * Actúa como "Data Controller" del diagrama en pizarra.
 */
@Component
public class DataStore {

    private final List<Paciente>     pacientes      = new ArrayList<>();
    private final List<Medico>       medicos        = new ArrayList<>();
    private final List<Especialidad> especialidades = new ArrayList<>();
    private final List<Cita>         citas          = new ArrayList<>();
    private final List<Horario>      horarios       = new ArrayList<>();
    private final List<Auditoria>    auditorias     = new ArrayList<>();

    private int nextCitaId = 1;
    private int nextEspId  = 1;
    private int nextHorId  = 1;
    private int nextAudId  = 1;

    // ---- AUDITORÍA ----
    public void registrarAuditoria(String modulo, String accion, String detalle) {
        auditorias.add(0, new Auditoria(nextAudId++, "admin", accion, modulo, detalle));
    }
    public List<Auditoria> getAuditorias() { return auditorias; }

    // ---- PACIENTES ----
    public List<Paciente> getPacientes() { return pacientes; }
    public void addPaciente(Paciente p) {
        pacientes.add(p);
        registrarAuditoria("Pacientes", "CREAR", "DNI: " + p.getDni() + " — " + p.getNombre());
    }
    public void deletePaciente(String dni) {
        Paciente p = findPaciente(dni);
        pacientes.removeIf(x -> x.getDni().equals(dni));
        if (p != null) registrarAuditoria("Pacientes", "ELIMINAR", "DNI: " + dni);
    }
    public Paciente findPaciente(String dni) {
        return pacientes.stream().filter(p -> p.getDni().equals(dni)).findFirst().orElse(null);
    }
    public void updatePaciente(Paciente u) {
        for (int i = 0; i < pacientes.size(); i++)
            if (pacientes.get(i).getDni().equals(u.getDni())) {
                pacientes.set(i, u);
                registrarAuditoria("Pacientes", "EDITAR", "DNI: " + u.getDni());
                return;
            }
    }

    // ---- MÉDICOS ----
    public List<Medico> getMedicos() { return medicos; }
    public void addMedico(Medico m) {
        medicos.add(m);
        registrarAuditoria("Médicos", "CREAR", "CMP: " + m.getCmp() + " — " + m.getNombre());
    }
    public void deleteMedico(String cmp) {
        Medico m = findMedico(cmp);
        medicos.removeIf(x -> x.getCmp().equals(cmp));
        if (m != null) registrarAuditoria("Médicos", "ELIMINAR", "CMP: " + cmp);
    }
    public Medico findMedico(String cmp) {
        return medicos.stream().filter(m -> m.getCmp().equals(cmp)).findFirst().orElse(null);
    }
    public void updateMedico(Medico u) {
        for (int i = 0; i < medicos.size(); i++)
            if (medicos.get(i).getCmp().equals(u.getCmp())) {
                medicos.set(i, u);
                registrarAuditoria("Médicos", "EDITAR", "CMP: " + u.getCmp());
                return;
            }
    }

    // ---- ESPECIALIDADES ----
    public List<Especialidad> getEspecialidades() { return especialidades; }
    public void addEspecialidad(Especialidad e) {
        e.setId(nextEspId++);
        especialidades.add(e);
        registrarAuditoria("Especialidades", "CREAR", e.getNombre());
    }
    public void deleteEspecialidad(int id) {
        Especialidad e = findEspecialidad(id);
        especialidades.removeIf(x -> x.getId() == id);
        if (e != null) registrarAuditoria("Especialidades", "ELIMINAR", e.getNombre());
    }
    public Especialidad findEspecialidad(int id) {
        return especialidades.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    public void updateEspecialidad(Especialidad u) {
        for (int i = 0; i < especialidades.size(); i++)
            if (especialidades.get(i).getId() == u.getId()) {
                especialidades.set(i, u);
                registrarAuditoria("Especialidades", "EDITAR", u.getNombre());
                return;
            }
    }

    // ---- CITAS ----
    public List<Cita> getCitas() { return citas; }
    public void addCita(Cita c) {
        c.setId(nextCitaId++);
        citas.add(c);
        registrarAuditoria("Citas", "CREAR", "Paciente: " + c.getPaciente());
    }
    public void deleteCita(int id) {
        Cita c = findCita(id);
        citas.removeIf(x -> x.getId() == id);
        if (c != null) registrarAuditoria("Citas", "ELIMINAR", "ID: " + id);
    }
    public Cita findCita(int id) {
        return citas.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
    public void updateCita(Cita u) {
        for (int i = 0; i < citas.size(); i++)
            if (citas.get(i).getId() == u.getId()) {
                citas.set(i, u);
                registrarAuditoria("Citas", "EDITAR", "ID: " + u.getId());
                return;
            }
    }

    // ---- HORARIOS ----
    public List<Horario> getHorarios() { return horarios; }
    public void addHorario(Horario h) {
        h.setId(nextHorId++);
        horarios.add(h);
        registrarAuditoria("Horarios", "CREAR", h.getMedico() + " — " + h.getDiaSemana());
    }
    public void deleteHorario(int id) {
        horarios.removeIf(x -> x.getId() == id);
        registrarAuditoria("Horarios", "ELIMINAR", "ID: " + id);
    }
    public Horario findHorario(int id) {
        return horarios.stream().filter(h -> h.getId() == id).findFirst().orElse(null);
    }
    public void updateHorario(Horario u) {
        for (int i = 0; i < horarios.size(); i++)
            if (horarios.get(i).getId() == u.getId()) {
                horarios.set(i, u);
                registrarAuditoria("Horarios", "EDITAR", "ID: " + u.getId());
                return;
            }
    }
}
