package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Project implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String started;
    private String deadline;
    private int progress;

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "collaborators")
    private List<User> teamList;

    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
    private List<Backlog> backlogsList;

    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
    private List<Sprint> sprintsList;

    @Override
    public int compareTo(Object o) {
        return id-((Project) o).getId();
    }
}
