package com.sistemafarmacia.sistemafarmacia.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Proveedor {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nit del proveedor es obligatorio")
    private String nit;

    @NotBlank(message = "El nombre del proveedor es obligatorio")
    private String nombre;

    @NotBlank(message = "El teléfono del proveedor es obligatorio")
    private String telefono;

    @NotBlank(message = "La dirección del proveedor es obligatoria")
    private String direccion;

    @NotBlank(message = "El correo electrónico del proveedor es obligatorio")
    private String correoElectronico;

    @OneToMany(mappedBy = "proveedor")
    private List<Medicamento> medicamentos;

    @OneToMany(mappedBy = "proveedor")
    private List<Compra> compras;

    public Long getId() {
        return id;
    }

    public String getNit() {
        return nit;
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

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
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
    public List<Compra> getCompras() {
        return compras;
    }
    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

}

