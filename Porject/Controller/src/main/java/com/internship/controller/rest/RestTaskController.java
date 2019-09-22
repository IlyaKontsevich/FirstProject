/*package com.internship.controller.rest;

import com.internship.model.Task;
import com.internship.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/{userId}/task")
public class RestTaskController {
    @Autowired
    private ITaskService service;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Collection<Task> view(@PathVariable Integer userId,
                                 @RequestParam(value="page", defaultValue = "1") String page,
                                 @RequestParam(value="size", defaultValue = "10") String size,
                                 @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                                 @RequestParam(required = false, value="filter") List<String> filter){
        if(filter == null) {
            filter = new ArrayList<String>();
            filter.add("");
        }
        Collection<Task> list = service.getPage(Integer.parseInt(page),Integer.parseInt(size),userId,sort,filter);
        return list;
    }

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void delete( @PathVariable int id){
        service.delete(id);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    public void edit(@ModelAttribute("task") Task task){
        service.update(task);
    }

    @RequestMapping(value="/{id}",method = RequestMethod.POST)
    public Task save(@ModelAttribute("task") Task task){
        if(service.add(task) != null)
            return task;
        else
            return null;
    }
}
*/