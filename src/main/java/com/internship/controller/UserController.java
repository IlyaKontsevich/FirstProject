package com.internship.controller;

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
@RequestMapping("/user")
public class UserController {
    private Integer pageSize = 3;
    private String sortType = "id";
    @Autowired
    private IUserService service;

    @RequestMapping("/form")
    public String showForm(Model m){
        m.addAttribute("command", new User("username"));
        return "userForm";
    }

    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user){
        if(service.add(user) != null)
            return "redirect:0";
        else
            return "redirect:error";
    }

    @RequestMapping("/error")
    public String error(){
        return "error";
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
        return "redirect:0";
    }
    @RequestMapping("/{position}")
    public String view(@PathVariable Integer position,Model m){
        Collection<User> list = service.getPage(position,pageSize,sortType);
        m.addAttribute("pageSize",pageSize);
        m.addAttribute("size",service.getSize());
        m.addAttribute("position",position);
        m.addAttribute("list",list);
        return "viewUser";
    }


    @RequestMapping(value="{id}/editsave",method = RequestMethod.POST)
    public String editSave(@ModelAttribute("user") User user){
        service.update(user);
        return "redirect:/user/0";
    }

    @RequestMapping(value="{id}/edit")
    public String edit(@PathVariable int id, Model m){
        User user = service.get(id);
        m.addAttribute("command",user);
        return "userEditForm";
    }

    @RequestMapping(value="{id}/delete",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        service.delete(id);
        return "redirect:/user/0";
    }
}
