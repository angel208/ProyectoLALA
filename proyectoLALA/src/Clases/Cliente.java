/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author macana
 */
public class Cliente {
    private int codigoCliente;
    private String rif;
    private String razonSocial;
    private String direccion;
     private String contacto;
     private long tlf;
     private long fax;
     private  String email;
     private String estadoCliente;
     private String  Categoria;
     private  float montoMaxventa;
     private float descuento;

    public Cliente(int codigoCliente, String rif, String razonSocial, String direccion, String contacto, long tlf, long fax, String email, String estadoCliente, String Categoria, float montoMaxventa, float descuento) {
        this.codigoCliente = codigoCliente;
        this.rif = rif;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.contacto = contacto;
        this.tlf = tlf;
        this.fax = fax;
        this.email = email;
        this.estadoCliente = estadoCliente;
        this.Categoria = Categoria;
        this.montoMaxventa = montoMaxventa;
        this.descuento = descuento;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public long getTlf() {
        return tlf;
    }

    public void setTlf(long tlf) {
        this.tlf = tlf;
    }

    public long getFax() {
        return fax;
    }

    public void setFax(long fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public float getMontoMaxventa() {
        return montoMaxventa;
    }

    public void setMontoMaxventa(float montoMaxventa) {
        this.montoMaxventa = montoMaxventa;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
     
    
}
