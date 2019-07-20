package com.internship.store;

import com.internship.model.Task;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TaskStore implements IStore<Task> {

    public void addInfo(Map<String, Task> tasks){
        try {
            Writer writer = new FileWriter("TaskStore.txt");

            for (Task task : tasks.values()) {
                writer.write(" " + task.getId() + ",");
                writer.write(task.getName() + ",");
                writer.write(task.getDeadline() + ",");
                writer.write(task.getUserId() + "\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        Map<String, Task> tasks = getInfo();
        int maxId = -1;
        for (Task task : tasks.values()){
            if (task.getId() > maxId){
                maxId = task.getId();
            }
        }
        maxId++;
        return maxId;
    }

    public Map<String, Task> getInfo(){
        Map<String, Task> tasks = new HashMap();
        try {
        if(! (new File("TaskStore.txt").isFile())){
            File file = new File("TaskStore.txt");
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader("TaskStore.txt"));

            while (reader.read() != -1) {
                String[] string = reader.readLine().split(",");//get one string divided on ,

                String taskName = string[1];//read task name
                Task task = new Task(taskName);//create new task
                task.setDeadline(LocalDate.parse(string[2]));//read task dead line
                task.setId(Integer.parseInt(string[0]));//get task Id
                task.setUserId(Integer.parseInt(string[3]));//get task user name
                tasks.put(task.getName(), task);//add task in Map
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return tasks;
    }
}