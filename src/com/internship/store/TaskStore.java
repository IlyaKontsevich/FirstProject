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
        BufferedReader reader = new BufferedReader (new FileReader("TaskStore.txt"));
        Map<String, Task> tasks = new HashMap();

        while (reader.read() != -1) {
            StringBuilder taskName = new StringBuilder("");
            StringBuilder taskDeadLine = new StringBuilder("");
            StringBuilder taskId = new StringBuilder("");
            StringBuilder taskUserName = new StringBuilder("");
            char[] string = reader.readLine().toCharArray();
            int i = 0;
            while (string[i] != ',') {
                taskName.append(string[i++]);
            } i++;
            while (string[i] != ',') {
                taskDeadLine.append(string[i++]);
            } i++;
            while (string[i] != ',') {
                taskId.append(string[i++]);
            } i++;
            while (i < string.length) {
                taskUserName.append(string[i++]);
            } i++;
            Task task = new Task(taskName.toString(),taskDeadLine.toString());
            task.setUserName(taskUserName.toString());
            task.setId(taskId.toString());
            tasks.put(task.getName(),task);
            }
        return tasks;
    }

}

//.Moven
// moven project with one module
// build produce JAR file
// run application from Jar
//JAR file