package com.BouquetStore.Accessories;

abstract class Accessory {
    private double price;
    private String name;
    public Accessory(double price,String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    public String toString() {
        return "Name: " + this.getName() + ", Price: "+this.getPrice();
    }

}
