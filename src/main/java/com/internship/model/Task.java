package com.internship.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Task {
    private int id;
    private String name;
    private int userId;
    private Date deadline;


    public Task(String name, Date data) {
        this.name = name;
        this.deadline = data;
    }

    public Task(String name, String deadLine) throws ParseException {
        DateFormat format = new SimpleDateFormat("EEE MMM d yyyy", Locale.ENGLISH);
        Date date = format.parse(deadLine);

        this.deadline = date;
        this.name = name;
    }

    public void setUserId(int id){
        userId = id;
    }

    public void setUserId(String id){
        userId = Integer.parseInt(id);
    }

    public int getUserId(){
        return userId;
    }


    public String getName(){
        return name;
    }

    public Date getDeadline(){
        return deadline;
    }

    public void setId(String id) { this.id = Integer.parseInt(id); }

    public int getId() { return id; }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                userId == task.userId &&
                Objects.equals(name, task.name) &&
                Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, deadline);
    }
}

//