package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private Date started;
    private Date deadline;
    private double progress;

    @OneToOne
    private User owner;

    @ManyToMany
    private List<User> teamList;

    @OneToMany
    private List<Backlog> backlogsList;

    @OneToMany
    private List<Sprint> sprintsList;

}
