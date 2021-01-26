package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double progress;
    private User assignee;
    private Date created;
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}
