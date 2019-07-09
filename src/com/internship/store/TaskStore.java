package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class TaskStore {
    public Map<String, Task> tasks = new HashMap();


    public void addInfo() throws IOException{
        Writer writer = new FileWriter("TaskStore.txt");

        for (Task task : tasks.values()){
            writer.write(" " + task.getName() + "\n");
            writer.write(task.getDeadline() + "\n");
            writer.write(task.getId() + "\n");
            writer.write(task.getUser().getName() + "\n");
        }
        writer.close();
    }

    public void getInfo() throws IOException, ParseException {
        BufferedReader reader = new BufferedReader (new FileReader("TaskStore.txt"));

        while (reader.read() != -1){
            Task task = new Task(reader.readLine(),reader.readLine());
            task.setId(reader.readLine());
            task.setUserName(reader.readLine());
            tasks.put(task.getName(),task);
        }
    }
}
