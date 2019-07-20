package com.internship;

import com.internship.console.ConsoleApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        ConsoleApp application = context.getBean("console", ConsoleApp.class);
        application.scan();
        System.out.println("EXIT");
    }
}
//