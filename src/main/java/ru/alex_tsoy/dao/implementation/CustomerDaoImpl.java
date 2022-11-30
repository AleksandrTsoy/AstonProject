package ru.alex_tsoy.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.CustomerDao;
import ru.alex_tsoy.entity.Customer;

import java.util.List;

@Service
public class CustomerDaoImpl extends BaseSession implements CustomerDao {

    public Customer findById(int id) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select c from Customer c where c.id=:id", Customer.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public List<Customer> findAllCustomers() {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select c from Customer c", Customer.class)
                    .getResultList();
        }
    }

    public void save(Customer customer) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
    }

    public void update(Customer customer) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        }
    }

    public void delete(Customer customer) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(customer);
            transaction.commit();
        }
    }
}
