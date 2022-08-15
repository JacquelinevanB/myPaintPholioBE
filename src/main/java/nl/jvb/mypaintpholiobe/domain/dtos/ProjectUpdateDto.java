package nl.jvb.mypaintpholiobe.domain.dtos;

import nl.jvb.mypaintpholiobe.domain.entities.ArtProject;
import nl.jvb.mypaintpholiobe.domain.entities.FileUploadResponse;

import java.util.Date;

public class ProjectUpdateDto {

    private Long id;
    private String reflectionText;
    private Date date;
    private int sequenceNumber;
    private FileUploadResponse fileUploadResponse;
    private ArtProjectDto artProjectDto;

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

    public void setDate(Date date) {
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
