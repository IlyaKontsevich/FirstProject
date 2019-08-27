package com.internship.dao;/*package com.com.internship.dao;

import com.com.internship.mappers.UserMapper;
import com.com.internship.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;

@Repository
public class UserSpringJdbcDao implements IUserDao{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserSpringJdbcDao(JdbcTemplate jdbcTemplate) {
          this.jdbcTemplate = jdbcTemplate;
          jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users(id SERIAL PRIMARY KEY, name varchar NOT NULL);");
    }

    @Override
    public User add(User user) {
        if(0 != jdbcTemplate.queryForObject("Select COUNT(*) from users WHERE email = ?",new Object[] {user.getEmail()}, Integer.class)) {
            return null;
        }
        jdbcTemplate.update("INSERT INTO  users (name,age,email) VALUES  (?,?,?)",user.getName(),user.getAge(),user.getEmail());
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", new Object[]{user.getEmail()}, new UserMapper());
   }

    @Override
    public User get(Integer id) {
        User user;
        try {
        user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[]{id}, new UserMapper());
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> users = jdbcTemplate.query("Select * from users", new UserMapper());
        return users;
    }
    @Override
    public Collection<User> getPage(Integer position, Integer pageSize, List<String> sortType,List<String> filter){
        Collection<User> users = jdbcTemplate.query("Select * from users "+filter+sortType+
                " LIMIT '"+pageSize+"' OFFSET '"+position+"'", new UserMapper());
        return users;
    }
    @Override
    public Integer getSize() {
        Integer count = jdbcTemplate.queryForObject("Select COUNT(*) from users", Integer.class);
        return count;
    }

    @Override
    public void update(User user) {
        String sql = "update users set name = ?, age = ?, email = ? where id = ? ";
        jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getEmail(),user.getId());
    }

    @Override
    public boolean delete(Integer id) {
        int rez = jdbcTemplate.update("DELETE FROM users WHERE id = ?",id);
        if(rez == 0) {
            return false;
        }else{
            return true;
        }
    }
}
*/