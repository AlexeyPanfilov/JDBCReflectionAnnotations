package org.example.cars;

import org.example.annotations.Column;
import org.example.annotations.Table;

@Table
public class Car {

    @Column(type = "TEXT")
    protected String brand;
    @Column(type = "TEXT")
    protected String model;
    @Column(type = "INTEGER")
    protected int manufactureYear;
    @Column(type = "INTEGER")
    protected int price;

    public Car(String brand, String model, int manufactureYear, int price) {
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + " " + model + ", " + manufactureYear + " " + price + " rub.";
    }
}
