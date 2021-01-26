package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] picture;
    private String pictureType;
    private String pictureName;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private String password;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<Project> projectList;

    @OneToMany(mappedBy = "assignee")
    private List<Task> tasks;

    @ManyToMany
    private List<Project> collaborators;
}
