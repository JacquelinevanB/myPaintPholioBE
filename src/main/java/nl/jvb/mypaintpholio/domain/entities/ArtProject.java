package nl.jvb.mypaintpholio.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class ArtProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String mediumType;
    private String dateStart;
    private String dateEnd;
    private String inspiration;
    private int height;
    private int width;
    private String description;
    private String subject;
    private boolean isFinished = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    private User user;

    @OneToMany(mappedBy = "artProject", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ProjectUpdate> projectUpdates;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMediumType() {
        return mediumType;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getInspiration() {
        return inspiration;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public User getUser() {
        return user;
    }

    public List<ProjectUpdate> getProjectUpdates() {
        return projectUpdates;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProjectUpdates(List<ProjectUpdate> projectUpdates) {
        this.projectUpdates = projectUpdates;
    }
}
