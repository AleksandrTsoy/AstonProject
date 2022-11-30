package ru.alex_tsoy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.CustomerDao;
import ru.alex_tsoy.entity.Customer;
import ru.alex_tsoy.exception.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;

    public Customer findById(int id) throws EntityNotFoundException {
        Customer customer = customerDao.findById(id);
        if (customer != null) {
            return customer;
        } else {
            throw new EntityNotFoundException("Position not found.");
        }
    }

    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomers();
    }

    public void save(Customer customer) {
        customerDao.save(customer);
    }

    public void update(Customer updateCustomer) throws EntityNotFoundException {
        Customer customer = customerDao.findById(updateCustomer.getId());
        if (customer != null) {
            customer.setName(updateCustomer.getName());
            customer.setEmail(updateCustomer.getEmail());
            customerDao.update(customer);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public void deleteById(int id) throws EntityNotFoundException {
        Customer customer = customerDao.findById(id);
        if (customer != null) {
            customerDao.delete(customer);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }
}
