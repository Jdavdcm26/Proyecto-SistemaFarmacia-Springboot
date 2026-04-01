package com.sistemafarmacia.sistemafarmacia.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;

@Entity
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaEmision;
    private Double total;
    private String observaciones;

    @OneToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;   
    
    public Long getId() {
        return id;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public Double getTotal() {
        return total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

}
