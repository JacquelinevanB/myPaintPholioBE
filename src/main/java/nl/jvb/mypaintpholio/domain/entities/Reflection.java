package nl.jvb.mypaintpholio.domain.entities;

import javax.persistence.*;

@Entity
public class Reflection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reflexionText;
    private String dateMade;
    private int sequenceNumber;

    @OneToOne
    FileUploadResponse file;

    @ManyToOne
    @JoinColumn(name = "project")
    public Project project;

    public Long getId() {
        return id;
    }

    public String getReflexionText() {
        return reflexionText;
    }

    public String getDateMade() {
        return dateMade;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public Project getProject() {
        return project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReflexionText(String reflexionText) {
        this.reflexionText = reflexionText;
    }

    public void setDateMade(String dateMade) {
        this.dateMade = dateMade;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
