package nl.jvb.mypaintpholiobe.domain.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String commentText;
    private Date dateOfComment;

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
}
