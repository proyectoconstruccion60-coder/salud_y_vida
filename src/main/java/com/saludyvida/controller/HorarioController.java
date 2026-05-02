package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import com.saludyvida.model.Horario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private DataStore dataStore;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("horario", new Horario());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("mostrarTabla", false);
        return "sistema/horarios";
    }

    /** Botón "Ver Horarios" → muestra la tabla */
    @PostMapping("/buscar")
    public String mostrarTabla(Model model) {
        model.addAttribute("horario", new Horario());
        model.addAttribute("horarios", dataStore.getHorarios());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("mostrarTabla", true);
        return "sistema/horarios";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Horario horario, RedirectAttributes ra) {
        dataStore.addHorario(horario);
        ra.addFlashAttribute("exito", "Horario registrado correctamente.");
        return "redirect:/horarios/buscar-redirect";
    }

    @GetMapping("/buscar-redirect")
    public String buscarRedirect(Model model) {
        model.addAttribute("horario", new Horario());
        model.addAttribute("horarios", dataStore.getHorarios());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("mostrarTabla", true);
        return "sistema/horarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Horario h = dataStore.findHorario(id);
        model.addAttribute("horario", h != null ? h : new Horario());
        model.addAttribute("horarios", dataStore.getHorarios());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("mostrarTabla", true);
        model.addAttribute("modoEdicion", true);
        return "sistema/horarios";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Horario horario, RedirectAttributes ra) {
        dataStore.updateHorario(horario);
        ra.addFlashAttribute("exito", "Horario actualizado.");
        return "redirect:/horarios/buscar-redirect";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        dataStore.deleteHorario(id);
        ra.addFlashAttribute("exito", "Horario eliminado.");
        return "redirect:/horarios/buscar-redirect";
    }
}
