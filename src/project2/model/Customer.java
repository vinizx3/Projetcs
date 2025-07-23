package project2.model;

import java.io.Serializable;
public class Customer extends person implements Serializable {
    private String cpf;

    public Customer(int id, String name, String cpf){
        super(id, name);
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", cpf: " + cpf;
    }

}


