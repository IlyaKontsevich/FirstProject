package com.internship.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task {
    private int id;
    private String name;
    private String userName;
    private Date deadline;


    public Task(String name, Date data) {
        this.name = name;
        this.deadline = data;
        id = hashCode();
    }

    public Task(String name, String deadLine) throws ParseException {
        DateFormat format = new SimpleDateFormat("EEE MMM d yyyy", Locale.ENGLISH);
        Date date = format.parse(deadLine);

        this.deadline = date;
        this.name = name;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }


    public String getName(){
        return name;
    }

    public Date getDeadline(){
        return deadline;
    }

    public void setId(String id) { this.id = Integer.parseInt(id); }

    public int getId() { return id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                Objects.equals(name, task.name) &&
                Objects.equals(userName, task.userName) &&
                Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userName, deadline);
    }
}

//