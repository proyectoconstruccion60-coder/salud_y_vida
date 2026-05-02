package com.saludyvida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicoController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "publico/index";
    }

    @GetMapping("/login")
    public String login() {
        return "publico/login";
    }

    @GetMapping("/ingresar")
    public String ingresar() {
        return "redirect:/citas/reserva";
    }

    @GetMapping("/reserva")
    public String reservaShortcut() {
        return "redirect:/citas/reserva";
    }
}
