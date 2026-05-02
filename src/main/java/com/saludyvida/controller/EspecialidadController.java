package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import com.saludyvida.model.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/especialidades")
public class EspecialidadController {

    @Autowired
    private DataStore dataStore;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        model.addAttribute("mostrarTabla", false);
        return "sistema/especialidades";
    }

    @PostMapping("/buscar")
    public String mostrarTabla(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", true);
        return "sistema/especialidades";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Especialidad especialidad, RedirectAttributes ra) {
        dataStore.addEspecialidad(especialidad);
        ra.addFlashAttribute("exito", "Especialidad agregada.");
        return "redirect:/especialidades/buscar-redirect";
    }

    @GetMapping("/buscar-redirect")
    public String buscarRedirect(Model model) {
        model.addAttribute("especialidad", new Especialidad());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", true);
        return "sistema/especialidades";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Especialidad e = dataStore.findEspecialidad(id);
        model.addAttribute("especialidad", e != null ? e : new Especialidad());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", true);
        model.addAttribute("modoEdicion", true);
        return "sistema/especialidades";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Especialidad especialidad, RedirectAttributes ra) {
        dataStore.updateEspecialidad(especialidad);
        ra.addFlashAttribute("exito", "Especialidad actualizada.");
        return "redirect:/especialidades/buscar-redirect";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        dataStore.deleteEspecialidad(id);
        ra.addFlashAttribute("exito", "Especialidad eliminada.");
        return "redirect:/especialidades/buscar-redirect";
    }
}
