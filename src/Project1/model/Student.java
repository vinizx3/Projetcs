package Project1.model;

import Project1.Interface.Assessment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Assessment, Serializable{
    private List<Note> noteList;

    public Student(String name, String cpf){
        super(name, cpf);
        this.noteList = new ArrayList<>();
    }

    public void addNote(Note note){
        noteList.add(note);
    }
    public List<Note> getNoteList(){
        return noteList;
    }
    @Override
    public void displayData(){
        System.out.println("Student: " + name + ", CPF: " + cpf);
    }
    @Override
    public double calcAvarage(){
        if (noteList.isEmpty()) return 0.0;

        double sum = 0;
        for (Note note : noteList){
            sum += note.getValue();
        }
        return sum / noteList.size();
    }

    public String getName(){
        return name;
    }
    public String getCpf(){
        return cpf;
    }
}
