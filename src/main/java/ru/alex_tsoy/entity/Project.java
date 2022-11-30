package ru.alex_tsoy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Project {
    private int id;
    private String name;
    private LocalDate end;
    private Customer customer;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", end=" + end +
                ", customer=" + customer +
                '}';
    }
}
