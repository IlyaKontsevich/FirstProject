package com.internship.service;
import com.internship.dao.ITaskDao;
import com.internship.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class TaskService implements ITaskService{
    @Autowired
    @Qualifier("taskHibernateDao")
    private ITaskDao dao;
    @Autowired
    private IUserService service;


    @Override
    public Collection<Task> getPage(Integer position, Integer pageSize, Integer userId, List<String> sortType,List<String> filter){
        /*String sort;
        sort = " ORDER BY " + String.join(",", sortType);
        sort = sort.replace(":", " ");
        String filters;
        if(filter.size()!=1){
            if(filter.get(0).equals(""))
                filter.remove(0);
            filters = String.join("' AND ",filter);
            filters = " AND "+filters.replace(":","='")+"'";
        }else {
            if(filter.get(0).equals(""))
                filters ="";
            else{
                filters = String.join(" AND ",filter);
                filters = " AND "+filters.replace(":","='")+"'";
            }
        }*/
        if(filter.get(0).equals("")){
            filter.remove(0);
        }
        if(position != 1) {
            position = position + pageSize - 2;
        }else{
            position = position-1;
        }
        return dao.getPage(position,userId,pageSize,sortType,filter);
    }

    @Override
    public void update(Task task) {
        dao.update(task);
    }

    @Override
    public Task add(Task task) {
        //task.setUser(service.get(task.getUserId()));
        return dao.add(task);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public Task get(Integer id) {
        return dao.get(id);
    }


    @Override
    public Integer getSize(Integer userId) {
        return dao.getSize(userId);
    }

    @Override
    public Collection<Task> getAll() {
        return dao.getAll(1);
    }

}
