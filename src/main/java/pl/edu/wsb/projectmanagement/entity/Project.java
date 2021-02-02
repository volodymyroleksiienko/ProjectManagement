package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    private Set<User> teamList;

    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
    private List<Backlog> backlogsList;

    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
    private List<Sprint> sprintsList;

    @Override
    public int compareTo(Object o) {
        return id-((Project) o).getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(started, project.started) &&
                Objects.equals(deadline, project.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, started, deadline);
    }
}
