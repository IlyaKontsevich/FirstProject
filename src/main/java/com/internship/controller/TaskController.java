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
@RequestMapping("user/{userId}/task")
public class TaskController {
    @Autowired
    private ITaskDao taskDao;
    private Integer pageSize = 3;
    private String sortType = "id";

    @RequestMapping("/form")
    public String showForm(Model m) {
        m.addAttribute("command", new Task("taskname"));
        return "taskForm";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@PathVariable Integer userId,@ModelAttribute("task") Task task){
        task.setUserId(userId);
        LocalDate localDate = LocalDate.parse(task.getDate());
        LocalDate todayDate = LocalDate.now();
        if(localDate.isBefore(todayDate))
        {
            return "redirect:/user/error";
        }else {
            task.setDeadline(localDate);
            if (taskDao.add(task) != null)
                return "redirect:0";
            else
                return "redirect:/user/error";
        }
    }

    @RequestMapping("/pagesize")
    public String changePageSize(){
        return "pageSize";
    }

    @RequestMapping("/savepage")
    public String savePageSize(Integer pageSize){
        this.pageSize = pageSize;
        return "redirect:0";
    }
    @RequestMapping("/changesort{sorttype}")
    public String changeSortType(@PathVariable String sorttype){
        sorttype = sorttype.replace("{", "");
        sorttype = sorttype.replace("}", "");
        this.sortType = sorttype;
        System.out.println(sorttype);
        return "redirect:0";
    }
    @RequestMapping("/{position}")
    public String view(@PathVariable Integer userId,@PathVariable Integer position,Model m){
        Collection<Task> list = taskDao.getPage(position,pageSize,userId,sortType);
        m.addAttribute("pageSize",pageSize);
        m.addAttribute("size",taskDao.getSize(userId));
        m.addAttribute("position",position);
        m.addAttribute("userId",userId);
        m.addAttribute("list",list);
        return "task";
    }

    @RequestMapping(value="{id}/editsave",method = RequestMethod.POST)
    public String editSave(@ModelAttribute("task") Task task){
        LocalDate localDate = LocalDate.parse(task.getDate());
        task.setDeadline(localDate);
        taskDao.update(task);
        return "redirect:../0";
    }

    @RequestMapping(value="{id}/edit")
    public String edit(@PathVariable Integer userId,@PathVariable Integer id, Model m){
        Task task = taskDao.get(id);
        m.addAttribute("command",task);
        m.addAttribute("userId",userId);
        return "taskEditForm";
    }
    @RequestMapping(value="{id}/delete",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        taskDao.delete(id);
        return "redirect: ../0";
    }
}

