package com.internship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilterSortPage {

    @RequestMapping(value={"user/savefilter{type}","user/{userUrl}/{userId}/task/savefilter{type}"})
    public String saveFilter(@PathVariable String type,String filtervalue,@RequestParam(value="page", defaultValue = "1") String page,
                             @RequestParam(value="size", defaultValue = "3") String size,
                             @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                             @RequestParam(required = false, value="filter") List<String> filter){
        if(filter == null) {
            filter = new ArrayList<String>();
            filter.add("");
        }
        type = type.replace("{", "");
        type =type.replace("}", "");
        if(type.equals("nul")){
            filter.clear();
            return "redirect:./?page="+page+"&size="+size+"&sort="+String.join("&sort=",sort);
        }if(filtervalue != null)
            type = type+","+filtervalue+"";
        if(contains(filter,type)==-1){
            filter.add(type.replace(",",":"));
        }else{
            filter.remove(contains(filter,type));
            filter.add(type.replace(",",":"));
        }return "redirect:./?page="+page+"&size="+size+"&sort="+String.join("&sort=",sort)+"&filter="+String.join("&filter=",filter);
    }

    @RequestMapping(value = {"user/changepage{pages}","user/{userUrl}/{userId}/task/changepage{pages}"})
    public String changePage(@PathVariable String pages,@RequestParam(value="page", defaultValue = "1") String page,
                             @RequestParam(value="size", defaultValue = "3") String size,
                             @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                             @RequestParam(required = false, value="filter") List<String> filter){
        if(filter == null)
            return "redirect:./?page="+pages+"&size="+size+"&sort="+String.join("&sort=",sort);
        return "redirect:./?page="+pages+"&size="+size+"&sort="+String.join("&sort=",sort)+"&filter="+String.join("&filter=",filter);
    }

    @RequestMapping(value = {"user/pagesize{pageSize}","user/{userUrl}/{userId}/task/pagesize{pageSize}"})
    public String changePageSize(@PathVariable String pageSize,
                                 @RequestParam(value="page", defaultValue = "1") String page,
                                 @RequestParam(value="size", defaultValue = "3") String size,
                                 @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                                 @RequestParam(required = false, value="filter") List<String> filter){
        pageSize = pageSize.replace("{", "");
        pageSize = pageSize.replace("}", "");
        if(filter == null)
            return "redirect:./?page=1&size="+pageSize+"&sort="+String.join("&sort=",sort);
        return "redirect:./?page=1&size="+pageSize+"&sort="+String.join("&sort=",sort)+"&filter="+String.join("&filter=",filter);
    }

    public int contains(List<String> list,String string){
        String mass[] = string.split(",");
        int i=0;
        for(String str: list) {
            if(str.contains(mass[0]))
                return i;
            i++;
        }
        return -1;
    }

    @RequestMapping(value = {"user/changesort{sorttype}","user/{userUrl}/{userId}/task/changesort{sorttype}"})
    public String changeSortType(@RequestParam(value="page", defaultValue = "1") String page,
                                 @RequestParam(value="size", defaultValue = "3") String size,
                                 @RequestParam(value="sort",defaultValue = "name:asc") List<String> sort,
                                 @RequestParam(required = false, value="filter") List<String> filter,
                                 @PathVariable String sorttype){
        sorttype = sorttype.replace("{", "");
        sorttype = sorttype.replace("}", "");
        String mass[] = sorttype.split(",");
        if(mass[1].equals("nul")){
            if(contains(sort,sorttype)!=-1){
                sort.remove(contains(sort,sorttype));
            }if(!sort.isEmpty()){
                size = size+"&sort=";
            }
            if(filter == null)
                return "redirect:./?page="+page+"&size="+size+String.join("&sort=",sort);
            return "redirect:./?page="+page+"&size="+size+String.join("&sort=",sort)+"&filter="+String.join("&filter=",filter);
        }if(contains(sort,sorttype)==-1){
            sort.add(sorttype.replace(",",":"));
        }else{
            sort.remove(contains(sort,sorttype));
            sort.add(sorttype.replace(",",":"));
        }if(!sort.isEmpty()){
            size = size+"&sort=";
        }if(filter == null)
            return "redirect:./?page="+page+"&size="+size+String.join("&sort=",sort);
        return "redirect:./?page="+page+"&size="+size+String.join("&sort=",sort)+"&filter="+String.join("&filter=",filter);
    }
}
