package ru.alex_tsoy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex_tsoy.entity.Customer;
import ru.alex_tsoy.entity.Project;
import ru.alex_tsoy.exception.EntityNotFoundException;
import ru.alex_tsoy.service.ProjectService;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllAccounts(@RequestBody @Valid Customer customer) {
        return ResponseEntity.ok(projectService.findAllProjectsByCustomer(customer.getId()));
    }

    @PostMapping("/create")
    public ResponseEntity<Project> create(@RequestBody @Valid Project project) {
        try {
            projectService.save(project);
            return ResponseEntity.ok(projectService.findById(project.getId()));
        } catch (SQLException | EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Project> update(@RequestBody @Valid Project project) {
        try {
            projectService.update(project);
            return ResponseEntity.ok(projectService.findById(project.getId()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody @Valid Project project) {
        try {
            projectService.deleteById(project.getId());
            return ResponseEntity.ok("Delete success.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
