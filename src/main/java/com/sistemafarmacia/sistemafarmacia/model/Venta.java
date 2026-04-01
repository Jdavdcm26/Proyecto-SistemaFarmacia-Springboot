package com.sistemafarmacia.sistemafarmacia.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La fecha de la venta es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El total de la venta es obligatorio")
    private Double total;

    @NotBlank(message = "El estado de la venta es obligatorio")
     private String Estado;

     @ManyToOne
     @JoinColumn(name = "cliente_id")
     @NotNull(message = "El cliente de la venta es obligatorio")
     private Cliente cliente;

     @OneToMany(mappedBy = "venta")
     private List<DetalleVenta> detalles;

     @OneToOne(mappedBy = "venta")
     private Factura factura;

    public Long getId() {
        return id;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public Double getTotal() {
        return total;
    }

    public String getEstado() {
        return Estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
   