package com.internship.service;

import com.internship.dao.UserHibernateDao;
import com.internship.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:hibernate.cfg.xml")
@Transactional
public class UserServiceTest {
    private UserHibernateDao dao = new UserHibernateDao();
    private User user;
    private Integer userId;
    private Integer tableSize = dao.getSize();

    @Before
    public User initialisationUser(){
        User user = new User("username");
        user.setEmail("email@mail.ru");
        user.setAge(23);
        return dao.add(user);
    }
    @After
    public void deleteUser(){
        dao.delete(userId);
    }

    @Test
    public void update_CHANGE_PARAMETR() {
        Integer newAge = 22;
        user.setAge(newAge);
        dao.update(user);
        Assert.assertEquals(dao.get(userId).getAge(),newAge);
    }

    @Test
    public void getPage_RETURN_ONLY_USERS_WITH_ENTER_PARAMETR() {
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
    }

    @Test
    public void add_NULL_IF_USER_HAVE_SAME_EMAIL() {
        Assert.assertNull(dao.add(user));
    }

    @Test
    public void delete_GET_USER_RETURN_NULL() {
        dao.delete(userId);
        Assert.assertNull(dao.get(userId));
    }

    @Test
    public void get_GET_RETURN_SAME_USER() {
        Assert.assertEquals(dao.get(userId).getId(),user.getId());
    }

    @Test
    public void getSize_CURRENT_SIZE_MORE_PRIMARY() {
        Assert.assertTrue(tableSize < dao.getSize());
    }
    @Test
    public void getAll_TABLE_NOT_NULL() {
        Assert.assertNotNull(dao.getAll());
    }
}