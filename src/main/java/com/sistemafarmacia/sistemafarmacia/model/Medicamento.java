package com.sistemafarmacia.sistemafarmacia.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Medicamento {
    @Id
    @NotBlank(message = "El código del medicamento es obligatorio")
    private String codigo;

    @NotBlank(message = "El nombre del medicamento es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio de compra del medicamento es obligatorio")
    @Min(value = 0, message = "El precio de compra del medicamento no puede ser negativo")
    private Double precioCompra;

    @NotNull(message = "El precio de venta del medicamento es obligatorio")
    @Min(value = 0, message = "El precio de venta del medicamento no puede ser negativo")
    private Double precioVenta;

    @NotNull(message = "El stock del medicamento es obligatorio")
    @Min(value = 0, message = "El stock del medicamento no puede ser negativo")
    private Integer stock;

    @NotNull(message = "El stock mínimo del medicamento es obligatorio")
    @Min(value = 0, message = "El stock mínimo del medicamento no puede ser negativo")
    private Integer stockMinimo;

    @NotNull(message = "La fecha de vencimiento del medicamento es obligatoria")
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @NotNull(message = "La categoría del medicamento es obligatoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    @NotNull(message = "El proveedor del medicamento es obligatorio")
    private Proveedor proveedor;

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
