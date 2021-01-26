package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String picture;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    @ManyToMany
    private List<Project> projectList;

}
