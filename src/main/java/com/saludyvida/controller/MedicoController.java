package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import com.saludyvida.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private DataStore dataStore;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("medico", new Medico());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", false);
        return "sistema/medicos";
    }

    @PostMapping("/buscar")
    public String mostrarTabla(Model model) {
        model.addAttribute("medico", new Medico());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", true);
        return "sistema/medicos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Medico medico, RedirectAttributes ra) {
        dataStore.addMedico(medico);
        ra.addFlashAttribute("exito", "Médico registrado correctamente.");
        return "redirect:/medicos/buscar-redirect";
    }

    @GetMapping("/buscar-redirect")
    public String buscarRedirect(Model model) {
        model.addAttribute("medico", new Medico());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", true);
        return "sistema/medicos";
    }

    @GetMapping("/editar/{cmp}")
    public String editar(@PathVariable String cmp, Model model) {
        Medico m = dataStore.findMedico(cmp);
        model.addAttribute("medico", m != null ? m : new Medico());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        model.addAttribute("mostrarTabla", true);
        model.addAttribute("modoEdicion", true);
        return "sistema/medicos";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Medico medico, RedirectAttributes ra) {
        dataStore.updateMedico(medico);
        ra.addFlashAttribute("exito", "Médico actualizado.");
        return "redirect:/medicos/buscar-redirect";
    }

    @GetMapping("/eliminar/{cmp}")
    public String eliminar(@PathVariable String cmp, RedirectAttributes ra) {
        dataStore.deleteMedico(cmp);
        ra.addFlashAttribute("exito", "Médico eliminado.");
        return "redirect:/medicos/buscar-redirect";
    }
}
