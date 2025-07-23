package project2.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Sale implements Serializable {
    private int id;
    private Customer customer;
    private List<Product> productSold;
    private LocalDateTime dateTime;
    private double totalValue;

    public Sale(int id, Customer customer, List<Product> productSold, LocalDateTime dateTime, double totalValue){
        this.id = id;
        this.customer = customer;
        this.productSold = productSold;
        this.dateTime = dateTime;
        this.totalValue = totalValue;
    }

    public int getId(){
        return id;
    }
    public Customer getCustomer(){
        return customer;
    }
    public List<Product> getProduct(){
        return productSold;
    }
    public LocalDateTime getDateTime(){
        return dateTime;
    }
    public double getTotalValue(){
        return totalValue;
    }

    @Override
    public String toString(){
        return "Sale [ID: " + id + ", Customer: " + customer.getName() + ", Products: " + productSold.size() + ", Data: " + dateTime + ", Total: " + totalValue + "]";
    }
}
