package nl.jvb.mypaintpholiobe.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "photos")
public class PhotoElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;
    private int sequenceNumber;

    @OneToOne
    CommentElement commentElement;

    @OneToOne
    FileUploadResponse file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artproject_id")
    private ArtProject artProject;


    public Long getId() {
        return id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public CommentElement getCommentElement() {
        return commentElement;
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

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setCommentElement(CommentElement commentElement) {
        this.commentElement = commentElement;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }

    public void setArtProject(ArtProject artProject) {
        this.artProject = artProject;
    }
}
