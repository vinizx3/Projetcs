package Project1.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassRoom implements Serializable {
    private String name;
    private Teacher teacher;
    private List<Student> students;

    public ClassRoom(String name, Teacher teacher){
        this.name = name;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student){
        students.add(student);
    }
    public void listStudent(){
        System.out.println("class: " + name);
        for (Student student : students){
            System.out.println("- " + student.getName());
        }
    }

    public Teacher getTeacher(){
        return teacher;
    }
    public String getName(){
        return name;
    }
    public List<Student> getStudents(){
        return students;
    }
}
