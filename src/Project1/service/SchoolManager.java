package Project1.service;

import Project1.model.ClassRoom;
import Project1.model.Student;
import Project1.model.Teacher;
import Project1.util.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SchoolManager {
    private Map<String, Teacher> teacherMap;
    private Map<String, Student> studentMap;
    private List<ClassRoom> classRooms;

    public SchoolManager(){
        this.teacherMap = new HashMap<>();
        this.studentMap = new HashMap<>();
        this.classRooms = new ArrayList<>();
    }

    public void teacherRegister(Teacher teacher){
        teacherMap.put(teacher.getCpf(), teacher);
    }
    public void studentRegister(Student student){
        studentMap.put(student.getCpf(), student);
    }

    public Teacher searchTeacher(String cpf){
        return teacherMap.get(cpf);
    }
    public Student searchStudent(String cpf){
        return studentMap.get(cpf);
    }

    public void creatClass(String className, Teacher teacher){
        ClassRoom newClass = new ClassRoom(className, teacher);
        classRooms.add(newClass);
    }
    public void addStudentInClass(String className, Student Student){
        for (ClassRoom classRoom : classRooms){
            if (classRoom.getName().equalsIgnoreCase(className)){
                classRoom.addStudent(Student);
                return;
            }
        }
        System.out.println("Class not found");
    }
    public void listClass(){
        for (ClassRoom classRoom : classRooms){
            System.out.println("Class: " + classRoom.getName() + ", Teacher: " + classRoom.getTeacher().getName());
        }
    }
    public List<ClassRoom> getClassRooms(){
        return classRooms;
    }

    public void dataSave() {
        FileUtil.save("classrooms.dat", classRooms);
    }

    public void dataLoad() {
        classRooms = FileUtil.load("classrooms.dat");
    }

}
