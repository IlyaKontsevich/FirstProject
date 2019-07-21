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
/*<bean id="userDbDao" class="com.internship.dao.UserDbDao"> </bean>
    <bean id="taskDbDao" class="com.internship.dao.TaskDbDao"> </bean>
    <bean id="userDbService" class="com.internship.service.UserDbService">
        <constructor-arg index = "0" ref="userDbDao"/>
    </bean>
    <bean id="taskDbService" class="com.internship.service.TaskDbService">
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