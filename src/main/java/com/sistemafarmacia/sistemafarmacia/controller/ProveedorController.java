package com.sistemafarmacia.sistemafarmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sistemafarmacia.sistemafarmacia.model.Proveedor;
import com.sistemafarmacia.sistemafarmacia.service.ProveedorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", service.listarTodos());
        return "proveedores/listar";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Proveedor proveedor,
                          BindingResult result) {
        if (result.hasErrors()) return "proveedores/formulario";
        service.guardar(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        service.buscarPorId(id).ifPresent(p -> model.addAttribute("proveedor", p));
        return "proveedores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/proveedores";
    }
}
