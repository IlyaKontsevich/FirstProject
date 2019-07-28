package com.internship.controller;

import com.internship.dao.ITaskDao;
import com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.Collection;

@Controller
public class TaskController {
    @Autowired
    private ITaskDao taskDao;

    @RequestMapping("viewUser{userId}/taskForm")
    public String showForm(@PathVariable Integer userId,Model m) {
        m.addAttribute("command", new Task("taskname"));
        return "taskForm";
    }

    @RequestMapping(value="/viewUser{userId}/taskSave",method = RequestMethod.POST)
    public String save(@PathVariable Integer userId,@ModelAttribute("task") Task task){
        task.setUserId(userId);
        LocalDate localDate = LocalDate.parse(task.getDate());
        task.setDeadline(localDate);

        if(taskDao.add(task) != null)
            return "redirect:/viewUser{userId}/task0";
        else
            return "redirect:/error";
    }

    @RequestMapping("viewUser{userId}/task{position}")
    public String view(@PathVariable Integer userId,@PathVariable Integer position,Model m){
        Collection<Task> list = taskDao.getPage(position,userId);
        m.addAttribute("size",taskDao.getSize(userId));
        m.addAttribute("position",position);
        m.addAttribute("userId",userId);
        m.addAttribute("list",list);
        return "task";
    }

    @RequestMapping(value="viewUser{userId}/taskEditSave",method = RequestMethod.POST)
    public String editSave(@PathVariable Integer userId,@ModelAttribute("task") Task task){
        LocalDate localDate = LocalDate.parse(task.getDate());
        task.setDeadline(localDate);
        taskDao.update(task);
        return "redirect:/viewUser{userId}/task0";
    }

    @RequestMapping(value="/viewUser{userId}/editTask{id}")
    public String edit(@PathVariable Integer userId,@PathVariable Integer id, Model m){
        Task task = taskDao.get(id);
        m.addAttribute("command",task);
        m.addAttribute("userId",userId);
        return "taskEditForm";
    }
    @RequestMapping(value="viewUser{userId}/deleteTask{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id,@PathVariable int userId){
        taskDao.delete(id);
        return "redirect:/viewUser{userId}/task0";
    }
}

