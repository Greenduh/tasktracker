package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // interrupt execution if no arguments are passed
        if (args.length == 0) {
            System.out.println("No operation specified.");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        File tasksFile = new File(System.getProperty("user.home") + "/tasks.json");

        // create tasks.json if it doesn't exist
        try {
            if (tasksFile.createNewFile()) {
                System.out.println("File created: " + tasksFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        TaskList list;
        try {
            list = mapper.readValue(tasksFile, TaskList.class);
        } catch (IOException e) {
            list = new TaskList();
        }

        switch (args[0]) {
            case "add":
                Task task = new Task(args[1]);
                list.addTask(task);
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(tasksFile, list);
                    System.out.printf("Task added successfully (ID: %d)\n", task.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                break;
            case "delete":
                break;
            case "mark":
                break;
            case "list":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        }
    }
}
