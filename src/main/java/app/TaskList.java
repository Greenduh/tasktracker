package app;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private int idCount;
    private List<Task> list;

    public TaskList() {
        this.idCount = 1;
        this.list = new ArrayList<>();
    }

    private Task findById(int id) {
        return list.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public boolean updateTask(int id, String description) {
        Task task = findById(id);
        if (task == null) {
            System.out.println("No tasks found with id: " + id);
            return false;
        }
        int taskIndex = list.indexOf(task);
        task.setDescription(description);
        list.set(taskIndex, task);
        return true;
    }

    public boolean deleteTask(int id) {
        Task task = findById(id);
        if (task == null) {
            System.out.println("No tasks found with id: " + id);
            return false;
        }
        list.remove(task);
        return true;
    }

    public boolean markTask(int id, TaskStatus status) {
        Task task = findById(id);
        if (task == null) {
            System.out.println("No tasks found with id: " + id);
            return false;
        }
        int taskIndex = list.indexOf(task);
        task.setStatus(status);
        list.set(taskIndex, task);
        return true;
    }

    public int getIdCount() {
        return idCount;
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
    }
}
