package nl.jvb.mypaintpholiobe.domain.dtos;

public class PhotoElementDto {

    private Long id;

    private Long projectId;
    private int sequenceNumber;
    private CommentElementDto commentElementDto;
    private ArtProjectDto artProjectDto;


    public Long getId() {
        return id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public CommentElementDto getCommentElementDto() {
        return commentElementDto;
    }

    public ArtProjectDto getArtProjectDto() {
        return artProjectDto;
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

    public void setCommentElementDto(CommentElementDto commentElementDto) {
        this.commentElementDto = commentElementDto;
    }

    public void setArtProjectDto(ArtProjectDto artProjectDto) {
        this.artProjectDto = artProjectDto;
    }
}
