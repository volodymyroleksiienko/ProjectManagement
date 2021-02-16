package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int progress;
    private String startDate;
    private String endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    @OneToMany(mappedBy = "sprint")
    private List<Task> taskList;

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", progress=" + progress +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", project=" + project +
                '}';
    }
}
