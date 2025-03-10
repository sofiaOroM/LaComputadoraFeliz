/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author sofia
 */
public class DetalleVenta {
    private int id;
    private int computadoraId;
    private int ventaId;
    private int cantidad;

    public DetalleVenta(int id, int ventaId, int computadoraId, int cantidad) {
        this.id = id;
        this.ventaId = ventaId;
        this.computadoraId = computadoraId;
        this.cantidad = cantidad;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComputadoraId() {
        return computadoraId;
    }

    public void setComputadoraId(int computadoraId) {
        this.computadoraId = computadoraId;
    }

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
