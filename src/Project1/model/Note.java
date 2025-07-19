package Project1.model;

import java.io.Serializable;

public class Note implements Serializable {
    private String discipline;
    private double value;

    public Note(String discipline, double value){
        this.discipline = discipline;
        this.value = value;
    }

    public String getDiscipline(){
        return discipline;
    }
    public double getValue(){
        return value;
    }

}
