package com.internship.dao;

import com.internship.model.Task;
import com.internship.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Component
public class TaskHibernateDao implements ITaskDao {

    @Override
    public Collection<Task> getPage(Integer position, Integer userId, Integer pageSize, List<String> sortType, List<String> filter) {

        Predicate[] predicates = new Predicate[filter.size()+1];
        Order[] orders = new Order[sortType.size()];

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = cb.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);

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
        predicates[0] = cb.equal(root.get("user"),userId);
        for(int i = 0; i < filter.size();i++){
            String str = filter.get(i);
            String[] parts = str.split(":");
            if (parts[0].equals("isdone"))
                predicates[i+1] = cb.equal(root.get(parts[0]),Boolean.parseBoolean(parts[1]));
            else
            if (parts[0].equals("timeadd") || parts[0].equals("deadline"))
                predicates[i+1] = cb.equal(root.get(parts[0]), LocalDate.parse(parts[1]));
            else
                predicates[i+1] = cb.equal(root.get(parts[0]),parts[1]);
        }
        cr.select(root).where(predicates);
        Collection<Task> tasks = session.createQuery(cr).setFirstResult(position).setMaxResults(pageSize).getResultList();
        session.close();
        return tasks;
    }

    @Override
    public Integer getSize(Integer userId) {
        return getAll(userId).size();
    }

    @Override
    public void update(Task task) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    @Override
    public Task add(Task task) {
        List<Task> tasks = (List<Task>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Task WHERE userid = '"+task.getUser().getId()+"'AND name ='"+task.getName()+"'").list();
        if(tasks.size()!=0){
            return null;
        }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
        tasks = (List<Task>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Task WHERE userid = '"+task.getUser().getId()+"'AND name ='"+task.getName()+"'").list();
        return tasks.get(0);
    }

    @Override
    public Task get(Integer id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Task.class, id);
    }

    @Override
    public Collection<Task> getAll(Integer userId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Task> cr = cb.createQuery(Task.class);
        Root<Task> root = cr.from(Task.class);
        cr.select(root).where(cb.equal(root.get("user"),userId));
        Collection<Task> tasks = session.createQuery(cr).getResultList();
        session.close();
        return tasks;
    }

    @Override
    public boolean delete(Integer id) {
        if(get(id)==null){
            return false;
        }
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(get(id));
        tx1.commit();
        session.close();
        return true;
    }
}
