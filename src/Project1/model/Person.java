package Project1.model;

public abstract class Person{
    protected String name;
    protected String cpf;

    public Person(String name, String cpf){
        this.name = name;
        this.cpf = cpf;
    }
    public abstract void displayData();

}
