package pl.edu.wsb.projectmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean status;

    @ManyToOne
    private Task task;

    public TaskItem(String description, boolean status) {
        this.description = description;
        this.status = status;
    }
}
