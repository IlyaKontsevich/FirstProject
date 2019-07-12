package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.util.*;

public class UserStore {

    public void addInfo(Map<String, User> users) throws IOException {
        Writer writer = new FileWriter("UserStore.txt");

        for (User user : users.values()){
                writer.write(" " + user.getName()); //write user name
                for (String userTask : user.getTasks()) { //write user task
                    writer.write("," + userTask);
                }
                writer.write("\n");
        }
        writer.close();
    }

    public Map<String, User> getInfo() throws IOException {
        if(! (new File("UserStore.txt").isFile())){
            File file = new File("UserStore.txt");
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader("UserStore.txt"));
        Map<String, User> users = new HashMap();
        while (reader.read() != -1) {
            String[] string = reader.readLine().split(",");//get one string divided on ,
            int i = 0;

            String userName = string[i++];//get user name
            User user = new User(userName);//creat new user

            while (i < string.length){//read users task
                String userTaskName = string[i++];
                user.addTask(userTaskName);
            }
            users.put(user.getName(),user);//add user in Map
        }
        return users;
    }
}
