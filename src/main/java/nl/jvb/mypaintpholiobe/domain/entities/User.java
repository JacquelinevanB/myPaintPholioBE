package nl.jvb.mypaintpholiobe.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(nullable = false, unique = true)
    private String username;
//    @Column(nullable = false, length = 50)
    private String password;
//    @Column(nullable = false, length = 50)
    private String firstName;
//    @Column(nullable = false, length = 50)
    private String lastName;
//    @Column(nullable = false)
    private String emailAddress;


    @OneToOne
    FileUploadResponse file;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<ArtProject> artProjects = new HashSet<>();


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public Set<ArtProject> getArtProjects() {
        return artProjects;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public void setArtProjects(Set<ArtProject> artProjects) {
        this.artProjects = artProjects;
    }
}