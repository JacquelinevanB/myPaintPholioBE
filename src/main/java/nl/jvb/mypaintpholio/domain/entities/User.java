package nl.jvb.mypaintpholio.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
//    @Column(nullable = false, unique = true)
    private String username;

//    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private Boolean enabled = true;

//    @Column
    private String apiKey;

//    @Column(nullable = false)
    private String firstName;

//    @Column(nullable = false)
    private String lastName;

//    @Column(nullable = false)
    private String emailAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ArtProject> artProjects;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne
    FileUploadResponse file;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public String getApiKey() {
        return apiKey;
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

    public List<ArtProject> getArtProjects() {
        return artProjects;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

    public void addAuthority(Authority authority) { this.authorities.add(authority); }

    public void removeAuthority(Authority authority) { this.authorities.remove(authority); }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public void setArtProjects(List<ArtProject> artProjects) {
        this.artProjects = artProjects;
    }
}