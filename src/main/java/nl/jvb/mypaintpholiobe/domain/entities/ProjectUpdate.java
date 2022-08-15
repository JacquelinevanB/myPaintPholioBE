package nl.jvb.mypaintpholiobe.domain.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "update")
public class ProjectUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reflectionText;
    private Date date;
    private int sequenceNumber;


    @OneToOne
    FileUploadResponse file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artproject_id")
    private ArtProject artProject;


    public Long getId() {
        return id;
    }

    public String getReflectionText() {
        return reflectionText;
    }

    public Date getDate() {
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

    public void setReflectionText(String reflectionText) {
        this.reflectionText = reflectionText;
    }

    public void setDate(Date date) {
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
