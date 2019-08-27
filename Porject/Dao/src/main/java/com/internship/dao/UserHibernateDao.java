package com.internship.dao;

import com.internship.model.User;
import com.internship.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.List;

@Component
public class UserHibernateDao implements IUserDao{

    @Override
    public Collection<User> getPage(Integer position, Integer pageSize, List<String> sortType, List<String> filter) {
        Predicate[] predicates = new Predicate[filter.size()];
        Order[] orders = new Order[sortType.size()];

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        for(int i =0; i< sortType.size();i++){
            String str = sortType.get(i);
            String[] parts = str.split(":");

            if(parts[1].equals("asc")){
                orders[i] = cb.asc(root.get(parts[0]));
            }if(parts[1].equals("desc")){
                orders[i] = cb.desc(root.get(parts[0]));
            }
        }
        cr.orderBy(orders);
        for(int i = 0; i < filter.size();i++){
            String str = filter.get(i);
            String[] parts = str.split(":");
            predicates[i] = cb.equal(root.get(parts[0]),parts[1]);
        }
        cr.select(root).where(predicates);
        Collection<User> users = session.createQuery(cr).setFirstResult(position).setMaxResults(pageSize).getResultList();
        session.close();
        return users;
    }

    @Override
    public Integer getSize() {
        return getAll().size();
    }

    @Override
    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public User add(User user) {
        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User WHERE email ='"+user.getEmail()+"'").list();
        if(users.size()!=0){
            return null;
        }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
        users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User WHERE email ='"+user.getEmail()+"'").list();
        return users.get(0);
    }

    @Override
    public User get(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public Collection<User> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        Collection<User> users = session.createQuery(cr).getResultList();
        session.close();
        return users;
    }

    @Override
    public boolean delete(Integer id) {
        if(get(id) == null)
            return false;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(get(id));
        tx1.commit();
        session.close();
        return true;
    }
}
