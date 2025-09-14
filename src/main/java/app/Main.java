package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // interrupt execution if no arguments are passed
        if (args.length < 1) {
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
                if (args.length < 2) {
                    System.out.println("No task description specified.");
                    return;
                }

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
                if (args.length < 2) {
                    System.out.println("No task id specified.");
                    return;
                }
                if (args.length < 3) {
                    System.out.println("No task description specified.");
                    return;
                }

                list.updateTask(Integer.parseInt(args[1]), args[2]);
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(tasksFile, list);
                    System.out.println("Task updated successfully.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                break;
            case "mark":
                break;
            case "list":
                for (Task t : list.getList()) {
                    System.out.println(t + "\n");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + args[0]);
        }
    }
}
