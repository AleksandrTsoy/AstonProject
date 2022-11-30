package ru.alex_tsoy.dao.implementation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class BaseSession {
    public SessionFactory getSession() {
        return new Configuration().configure().buildSessionFactory();
    }
}
