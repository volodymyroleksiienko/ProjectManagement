package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TaskItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean status;

    @ManyToOne
    private Task task;
}
