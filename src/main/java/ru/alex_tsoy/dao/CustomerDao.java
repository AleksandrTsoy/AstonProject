package ru.alex_tsoy.dao;

import ru.alex_tsoy.entity.Customer;

import java.util.List;

public interface CustomerDao {
    Customer findById(int id);
    List<Customer> findAllCustomers();
    void save(Customer customer);
    void update(Customer customer);
    void delete(Customer customer);
}
