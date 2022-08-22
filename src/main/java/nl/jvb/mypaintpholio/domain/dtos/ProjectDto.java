package nl.jvb.mypaintpholio.domain.dtos;

public class ProjectDto {
    private Long id;
//    @NotEmpty
    private String title;
    private String mediumType;
//    @NotEmpty
    private String dateStart;
    private String dateEnd;
    private String inspiration;
    private int height;
    private int width;
    private String description;
    private String subject;
    private Boolean isFinished = false;
    private PersonDto personDto;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMediumType() {
        return mediumType;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getInspiration() {
        return inspiration;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getDescription() {
        return description;
    }

    public String getSubject() {
        return subject;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public PersonDto getUserDto() {
        return personDto;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setInspiration(String inspiration) {
        this.inspiration = inspiration;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setUserDto(PersonDto personDto) {
        this.personDto = personDto;
    }

}
