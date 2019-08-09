package com.internship.controller;

import com.internship.model.User;
import com.internship.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {
    @Autowired
    private IUserService service;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Collection<User> view(@RequestParam(value="page", defaultValue = "1") String page,
                       @RequestParam(value="size", defaultValue = "3") String size,
                       @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                       @RequestParam(value="filter",defaultValue = "age:1") List<String> filter){
        Collection<User> list = service.getPage(Integer.parseInt(page),Integer.parseInt(size),sort,filter);
        return list;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void delete( @PathVariable int id){
        service.delete(id);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public void edit(@ModelAttribute("user") User user){
        service.update(user);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.POST)
    public User save(@ModelAttribute("user") User user){
        if(service.add(user) != null)
            return user;
        else
            return null;
    }
}
