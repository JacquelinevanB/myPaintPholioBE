package nl.jvb.mypaintpholio.domain.dtos;

import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;


public class ProjectUpdateDto {

    private Long id;
    private String reflectionText;
    private String date;
    private int sequenceNumber;
    private FileUploadResponse fileUploadResponse;
    private ArtProjectDto artProjectDto;

    public Long getId() {
        return id;
    }

    public String getReflectionText() {
        return reflectionText;
    }

    public String getDate() {
        return date;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public FileUploadResponse getFileUploadResponse() {
        return fileUploadResponse;
    }

    public ArtProjectDto getArtProjectDto() {
        return artProjectDto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReflectionText(String reflectionText) {
        this.reflectionText = reflectionText;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public void setFileUploadResponse(FileUploadResponse fileUploadResponse) {
        this.fileUploadResponse = fileUploadResponse;
    }

    public void setArtProjectDto(ArtProjectDto artProjectDto) {
        this.artProjectDto = artProjectDto;
    }
}
