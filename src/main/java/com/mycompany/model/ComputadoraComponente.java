/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

/**
 *
 * @author sofia
 */
public class ComputadoraComponente {
    private int id;
    private int computadoraId;
    private int componenteId;
    private int cantidad;

    public ComputadoraComponente(int id, int computadoraId, int componenteId, int cantidad) {
        this.id = id;
        this.computadoraId = computadoraId;
        this.componenteId = componenteId;
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

    public int getComponenteId() {
        return componenteId;
    }

    public void setComponenteId(int componenteId) {
        this.componenteId = componenteId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
