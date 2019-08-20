package com.internship.controller;

import com.internship.model.Task;
import com.internship.service.ITaskService;
import com.internship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("user/{userUrl}/{userId}/task")
public class TaskController {
    @Autowired
    private ITaskService service;
    @Autowired
    private IUserService userService;

    @RequestMapping("{param}/form")
    public String showForm(Model m) {
        m.addAttribute("command", new Task("taskname"));
        return "taskForm";
    }

    @RequestMapping(value="{param}/save",method = RequestMethod.POST)
    public String save(String date,@PathVariable Integer userId,@ModelAttribute("task") Task task){
        task.setUser(userService.get(userId));
        LocalDate localDate = LocalDate.parse(date);
        LocalDate todayDate = LocalDate.now();
        if(localDate.isBefore(todayDate)) {
            return "redirect:/user/{userUrl}/error";
        }else {
            task.setTimeadd(todayDate);
            task.setDeadline(localDate);
            task.setIsdone(false);
            if (service.add(task) != null)
                return "redirect:../?{param}";
            else
                return "redirect:/user/{userUrl}/error";
        }
    }


    @RequestMapping("/")
    public String view(@PathVariable String userUrl,@PathVariable Integer userId,
                       @RequestParam(value="page", defaultValue = "1") String page,
                       @RequestParam(value="size", defaultValue = "3") String size,
                       @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                       @RequestParam(required = false, value="filter") List<String> filter, Model m){
        if(filter == null) {
            filter = new ArrayList<String>();
            filter.add("");
            m.addAttribute("url","?page="+page+"&size="+size+"&sort="+String.join("&sort=",sort));
        }else {
            m.addAttribute("url","?page="+page+"&size="+size+"&sort="+String.join("&sort=",sort)+"&filter="+String.join("&filter=",filter));
        }
        Collection<Task> list = service.getPage(Integer.parseInt(page),Integer.parseInt(size),userId,sort,filter);
        m.addAttribute("userUrl",userUrl);
        m.addAttribute("filter",String.join(", and by ",filter).replace(":"," value:"));
        m.addAttribute("sort",String.join(", and by ",sort).replace(":"," order:"));
        m.addAttribute("pageSize",Integer.parseInt(size));
        m.addAttribute("size",service.getSize(userId));
        m.addAttribute("position",page);
        m.addAttribute("list",list);
        return "task";
    }

    @RequestMapping(value="{param}/{id}",method = RequestMethod.PUT)
    public String editSave(String time,String done,String date,@PathVariable Integer userId,@ModelAttribute("task") Task task){
        task.setUser(userService.get(userId));
        task.setTimeadd(LocalDate.parse(time));
        task.setDeadline(LocalDate.parse(date));
        task.setIsdone(Boolean.parseBoolean(done));
        service.update(task);
        return "redirect: ../../?{param}";
    }

    @RequestMapping(value="{param}/{id}/edit")
    public String edit(@PathVariable Integer userId,@PathVariable Integer id, Model m){
        Task task = service.get(id);
        m.addAttribute("command",task);
        m.addAttribute("date",task.getTimeadd());
        return "taskEditForm";
    }
    @RequestMapping(value="{param}/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable int id){
        service.delete(id);
        return "redirect: ../?{param}";
    }
}

