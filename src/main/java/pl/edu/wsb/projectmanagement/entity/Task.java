package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private double progress;
    @ManyToOne
    private User assignee;
    private Date created;
    private Date deadline;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    private List<TaskItem> itemList;

    @ManyToOne
    private Sprint sprint;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}
