package nl.jvb.mypaintpholiobe.domain.dtos;

import java.util.Date;

public class CommentDto {

    private Long id;
    private String author;
    private String commentText;
    private Date dateOfComment;
    private ArtProjectDto artProjectDto;
    public Long projectId;


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

    public ArtProjectDto getArtProjectDto() {
        return artProjectDto;
    }


    public void setId(Long id) { this.id = id; }

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

    public void setArtProjectDto(ArtProjectDto artProjectDto) {
        this.artProjectDto = artProjectDto;
    }
}
