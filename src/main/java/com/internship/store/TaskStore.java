package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskStore {

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
        DateFormat format = new SimpleDateFormat("EEE MMM d yyyy", Locale.ENGLISH);
        BufferedReader reader = new BufferedReader(new FileReader("TaskStore.txt"));

            while (reader.read() != -1) {
                String[] string = reader.readLine().split(",");//get one string divided on ,

                String taskName = string[1];//read task name
                String taskDeadLine = string[2];//read task dead line
                try {
                    Date date = format.parse(taskDeadLine);
                    Task task = new Task(taskName, date);//create new task
                    task.setId(Integer.parseInt(string[0]));//get task Id
                    task.setUserId(Integer.parseInt(string[3]));//get task user name
                    tasks.put(task.getName(), task);//add task in Map
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return tasks;
    }
}