package ru.alex_tsoy.dao;

import ru.alex_tsoy.entity.Project;

import java.util.List;

public interface ProjectDao {
    Project findById(int id);
    List<Project> findAllProjectsByCustomer(int id);
    void save(Project project);
    void update(Project project);
    void delete(Project project);
}
