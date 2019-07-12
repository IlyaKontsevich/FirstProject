package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TaskStore {

    public void addInfo(Map<String, Task> tasks) throws IOException{
        Writer writer = new FileWriter("TaskStore.txt");

        for (Task task : tasks.values()){
            writer.write(" " + task.getName() + ",");
            writer.write(task.getDeadline() + ",");
            writer.write(task.getId() + ",");
            writer.write(task.getUserName() + "\n");
        }
        writer.close();
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

            String taskName = string[0];//read task name
            String taskDeadLine = string[1];//read task dead line

            Task task = new Task(taskName,taskDeadLine);//create new task
            task.setId(string[2]);//get task Id
            task.setUserName(string[3]);//get task user name
            tasks.put(task.getName(),task);//add task in Map
            }
        return tasks;
    }

}

//.Moven
// moven project with one module
// build produce JAR file
// run application from Jar
//JAR file