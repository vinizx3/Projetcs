package Project1.util;

import Project1.model.Student;

import java.io.*;
import java.util.List;
import java.util.Map;

public class FileUtil {

    public static <T> void save (String fileName, Map<String, Student> list){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
        outputStream.writeObject(list);
            System.out.println("Successfully data saved ");
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static <T> List <T> Load (String fileName){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            return (List<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
