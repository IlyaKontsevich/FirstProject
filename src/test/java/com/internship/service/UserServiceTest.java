package com.internship.service;

import com.internship.dao.UserHibernateDao;
import com.internship.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
public class UserServiceTest {
    private UserHibernateDao dao = new UserHibernateDao();


    public User initialisationUser(){
        User user = new User("username");
        user.setEmail("email@mail.ru");
        user.setAge(23);
        return dao.add(user);
    }

    @Test
    public void update_CHANGE_PARAMETR() {
        User user = initialisationUser();
        Integer newAge = 22;
        user.setAge(newAge);
        dao.update(user);
        Assert.assertEquals(dao.get(user.getId()).getAge(),newAge);
        dao.delete(user.getId());
    }

    @Test
    public void getPage_RETURN_ONLY_USERS_WITH_ENTER_PARAMETR() {
        User user = initialisationUser();
        List<String> sort = new ArrayList();
        List<String> filter = new ArrayList();
        sort.add("name:desc");
        filter.add("name:username");
        filter.add("age:23");
        filter.add("email:email@mail.ru");
        List<User> list = (List<User>) dao.getPage(0,1,sort,filter);
        User resUser = list.get(0);
        Assert.assertTrue(resUser.getEmail().equals("email@mail.ru") &&
                resUser.getName().equals("username")&&resUser.getAge().equals(23));
        dao.delete(user.getId());
    }

    @Test
    public void add_NULL_IF_USER_HAVE_SAME_EMAIL() {
        User user = initialisationUser();
        Assert.assertNull(dao.add(user));
        dao.delete(user.getId());
    }

    @Test
    public void delete_GET_USER_RETURN_NULL() {
        User user = initialisationUser();
        dao.delete(user.getId());
        Assert.assertNull(dao.get(user.getId()));
    }

    @Test
    public void get_GET_RETURN_SAME_USER() {
        User user = initialisationUser();
        Assert.assertEquals(dao.get(user.getId()).getId(),user.getId());
        dao.delete(user.getId());
    }

    @Test
    public void getSize_CURRENT_SIZE_MORE_PRIMARY() {
        Integer tableSize = dao.getSize();
        User user = initialisationUser();
        Assert.assertTrue(tableSize < dao.getSize());
        dao.delete(user.getId());
    }
    @Test
    public void getAll_TABLE_NOT_NULL() {
        User user = initialisationUser();
        Assert.assertNotNull(dao.getAll());
        dao.delete(user.getId());
    }
}