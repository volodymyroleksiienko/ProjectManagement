package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double progress;
    @ManyToOne
    private User assignee;
    private String created = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
    private String deadline;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    private List<TaskItem> itemList;

    @ManyToOne
    private Sprint sprint;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus=TaskStatus.OPEN;
}
