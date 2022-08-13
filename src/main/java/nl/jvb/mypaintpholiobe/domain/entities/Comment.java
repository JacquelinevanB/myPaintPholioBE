package nl.jvb.mypaintpholiobe.domain.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String commentText;
    private Date dateOfComment;
    private Long projectId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artproject_id")
    private ArtProject artProject;


    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getCommentText() {
        return commentText;
    }

    public Date getDateOfComment() {
        return dateOfComment;
    }

    public Long getProjectId() {
        return projectId;
    }

    public ArtProject getArtProject() {
        return artProject;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setDateOfComment(Date dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public void setArtProject(ArtProject artProject) {
        this.artProject = artProject;
    }
}
