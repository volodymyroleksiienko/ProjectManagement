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

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "sprint",cascade = CascadeType.REMOVE)
    private List<Task> taskList;
}
