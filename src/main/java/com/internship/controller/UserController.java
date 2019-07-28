package com.internship.controller;

import com.internship.dao.IUserDao;
import com.internship.model.User;
import com.internship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class UserController {
    @Autowired
    private IUserService service;

    @RequestMapping("/userForm")
    public String showForm(Model m){
        m.addAttribute("command", new User("username"));
        return "userForm";
    }

        @RequestMapping(value="/userSave",method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user){
        if(service.add(user) != null)
            return "redirect:/viewUser0";
        else
            return "redirect:/error";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
    }

    @RequestMapping("/viewUser{position}")
    public String view(@PathVariable Integer position,Model m){
        Collection<User> list = service.getPage(position);
        m.addAttribute("size",service.getSize());
        m.addAttribute("position",position);
        m.addAttribute("list",list);
        return "viewUser";
    }

    @RequestMapping(value="/userEditSave",method = RequestMethod.POST)
    public String userEditSave(@ModelAttribute("user") User user){
        service.update(user);
        return "redirect:/viewUser0";
    }

    @RequestMapping(value="/editUser/{id}")
    public String edit(@PathVariable int id, Model m){
        User user = service.get(id);
        m.addAttribute("command",user);
        return "userEditForm";
    }

    @RequestMapping(value="/deleteUser/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        service.delete(id);
        return "redirect:/viewUser0";
    }
}
