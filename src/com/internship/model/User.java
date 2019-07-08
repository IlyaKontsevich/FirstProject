package com.internship.model;

import java.util.Scanner;

public class User {
    private String name;


    public String GetName(){
        return name;
    }

    public User(){
        Scanner scaner = new Scanner(System.in);

        System.out.println("Enter user name: ");
        name = scaner.nextLine();
        System.out.println("User successfully add");
    }

}
//fdgdfg
//1.Naming convention
//2.Pakets
//3.github .idea .out *.iml + ignore: campiled; IDE; build
//4.RAEDME.md
//store
//!
//Dao  CRUD user or task
//!
//Service
//!
//console controller + validation
//by.exapel.model.User
//          .console.M
//          .service.
//          .dao



//Task: user.id