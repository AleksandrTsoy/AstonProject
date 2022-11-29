package ru.alex_tsoy.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.EmployeeDao;
import ru.alex_tsoy.entity.Employee;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeDaoImpl extends BaseSession implements EmployeeDao {

    public Employee findById(int id) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select e from Employee e join fetch e.position where e.id=:id", Employee.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public List<Employee> findAllEmployees() {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select e from Employee e", Employee.class)
                    .getResultList();
        }
    }

    public void save(Employee employee) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    public void update(Employee employee) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    public void delete(Employee employee) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(employee);
            transaction.commit();
        }
    }

    public Map<String, Long> countEmployeeByPosition() {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("select p.position, count(e) from Employee as e\n" +
                    "left join Position as p on e.position.id = p.id\n" +
                    "group by p.position");
            List<Object[]> rows = query.list();
            Map<String, Long> result = new HashMap<>();
            for (Object[] row : rows) {
                result.put(row[0].toString(), (Long) row[1]);
            }
            return result;
        }
    }

    public Map<String, Long> countEmployeeByProject() {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Query query = session.createSQLQuery("select p.name, count(e) from employee.employee_to_project as etp\n" +
                    "    left join employee.employee e on etp.employee_id = e.employee_id\n" +
                    "    left join employee.project p on etp.project_id = p.project_id\n" +
                    "    group by p.name");
            List<Object[]> rows = query.list();
            Map<String, Long> result = new HashMap<>();
            for (Object[] row : rows) {
                result.put(row[0].toString(), ((BigInteger) row[1]).longValue());
            }
            return result;
        }
    }
}
