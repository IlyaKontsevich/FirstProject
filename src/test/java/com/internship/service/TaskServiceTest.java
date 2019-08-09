package com.internship.service;

import com.internship.dao.TaskHibernateDao;
import com.internship.dao.UserHibernateDao;
import com.internship.model.Task;
import com.internship.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    private Task task;
    private List<User> users = (List<User>) userDao.getAll();
    private Integer userId = users.get(0).getId();
    private Integer taskId;
    private Integer tableSize = dao.getAll(userId).size();

    @Before
    public void initialisationTask(){
    task = new Task();
    task.setUser(users.get(0));
    task.setName("dosumthing");
    task.setIsdone(false);
    task.setDeadline(LocalDate.parse("2019-12-12"));
    task.setTimeadd(LocalDate.parse("2019-12-12"));
    task.setPriority("low");
    task = dao.add(task);
    taskId = task.getId();
    }

    @After
    public void deleteTask(){
        dao.delete(taskId);
    }

    @Test
    public void getPage_RETURN_ONLY_TASKS_WITH_ENTER_PARAMETR() {
        List<String> sort = new ArrayList();
        List<String> filter = new ArrayList();
        sort.add("name:desc");
        filter.add("name:dosumthing");
        filter.add("deadline:2019-12-12");
        filter.add("timeadd:2019-12-12");
        filter.add("isdone:false");
        filter.add("priority:low");
        List<Task> list = (List<Task>) dao.getPage(0,userId,1,sort,filter);
        Task resTask = list.get(0);
        Assert.assertTrue(resTask.getName().equals("dosumthing") &&
                resTask.getPriority().equals("low")&&resTask.getIsdone().equals(false));
    }

    @Test
    public void update_CHANGE_PARAMETR() {
        task.setIsdone(true);
        dao.update(task);
        Assert.assertTrue(dao.get(taskId).getIsdone());
    }

    @Test
    public void add_NULL_IF_TASK_HAVE_SAME_NAME() {
        Assert.assertNull(dao.add(task));
    }

    @Test
    public void delete_GET_TASK_RETURN_NULL() {
        dao.delete(taskId);
        Assert.assertNull(dao.get(taskId));
    }

    @Test
    public void get_GET_RETURN_SAME_TASK() {
        Assert.assertEquals(dao.get(taskId).getId(),task.getId());
    }

    @Test
    public void getSize_CURRENT_SIZE_MORE_PRIMARY() {
        Assert.assertTrue(tableSize < dao.getSize(userId));
    }

    @Test
    public void getAll_TABLE_NOT_NULL() {
        Assert.assertNotNull(dao.getAll(userId));
    }
}