package com.internship.service;

import com.internship.dao.TaskHibernateDao;
import com.internship.dao.UserHibernateDao;
import com.internship.model.Task;
import com.internship.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class TaskServiceTest {
    private UserHibernateDao userDao = new UserHibernateDao();
    private TaskHibernateDao dao = new TaskHibernateDao();
    private List<User> users = (List<User>) userDao.getAll();

    public Task initialisationTask(){
    Task task = new Task();
    task.setUser(users.get(0));
    task.setName("dosumthing");
    task.setIsdone(false);
    task.setDeadline(LocalDate.parse("2019-12-12"));
    task.setTimeadd(LocalDate.parse("2019-12-12"));
    task.setPriority("low");
    return dao.add(task);
    }

    @Test
    public void getPage_RETURN_ONLY_TASKS_WITH_ENTER_PARAMETR() {
        Task task = initialisationTask();
        List<String> sort = new ArrayList();
        List<String> filter = new ArrayList();
        sort.add("name:desc");
        filter.add("name:dosumthing");
        filter.add("deadline:2019-12-12");
        filter.add("timeadd:2019-12-12");
        filter.add("isdone:false");
        filter.add("priority:low");
        List<Task> list = (List<Task>) dao.getPage(0,users.get(0).getId(),1,sort,filter);
        Task resTask = list.get(0);
        Assert.assertTrue(resTask.getName().equals("dosumthing") &&
                resTask.getPriority().equals("low")&&resTask.getIsdone().equals(false));
        dao.delete(task.getId());
    }

    @Test
    public void update_CHANGE_PARAMETR() {
        Task task = initialisationTask();
        task.setIsdone(true);
        dao.update(task);
        Assert.assertTrue(dao.get(task.getId()).getIsdone());
        dao.delete(task.getId());
    }

    @Test
    public void add_NULL_IF_TASK_HAVE_SAME_NAME() {
        Task task = initialisationTask();
        Assert.assertNull(dao.add(task));
        dao.delete(task.getId());
    }

    @Test
    public void delete_GET_TASK_RETURN_NULL() {
        Task task = initialisationTask();
        dao.delete(task.getId());
        Assert.assertNull(dao.get(task.getId()));
    }

    @Test
    public void get_GET_RETURN_SAME_TASK() {
        Task task = initialisationTask();
        Assert.assertEquals(dao.get(task.getId()).getId(),task.getId());
        dao.delete(task.getId());
    }

    @Test
    public void getSize_CURRENT_SIZE_MORE_PRIMARY() {
        Integer tableSize = dao.getSize(users.get(0).getId());
        Task task = initialisationTask();
        Assert.assertTrue(tableSize < dao.getSize(users.get(0).getId()));
        dao.delete(task.getId());
    }

    @Test
    public void getAll_TABLE_NOT_NULL() {
        Task task = initialisationTask();
        Assert.assertNotNull(dao.getAll(users.get(0).getId()));
        dao.delete(task.getId());
    }
}