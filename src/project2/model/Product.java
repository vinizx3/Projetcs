package project2.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id, qty;
    private String name;
    private double price;

    public Product(int id, String name, double price, int qty){
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQty(){
        return qty;
    }

    public void setQuantityStock(int qty){
        this.qty = qty;
    }

    @Override
    public String toString(){
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + qty;
    }
}
