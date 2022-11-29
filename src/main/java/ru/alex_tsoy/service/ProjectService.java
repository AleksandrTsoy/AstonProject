package ru.alex_tsoy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex_tsoy.dao.ProjectDao;
import ru.alex_tsoy.entity.Project;
import ru.alex_tsoy.exception.EntityNotFoundException;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDao projectDao;

    public Project findById(int id) throws EntityNotFoundException {
        Project project = projectDao.findById(id);
        if (project != null) {
            return project;
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public List<Project> findAllProjectsByCustomer(int id) {
        return projectDao.findAllProjectsByCustomer(id);
    }

    public void save(Project project) throws SQLException {
        projectDao.save(project);
    }

    public void update(Project updateProject) throws EntityNotFoundException {
        Project project = projectDao.findById(updateProject.getId());
        if (project != null) {
            project.setName(updateProject.getName());
            project.setEnd(updateProject.getEnd());
            projectDao.update(project);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }

    public void deleteById(int id) throws EntityNotFoundException {
        Project project = projectDao.findById(id);
        if (project != null) {
            projectDao.delete(project);
        } else {
            throw new EntityNotFoundException("Employee not found.");
        }
    }
}
