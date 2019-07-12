package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TaskStore {

    public void addInfo(Map<String, Task> tasks) throws IOException{
        Writer writer = new FileWriter("TaskStore.txt");

        for (Task task : tasks.values()){
            writer.write(" " + task.getId() + ",");
            writer.write(task.getName() + ",");
            writer.write(task.getDeadline() + ",");
            writer.write(task.getUserId() + "\n");
        }
        writer.close();
    }

    public int getMaxId() throws IOException, ParseException {
        Map<String, Task> tasks = new HashMap();
        tasks = getInfo();
        int maxId = -1;
        for (Task task : tasks.values()){
            if (task.getId() > maxId){
                maxId = task.getId();
            }
        }
        maxId++;
        return maxId;
    }

    public Map<String, Task> getInfo() throws IOException, ParseException {
        if(! (new File("TaskStore.txt").isFile())){
            File file = new File("TaskStore.txt");
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader (new FileReader("TaskStore.txt"));
        Map<String, Task> tasks = new HashMap();

        while (reader.read() != -1) {
            String[] string = reader.readLine().split(",");//get one string divided on ,

            String taskName = string[1];//read task name
            String taskDeadLine = string[2];//read task dead line

            Task task = new Task(taskName,taskDeadLine);//create new task
            task.setId(string[0]);//get task Id
            task.setUserId(string[3]);//get task user name
            tasks.put(task.getName(),task);//add task in Map
            }
        return tasks;
    }

}