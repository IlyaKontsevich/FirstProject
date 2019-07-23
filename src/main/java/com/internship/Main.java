package com.internship;

import com.internship.console.ConsoleApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner symbolScanner = new Scanner(System.in);
        System.out.println("Chose data storege type:\n1.File storage\n2.Data base(JDBC) storage\n3.Data base(spring-JDBC) storage");
        String symbol = symbolScanner.nextLine();
        while (!symbol.equals("1") && !symbol.equals("2") && !symbol.equals("3")) {
            System.out.println("Wrong number,please enter another: ");
            symbol = symbolScanner.nextLine();
        }
        if (symbol.equals("1")) {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "fileSystem");
        } else if (symbol.equals("2")) {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dbSystem");
        } else {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "spring-JDBC");
        }
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        ConsoleApp application = context.getBean("consoleApp", ConsoleApp.class);
        application.scan();
        System.out.println("EXIT");
    }
}

/*
<context:component-scan base-package="com.internship"/>
<bean id="userDbDao" class="com.internship.dao.UserDbDao"> </bean>
    <bean id="taskDbDao" class="com.internship.dao.TaskDbDao"> </bean>
    <bean id="userDbService" class="com.internship.service.UserService">
        <constructor-arg index = "0" ref="userDbDao"/>
    </bean>
    <bean id="taskDbService" class="com.internship.service.TaskService">
        <constructor-arg index = "0" ref="taskDbDao"/>
    </bean>
    <bean id="userFileDao" class="com.internship.dao.UserFileDao"> </bean>
    <bean id="taskFileDao" class="com.internship.dao.TaskFileDao"> </bean>
    <bean id="taskFileService" class="com.internship.service.TaskFileService">
        <constructor-arg index = "0" ref="taskFileDao"/>
    </bean>
    <bean id="userFileService" class="com.internship.service.UserFileService">
        <constructor-arg index = "0" ref="userFileDao"/>
    </bean>
    <bean id="console" class="com.internship.console.ConsoleApp">
        <constructor-arg index = "0" ref="taskDbService"/>
        <constructor-arg index = "1" ref="userDbService"/>
    </bean>*/