package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import com.saludyvida.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private DataStore dataStore;

    /**
     * GET /pacientes → muestra el formulario SIN tabla (mostrarTabla = false)
     */
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("mostrarTabla", false);
        return "sistema/pacientes";
    }

    /**
     * POST /pacientes/buscar → presionar botón "Ver Pacientes" muestra la tabla
     */
    @PostMapping("/buscar")
    public String mostrarTabla(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("pacientes", dataStore.getPacientes());
        model.addAttribute("mostrarTabla", true);
        return "sistema/pacientes";
    }

    /**
     * POST /pacientes/guardar → registra un nuevo paciente
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Paciente paciente, RedirectAttributes ra) {
        if (paciente.getDni() == null || paciente.getDni().isBlank()) {
            ra.addFlashAttribute("error", "El DNI es obligatorio.");
            return "redirect:/pacientes";
        }
        dataStore.addPaciente(paciente);
        ra.addFlashAttribute("exito", "Paciente registrado correctamente.");
        return "redirect:/pacientes/buscar-redirect";
    }

    /** Redirige mostrando la tabla luego de guardar */
    @GetMapping("/buscar-redirect")
    public String buscarRedirect(Model model) {
        model.addAttribute("paciente", new Paciente());
        model.addAttribute("pacientes", dataStore.getPacientes());
        model.addAttribute("mostrarTabla", true);
        return "sistema/pacientes";
    }

    /**
     * GET /pacientes/editar/{dni} → carga datos en el formulario para editar
     */
    @GetMapping("/editar/{dni}")
    public String editar(@PathVariable String dni, Model model) {
        Paciente p = dataStore.findPaciente(dni);
        model.addAttribute("paciente", p != null ? p : new Paciente());
        model.addAttribute("pacientes", dataStore.getPacientes());
        model.addAttribute("mostrarTabla", true);
        model.addAttribute("modoEdicion", true);
        return "sistema/pacientes";
    }

    /**
     * POST /pacientes/actualizar → guarda cambios del paciente editado
     */
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Paciente paciente, RedirectAttributes ra) {
        dataStore.updatePaciente(paciente);
        ra.addFlashAttribute("exito", "Paciente actualizado correctamente.");
        return "redirect:/pacientes/buscar-redirect";
    }

    /**
     * GET /pacientes/eliminar/{dni} → elimina paciente
     */
    @GetMapping("/eliminar/{dni}")
    public String eliminar(@PathVariable String dni, RedirectAttributes ra) {
        dataStore.deletePaciente(dni);
        ra.addFlashAttribute("exito", "Paciente eliminado.");
        return "redirect:/pacientes/buscar-redirect";
    }
}
