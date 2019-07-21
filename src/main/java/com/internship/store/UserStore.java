package com.internship.store;

import com.internship.model.User;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserStore implements IStore<User> {

    public void addInfo(Map<String, User> users){
        try {
            Writer writer = new FileWriter("UserStore.txt");

            for (User user : users.values()) {
                writer.write(" " + user.getId()); //write user id
                writer.write("," + user.getName());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        Map<String, User> users = getInfo();
        int maxId = -1;
        for (User user : users.values()){
            if (user.getId() > maxId){
                maxId = user.getId();
            }
        }
        maxId++;
        return maxId;
    }
    public Map<String, User> getInfo(){
        Map<String, User> users = new HashMap();
        try {
            if (!(new File("UserStore.txt").isFile())) {
                File file = new File("UserStore.txt");
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader("UserStore.txt"));
            while (reader.read() != -1) {
                String[] string = reader.readLine().split(",");//get one string divided on ,
                User user = new User(string[1]);//creat new user
                user.setId(Integer.parseInt(string[0]));
                users.put(user.getName(), user);//add user in Map
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}