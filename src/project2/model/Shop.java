package project2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable {
    private List<Product> productList;
    private List<Customer> customerlist;
    private List<Sale> saleList;

    public Shop(){
        this.productList = new ArrayList<>();
        this.customerlist = new ArrayList<>();
        this.saleList = new ArrayList<>();
    }

    public void addProduct(Product product){
        productList.add(product);
    }
    public List<Product> getProductList(){
        return productList;
    }

    public void addCustomer(Customer customer){
        customerlist.add(customer);
    }
    public List<Customer> getCustomerlist(){
        return customerlist;
    }

    public void addSale(Sale sale){
        saleList.add(sale);
    }
    public List<Sale> getSaleList(){
        return saleList;
    }
}
