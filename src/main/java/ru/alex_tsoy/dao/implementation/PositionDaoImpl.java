package ru.alex_tsoy.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.PositionDao;
import ru.alex_tsoy.entity.Position;

import java.util.List;

@Service
public class PositionDaoImpl extends BaseSession implements PositionDao {

    public Position findById(int id) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Position p where p.id=:id", Position.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    public List<Position> findAllPositions() {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            return session.createQuery("select p from Position p", Position.class)
                    .getResultList();
        }
    }

    public void save(Position position) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(position);
            transaction.commit();
        }
    }

    public void update(Position position) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(position);
            transaction.commit();
        }
    }

    public void delete(Position position) {
        try (SessionFactory sessionFactory = getSession();
             Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(position);
            transaction.commit();
        }
    }
}
