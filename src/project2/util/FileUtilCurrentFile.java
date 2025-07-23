package project2.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilCurrentFile {
    public static <T> void saveToFile(List<T> data, String fileName){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadFromFile(String fileName){
        File file = new File(fileName);
        if (!file.exists()){
            return new ArrayList<>();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))){
            return (List<T>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
