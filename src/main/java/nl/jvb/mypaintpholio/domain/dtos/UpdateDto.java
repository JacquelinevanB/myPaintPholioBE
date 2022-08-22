package nl.jvb.mypaintpholio.domain.dtos;

import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;


public class UpdateDto {

    private Long id;
    private String reflectionText;
    private String date;
    private int sequenceNumber;
    private FileUploadResponse fileUploadResponse;
    private ProjectDto projectDto;

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

    public ProjectDto getProjectDto() {
        return projectDto;
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

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }
}
