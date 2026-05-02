package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import com.saludyvida.model.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private DataStore dataStore;

    // ---- RESERVA (formulario nueva cita) ----
    @GetMapping("/reserva")
    public String mostrarReserva(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("especialidades", dataStore.getEspecialidades());
        return "sistema/reserva";
    }

    @PostMapping("/reserva/guardar")
    public String guardarReserva(@ModelAttribute Cita cita, RedirectAttributes ra) {
        cita.setEstado("Pendiente");
        dataStore.addCita(cita);
        ra.addFlashAttribute("exito", "Cita reservada correctamente.");
        return "redirect:/citas/gestion/buscar-redirect";
    }

    // ---- GESTIÓN (listado de citas) ----
    @GetMapping("/gestion")
    public String mostrarGestion(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("mostrarTabla", false);
        return "sistema/gestion";
    }

    @PostMapping("/gestion/buscar")
    public String mostrarTabla(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("citas", dataStore.getCitas());
        model.addAttribute("mostrarTabla", true);
        return "sistema/gestion";
    }

    @GetMapping("/gestion/buscar-redirect")
    public String buscarRedirect(Model model) {
        model.addAttribute("cita", new Cita());
        model.addAttribute("citas", dataStore.getCitas());
        model.addAttribute("mostrarTabla", true);
        return "sistema/gestion";
    }

    @GetMapping("/gestion/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Cita c = dataStore.findCita(id);
        model.addAttribute("cita", c != null ? c : new Cita());
        model.addAttribute("citas", dataStore.getCitas());
        model.addAttribute("medicos", dataStore.getMedicos());
        model.addAttribute("mostrarTabla", true);
        model.addAttribute("modoEdicion", true);
        return "sistema/gestion";
    }

    @PostMapping("/gestion/actualizar")
    public String actualizar(@ModelAttribute Cita cita, RedirectAttributes ra) {
        dataStore.updateCita(cita);
        ra.addFlashAttribute("exito", "Cita actualizada.");
        return "redirect:/citas/gestion/buscar-redirect";
    }

    @GetMapping("/gestion/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        dataStore.deleteCita(id);
        ra.addFlashAttribute("exito", "Cita eliminada.");
        return "redirect:/citas/gestion/buscar-redirect";
    }
}
