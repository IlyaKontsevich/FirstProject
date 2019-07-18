package com.internship;

import com.internship.console.ConsoleApp;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"config.xml"});
        ConsoleApp application = (ConsoleApp) context.getBean("console");
        application.scan();
        System.out.println("EXIT");
    }
}
