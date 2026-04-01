package com.sistemafarmacia.sistemafarmacia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @NotNull(message = "La cantidad del detalle de venta es obligatoria")
    @Min(value = 1, message = "La cantidad del detalle de venta debe ser al menos 1")    
    private Integer cantidad;

    @NotNull(message = "El precio del detalle de venta es obligatorio")
    @Min(value = 0, message = "El precio unitario no puede ser negativo")
    private Double precioUnitario;

    @NotNull(message = "El subtotal del detalle de venta es obligatorio")
    @Min(value = 0, message = "El subtotal no puede ser negativo")  
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "medicamento_codigo")
    private Medicamento medicamento;

    public Long getId() {
        return id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

}
