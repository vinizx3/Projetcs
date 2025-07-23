package project2.util;

public class ExceptionUtil {
    public void showError(String message, Exception e){
        System.out.println("[Error] " + message);
        System.out.println("Detail: " + e.getMessage());
    }
}
