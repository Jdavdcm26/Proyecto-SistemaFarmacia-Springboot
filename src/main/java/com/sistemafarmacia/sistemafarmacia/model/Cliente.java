package com.sistemafarmacia.sistemafarmacia.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente {
    
    @Id
    @NotBlank(message = "El número de identificación del cliente es obligatorio")
    private String identificacion;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombre;

    @NotBlank(message = "El teléfono del cliente es obligatorio")
    private String telefono;

    @NotBlank(message = "La dirección del cliente es obligatoria")
    private String direccion;

    @NotBlank(message = "El correo electrónico del cliente es obligatorio")
    private String correoElectronico;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> ventas;

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}
