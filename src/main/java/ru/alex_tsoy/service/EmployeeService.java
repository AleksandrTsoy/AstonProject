package ru.alex_tsoy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.EmployeeDao;
import ru.alex_tsoy.entity.Employee;
import ru.alex_tsoy.exception.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeDao employeeDao;

    public Employee findById(int id) throws EntityNotFoundException {
        Employee employee = employeeDao.findById(id);
        if (employee != null) {
            return employee;
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }

    public void save(Employee employee) throws SQLException {
        employeeDao.save(employee);
    }

    public void update(Employee updateEmployee) throws EntityNotFoundException {
        Employee employee = employeeDao.findById(updateEmployee.getId());
        if (employee != null) {
            employee.setLastName(updateEmployee.getLastName());
            employee.setEmail(updateEmployee.getEmail());
            employeeDao.update(employee);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public void deleteById(int id) throws EntityNotFoundException {
        Employee employee = employeeDao.findById(id);
        if (employee != null) {
            employeeDao.delete(employee);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public Map<String, Long> countEmployeeByPosition() {
        return employeeDao.countEmployeeByPosition();
    }

    public Map<String, Long> countEmployeeByProject() {
        return employeeDao.countEmployeeByProject();
    }
}
