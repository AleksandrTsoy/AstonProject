package ru.alex_tsoy.dao;

import ru.alex_tsoy.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDao {
    Employee findById(int id);

    List<Employee> findAllEmployees();

    void save(Employee employee);

    void update(Employee employee);

    void delete(Employee employee);

    Map<String, Long> countEmployeeByPosition();

    Map<String, Long> countEmployeeByProject();
}
