package com.sistemafarmacia.sistemafarmacia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sistemafarmacia.sistemafarmacia.model.Medicamento;
import com.sistemafarmacia.sistemafarmacia.service.MedicamentoService;
import com.sistemafarmacia.sistemafarmacia.service.CategoriaService;
import com.sistemafarmacia.sistemafarmacia.service.ProveedorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService service;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String listar(Model model,
                         @RequestParam(required = false, defaultValue = "") String buscar) {
        if (buscar.isEmpty()) {
            model.addAttribute("medicamentos", service.listarTodos());
        } else {
            model.addAttribute("medicamentos", service.buscarPorNombre(buscar));
        }
        model.addAttribute("buscar", buscar);
        model.addAttribute("alertas", service.alertasStockBajo());
        return "medicamentos/listar";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("medicamento", new Medicamento());
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("proveedores", proveedorService.listarTodos());
        return "medicamentos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Medicamento medicamento,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            model.addAttribute("proveedores", proveedorService.listarTodos());
            return "medicamentos/formulario";
        }
        service.guardar(medicamento);
        return "redirect:/medicamentos";
    }

    @GetMapping("/editar/{codigo}")
    public String formularioEditar(@PathVariable String codigo, Model model) {
        service.buscarPorCodigo(codigo).ifPresent(m -> model.addAttribute("medicamento", m));
        model.addAttribute("categorias", categoriaService.listarTodas());
        model.addAttribute("proveedores", proveedorService.listarTodos());
        return "medicamentos/formulario";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminar(@PathVariable String codigo) {
        service.eliminar(codigo);
        return "redirect:/medicamentos";
    }
}