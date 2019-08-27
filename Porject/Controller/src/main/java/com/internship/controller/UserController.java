package com.internship.controller;

import com.internship.model.User;
import com.internship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;

    @RequestMapping("{url}/form")
    public String showForm(Model m){
        m.addAttribute("command", new User("username"));
        return "userForm";
    }

    @RequestMapping(value="{url}/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user){
        if(service.add(user) != null)
            return "redirect:../?{url}";
        else
            return "redirect:error";
    }

    @RequestMapping("{url}/error")
    public String error(@PathVariable String url,Model m){
        m.addAttribute("url",url);
        return "error";
    }

    @RequestMapping(value = "/")
    public String view(@RequestParam(value="page", defaultValue = "1") String page,
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
        Collection<User> list = service.getPage(Integer.parseInt(page), Integer.parseInt(size), sort, filter);
        m.addAttribute("filter",String.join(", and by ",filter).replace(":"," value:"));
        m.addAttribute("sort",String.join(", and by ",sort).replace(":"," order:"));
        m.addAttribute("pageSize",Integer.parseInt(size));
        m.addAttribute("size",service.getSize());
        m.addAttribute("position",page);
        m.addAttribute("list",list);
        return "viewUser";
    }


    @RequestMapping(value="{url}/{id}",method = RequestMethod.PUT)
    public String editSave(@PathVariable String url,@ModelAttribute("user") User user){
        service.update(user);
        return "redirect:/user/?{url}";
    }

    @RequestMapping(value="{url}/{id}/edit")
    public String edit(@PathVariable String url,@PathVariable int id, Model m){
        User user = service.get(id);
        m.addAttribute("command",user);
        return "userEditForm";
    }

    @RequestMapping(value="{url}/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable String url,@PathVariable int id){
        service.delete(id);
        return "redirect:/user/?{url}";
    }
}
