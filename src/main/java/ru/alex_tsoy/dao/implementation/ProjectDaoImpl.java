package ru.alex_tsoy.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.ProjectDao;
import ru.alex_tsoy.entity.Project;

import java.util.List;

@Service
public class ProjectDaoImpl extends BaseSession implements ProjectDao {

    public Project findById(int id) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Project p join fetch p.customer where p.id=:id", Project.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public List<Project> findAllProjectsByCustomer(int id) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Project p join fetch p.customer where p.customer.id=:id", Project.class)
                    .setParameter("id", id)
                    .getResultList();
        }
    }

    public void save(Project project) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        }
    }

    public void update(Project project) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
        }
    }

    public void delete(Project project) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(project);
            transaction.commit();
        }
    }
}
