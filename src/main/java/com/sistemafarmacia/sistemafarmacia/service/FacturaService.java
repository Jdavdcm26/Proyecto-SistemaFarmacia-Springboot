package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Factura;
import com.sistemafarmacia.sistemafarmacia.model.Venta;
import com.sistemafarmacia.sistemafarmacia.repository.FacturaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repository;

    public Factura generarFactura(Venta venta) {
        Factura factura = new Factura();
        factura.setVenta(venta);
        factura.setFechaEmision(LocalDate.now());
        factura.setTotal(venta.getTotal());
        return repository.save(factura);
    }

    public Optional<Factura> buscarPorVenta(Long ventaId) {
        return repository.findByVentaId(ventaId);
    }

    public Optional<Factura> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Factura> listarTodas() {
        return repository.findAll();
    }
}
