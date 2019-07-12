package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.util.*;

public class UserStore {

    public void addInfo(Map<String, User> users) throws IOException {
        Writer writer = new FileWriter("UserStore.txt");

        for (User user : users.values()){
                writer.write(" " + user.getId()); //write user name
                writer.write("," + user.getName());
                writer.write("\n");
        }
        writer.close();
    }

    public int getMaxId() throws IOException {
        Map<String, User> users = new HashMap();
        users = getInfo();
        int maxId = -1;
        for (User user : users.values()){
            if (user.getId() > maxId){
                maxId = user.getId();
            }
        }
        maxId++;
        return maxId;
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
            User user = new User(string[1]);//creat new user
            user.setId(string[0]);
            users.put(user.getName(),user);//add user in Map
        }
        return users;
    }
}
