package com.internship.model;


import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tasks")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="deadline")
    private LocalDate deadline;
    @Column(name="timeAdd")
    private LocalDate timeadd;
    @Column(name="priority")
    private String priority;
    @Column(name="isdone")
    private Boolean isdone;
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsdone() {
        return isdone;
    }

    public void setIsdone(Boolean done) {
        isdone = done;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getTimeadd() {
        return timeadd;
    }

    public void setTimeadd(LocalDate timeadd) {
        this.timeadd = timeadd;
    }

    public Task(String name) {
        this.name = name;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
