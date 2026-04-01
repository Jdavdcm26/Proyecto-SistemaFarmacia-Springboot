package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Compra;
import com.sistemafarmacia.sistemafarmacia.model.DetalleCompra;
import com.sistemafarmacia.sistemafarmacia.repository.CompraRepository;
import com.sistemafarmacia.sistemafarmacia.repository.DetalleCompraRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private DetalleCompraRepository detalleRepository;

    @Autowired
    private MedicamentoService medicamentoService;

    public Compra registrar(Compra compra, List<DetalleCompra> detalles) {
        // Calcular total
        double total = detalles.stream()
                .mapToDouble(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();

        compra.setFecha(LocalDate.now());
        compra.setTotal(total);
        Compra compraGuardada = compraRepository.save(compra);

        // Guardar detalles y actualizar stock
        for (DetalleCompra detalle : detalles) {
            detalle.setCompra(compraGuardada);
            detalle.setSubtotal(detalle.getPrecioUnitario() * detalle.getCantidad());
            detalleRepository.save(detalle);

            // Aumentar stock del medicamento
            medicamentoService.aumentarStock(
                detalle.getMedicamento().getCodigo(),
                detalle.getCantidad()
            );
        }

        return compraGuardada;
    }

    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    public Optional<Compra> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }
}

   