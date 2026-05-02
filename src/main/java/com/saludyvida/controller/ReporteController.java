package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private DataStore dataStore;

    @GetMapping
    public String mostrarReportes(Model model) {
        model.addAttribute("totalPacientes", dataStore.getPacientes().size());
        model.addAttribute("totalMedicos",   dataStore.getMedicos().size());
        model.addAttribute("totalCitas",     dataStore.getCitas().size());
        model.addAttribute("especialidades", dataStore.getEspecialidades());

        long confirmadas = dataStore.getCitas().stream()
            .filter(c -> "Confirmado".equals(c.getEstado())).count();
        long pendientes  = dataStore.getCitas().stream()
            .filter(c -> "Pendiente".equals(c.getEstado())).count();

        model.addAttribute("citasConfirmadas", confirmadas);
        model.addAttribute("citasPendientes",  pendientes);
        return "sistema/reportes";
    }
}
