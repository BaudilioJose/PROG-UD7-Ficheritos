package net.salesianos.table;

import java.io.Serializable;

public class Table implements Serializable {
    private static final long serialVersionUID = 1L;

    private String color;
    private int legCount;

    // Constructor
    public Table(String color, int legCount) {
        this.color = color;
        this.legCount = legCount;
    }

    // Getters y Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLegCount() {
        return legCount;
    }

    public void setLegCount(int legCount) {
        this.legCount = legCount;
    }

    // Método toString sobrescrito para representación formateada
    @Override
    public String toString() {
        return String.format("Mesa [Color: %s, Número de patas: %d]", color, legCount);
    }
}