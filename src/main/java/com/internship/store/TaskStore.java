package com.internship.store;

import com.internship.model.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
@Profile("fileSystem")
public class TaskStore implements IStore<Task> {
    public TaskStore() {
        try {
            if (!(new File("TaskStore.txt").isFile())) {//create file if not exists
                File file = new File("TaskStore.txt");
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addInfo(Map<String, Task> tasks) {
        try {
            Writer writer = new FileWriter("TaskStore.txt");

            for (Task task : tasks.values()) {//write info in file
                writer.write(" " + task.getId() + ",");
                writer.write(task.getName() + ",");
                writer.write(task.getDeadline() + ",");
                writer.write(task.getUserId() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxId() {
        Map<String, Task> tasks = getInfo();
        int maxId = -1;
        for (Task task : tasks.values()) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        maxId++;
        return maxId;
    }

    public Map<String, Task> getInfo() {
        Map<String, Task> tasks = new HashMap();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("TaskStore.txt"));

            while (reader.read() != -1) {
                String[] string = reader.readLine().split(",");//get one string divided on ,

                String taskName = string[1];//read task name
                Task task = new Task(taskName);//create new task
                task.setDeadline(LocalDate.parse(string[2]));//set task dead line
                task.setId(Integer.parseInt(string[0]));//get task Id
                task.setUserId(Integer.parseInt(string[3]));//get task user name
                tasks.put(task.getName(), task);//add task in Map
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}