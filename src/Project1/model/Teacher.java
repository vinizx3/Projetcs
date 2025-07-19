package Project1.model;

import java.io.Serializable;

public class Teacher extends Person implements Serializable {
    private String discipline;

    public Teacher(String name, String cpf, String discipline){
        super(name, cpf);
        this.discipline = discipline;
    }

    @Override
    public void displayData(){
        System.out.println("Teacher: " + name + ", Discipline: " + discipline);
    }

    public String getDiscipline(){
        return discipline;
    }
    public String getName(){
        return name;
    }
    public String getCpf(){
        return cpf;
    }
}
