package nl.jvb.mypaintpholiobe.domain.dtos;

public class CreateStudentDto {
//    @NotEmpty
//    @Size(min = 3, max = 50)
    private String username;
//    @NotEmpty
//    @Size(min = 8, max = 50)
    private String password;
    private String firstName;
    private String lastName;
//    @NotEmpty
//    @Email
    private String emailAddress;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
