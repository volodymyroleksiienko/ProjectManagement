package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Backlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private int priority;

    @ManyToOne
    private Project project;
}
