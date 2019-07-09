package com.internship.store;

import com.internship.model.*;

import java.io.*;
import java.util.*;

public class UserStore {
    public Map<String, User> users = new HashMap();

    public void addInfo() throws IOException {
        Writer writer = new FileWriter("UserStore.txt");

        for (User user : users.values()){
            if(user != null) {
                writer.write(" " + user.getName() + "\n");
            }
        }
        writer.close();
    }

    public void getInfo() throws IOException {
        BufferedReader reader = new BufferedReader (new FileReader("UserStore.txt"));

        while (reader.read() != -1){
            User user = new User(reader.readLine());
            users.put(user.getName(),user);
        }
    }
}
