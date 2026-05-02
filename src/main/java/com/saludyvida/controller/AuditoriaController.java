package com.saludyvida.controller;

import com.saludyvida.data.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired
    private DataStore dataStore;

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("mostrarTabla", false);
        return "sistema/auditoria";
    }

    /** Botón "Ver Registro de Auditoría" → muestra la tabla */
    @PostMapping("/buscar")
    public String mostrarTabla(Model model) {
        model.addAttribute("auditorias", dataStore.getAuditorias());
        model.addAttribute("mostrarTabla", true);
        return "sistema/auditoria";
    }
}
