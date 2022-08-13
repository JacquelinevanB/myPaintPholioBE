package nl.jvb.mypaintpholiobe.domain.dtos;

public class StudentDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private TeacherDto teacherDto;



    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public TeacherDto getTeacherDto() {
        return teacherDto;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTeacherDto(TeacherDto teacherDto) { this.teacherDto = teacherDto; }

}
