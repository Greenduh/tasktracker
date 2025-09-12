package app;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File tasksFile = new File(System.getProperty("user.home") + "/tasks.json");

        // create tasks.json if it doesn't exist
        if (!tasksFile.exists()) {
            try {
                tasksFile.createNewFile();
                System.out.println("File created: " + tasksFile.getName());
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
