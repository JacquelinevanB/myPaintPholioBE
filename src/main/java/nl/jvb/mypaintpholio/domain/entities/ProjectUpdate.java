package nl.jvb.mypaintpholio.domain.entities;

import javax.persistence.*;

@Entity
public class ProjectUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reflexionText;
    private String date;
    private int sequenceNumber;

    @OneToOne
    FileUploadResponse file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artProject")
    private ArtProject artProject;

    public Long getId() {
        return id;
    }

    public String getReflexionText() {
        return reflexionText;
    }

    public String getDate() {
        return date;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public ArtProject getArtProject() {
        return artProject;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReflexionText(String reflexionText) {
        this.reflexionText = reflexionText;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public void setArtProject(ArtProject artProject) {
        this.artProject = artProject;
    }
}
