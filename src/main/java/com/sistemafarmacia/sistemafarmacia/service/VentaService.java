package com.sistemafarmacia.sistemafarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.sistemafarmacia.sistemafarmacia.model.Venta;
import com.sistemafarmacia.sistemafarmacia.model.DetalleVenta;
import com.sistemafarmacia.sistemafarmacia.repository.VentaRepository;
import com.sistemafarmacia.sistemafarmacia.repository.DetalleVentaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleRepository;

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    @Lazy
    private FacturaService facturaService;

    public Venta crear(Venta venta, List<DetalleVenta> detalles) {
        // Verificar stock de cada medicamento
        for (DetalleVenta detalle : detalles) {
            Optional med = medicamentoService.buscarPorCodigo(
                detalle.getMedicamento().getCodigo()
            );
            if (med.isEmpty()) {
                throw new RuntimeException("Medicamento no encontrado: " +
                    detalle.getMedicamento().getCodigo());
            }
            com.sistemafarmacia.sistemafarmacia.model.Medicamento m =
                (com.sistemafarmacia.sistemafarmacia.model.Medicamento) med.get();
            if (m.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para: " + m.getNombre() +
                    ". Stock disponible: " + m.getStock());
            }
        }

        // Calcular total
        double total = detalles.stream()
                .mapToDouble(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();

        venta.setFecha(LocalDate.now());
        venta.setTotal(total);
        venta.setEstado("PENDIENTE");
        Venta ventaGuardada = ventaRepository.save(venta);

        // Guardar detalles
        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(ventaGuardada);
            detalle.setSubtotal(detalle.getPrecioUnitario() * detalle.getCantidad());
            detalleRepository.save(detalle);
        }

        return ventaGuardada;
    }

    public Venta confirmar(Long ventaId) {
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Descontar stock de cada medicamento
        List<DetalleVenta> detalles = detalleRepository.findByVentaId(ventaId);
        for (DetalleVenta detalle : detalles) {
            medicamentoService.descontarStock(
                detalle.getMedicamento().getCodigo(),
                detalle.getCantidad()
            );
        }

        venta.setEstado("COMPLETADA");
        Venta ventaConfirmada = ventaRepository.save(venta);

        // Generar factura automaticamente
        facturaService.generarFactura(ventaConfirmada);

        return ventaConfirmada;
    }

    public Venta anular(Long ventaId) {
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        if (venta.getEstado().equals("COMPLETADA")) {
            // Devolver stock
            List<DetalleVenta> detalles = detalleRepository.findByVentaId(ventaId);
            for (DetalleVenta detalle : detalles) {
                medicamentoService.aumentarStock(
                    detalle.getMedicamento().getCodigo(),
                    detalle.getCantidad()
                );
            }
        }

        venta.setEstado("ANULADA");
        return ventaRepository.save(venta);
    }

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> buscarPorFecha(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaBetween(inicio, fin);
    }

    public List<Venta> buscarPorCliente(String identificacion) {
        return ventaRepository.findByClienteIdentificacion(identificacion);
    }

    public List<Venta> ventasDeHoy() {
        return ventaRepository.findByFecha(LocalDate.now());
    }

    public Double totalRecaudadoHoy() {
        return ventasDeHoy().stream()
                .filter(v -> v.getEstado().equals("COMPLETADA"))
                .mapToDouble(Venta::getTotal)
                .sum();
    }

    public Double totalRecaudadoMes() {
        LocalDate inicio = LocalDate.now().withDayOfMonth(1);
        LocalDate fin = LocalDate.now();
        return buscarPorFecha(inicio, fin).stream()
                .filter(v -> v.getEstado().equals("COMPLETADA"))
                .mapToDouble(Venta::getTotal)
                .sum();
    }
}
