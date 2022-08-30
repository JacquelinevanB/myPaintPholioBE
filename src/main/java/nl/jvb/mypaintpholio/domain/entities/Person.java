package nl.jvb.mypaintpholio.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Person extends User {

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    List<Project> projects;

    @OneToOne
    FileUploadResponse file;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}

