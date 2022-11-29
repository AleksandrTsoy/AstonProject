package ru.alex_tsoy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex_tsoy.entity.Customer;
import ru.alex_tsoy.exception.EntityNotFoundException;
import ru.alex_tsoy.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllAccounts() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> create(@RequestBody @Valid Customer customer) {
        try {
            customerService.save(customer);
            return ResponseEntity.ok(customerService.findById(customer.getId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Customer> update(@RequestBody @Valid Customer customer) {
        try {
            customerService.update(customer);
            return ResponseEntity.ok(customerService.findById(customer.getId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody @Valid Customer customer) {
        try {
            customerService.deleteById(customer.getId());
            return ResponseEntity.ok("Delete success.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
