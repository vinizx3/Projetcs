package Project1;

import Project1.model.ClassRoom;
import Project1.model.Note;
import Project1.model.Student;
import Project1.model.Teacher;
import Project1.service.SchoolManager;

import java.util.Scanner;

public class SchoolSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SchoolManager system = new SchoolManager();
        system.dataLoad();

        int option;
        do{
            System.out.println("\n--- Menu ---");
            System.out.println("1. Register teacher");
            System.out.println("2. Register student");
            System.out.println("3. Create class");
            System.out.println("4. Add student to class");
            System.out.println("5. list classes");
            System.out.println("6. list students and averages by class");
            System.out.println("0. leave");
            System.out.println("Choise one option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.println("Teacher name: ");
                    String name = sc.nextLine();
                    System.out.println("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.println("Discipline: ");
                    String discipline = sc.nextLine();
                    Teacher teacher = new Teacher(name, cpf, discipline);
                    system.teacherRegister(teacher);
                    System.out.println("Successfully teacher registered");
                }
                case 2 -> {
                    System.out.println("Student name: ");
                    String name = sc.nextLine();
                    System.out.println("CPF: ");
                    String cpf = sc.nextLine();
                    Student student = new Student(name, cpf);

                    System.out.println("How many notes do you want to add? ");
                    int qty = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < qty; i++) {
                        System.out.println("Discipline: ");
                        String disc = sc.nextLine();
                        System.out.println("Note: ");
                        double note = sc.nextInt();
                        sc.nextLine();
                        student.addNote(new Note(disc, note));
                    }

                    system.studentRegister(student);
                    System.out.println("Successfully student registered");
                }
                case 3 -> {
                    System.out.println("Class name: ");
                    String name = sc.nextLine();
                    System.out.println("CPF of the class teacher: ");
                    String teacherCPF = sc.nextLine();
                    Teacher teacher = system.searchTeacher(teacherCPF);
                    if (teacher != null){
                        system.creatClass(name, teacher);
                        System.out.println("Successfully class created ");
                    } else {
                        System.out.println("Teacher has not found");
                    }
                }
                case 4 -> {
                    System.out.println("Nome da turma: ");
                    String name = sc.nextLine();
                    System.out.println("CPF do aluno: ");
                    String studentCPF = sc.nextLine();
                    Student student = system.searchStudent(studentCPF);
                    if (student != null){
                        system.addStudentInClass(name, student);
                    } else {
                        System.out.println("Student has not found");
                    }
                }
                case 5 -> {
                    system.listClass();
                }
                case 6 -> {
                    for (ClassRoom classRoom : system.getClassRooms()){
                        System.out.println("\nClass: " + classRoom.getName());
                        for (Student student : classRoom.getStudents()){
                            System.out.println("- " + student.getName() + ", Avarage: " + student.calcAvarage());
                        }
                    }
                }
                case 0 -> {
                    system.dataSave();
                    System.out.println("Leaving the system...");
                }
                default -> System.out.println("Option invalid");
            }
        } while( option != 0);
        sc.close();
    }
}
