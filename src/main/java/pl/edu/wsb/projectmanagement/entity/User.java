package pl.edu.wsb.projectmanagement.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "usr")
public class User implements Comparable{
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
    @Email
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String username;
    private String phoneNumber;
    private String position;
    private String password;


    private boolean enabled=true;
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<Project> projectList;

    @OneToMany(mappedBy = "assignee")
    private List<Task> tasks;

    @ManyToMany
    private List<Project> collaborators;

    @Override
    public int compareTo(Object o) {
        return id-((User)o).getId();
    }
}
