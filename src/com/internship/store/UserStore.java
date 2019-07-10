package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.util.*;

public class UserStore {

    public void addInfo(Map<String, User> users) throws IOException {
        Writer writer = new FileWriter("UserStore.txt");

        for (User user : users.values()){
                writer.write(" " + user.getName());
                for (String userTask : user.getTasks()) {
                    writer.write("," + userTask);
                }
                writer.write("\n");
        }
        writer.close();
    }

    public Map<String, User> getInfo() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("UserStore.txt"));
        Map<String, User> users = new HashMap();
        while (reader.read() != -1) {
            StringBuilder userName = new StringBuilder("");
            char[] string = reader.readLine().toCharArray();
            int i = 0;
            while (i < string.length && string[i] != ',') {
                userName.append(string[i++]);
            }
            i++;
            User user = new User(userName.toString());
            while (i < string.length){
                StringBuilder userTaskName = new StringBuilder("");
                while(i < string.length && string[i] != ','){
                    userTaskName.append(string[i++]);
                }
                user.addTask(userTaskName.toString());
                i++;
            }
            users.put(user.getName(),user);
        }
        return users;
    }
}
