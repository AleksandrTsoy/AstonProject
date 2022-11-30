package ru.alex_tsoy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex_tsoy.entity.Employee;
import ru.alex_tsoy.exception.EntityNotFoundException;
import ru.alex_tsoy.service.EmployeeService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee) {
        try {
            employeeService.save(employee);
            return ResponseEntity.ok(employeeService.findById(employee.getId()));
        } catch (SQLException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee) {
        try {
            employeeService.update(employee);
            return ResponseEntity.ok(employeeService.findById(employee.getId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteEmployee(@RequestBody @Valid Employee employee) {
        try {
            employeeService.deleteById(employee.getId());
            return ResponseEntity.ok("Delete success.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/count-position")
    public ResponseEntity<Map<String, Long>> countEmployeeByPosition() {
        return ResponseEntity.ok(employeeService.countEmployeeByPosition());
    }

    @GetMapping("/count-project")
    public ResponseEntity<Map<String, Long>> countEmployeeByProject() {
        return ResponseEntity.ok(employeeService.countEmployeeByProject());
    }
}
