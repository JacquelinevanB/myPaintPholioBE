package nl.jvb.mypaintpholio.domain.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.jvb.mypaintpholio.domain.entities.Authority;
import nl.jvb.mypaintpholio.domain.entities.FileUploadResponse;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class PersonDto {
    public String username;

    public String password;

    public Boolean enabled;

    public String apiKey;

    public String firstName;

    public String lastName;

    public String emailAddress;
    @JsonSerialize
    public Set<Authority> authorities;

    public FileUploadResponse file;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getApiKey() {
        return apiKey;
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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public FileUploadResponse getFile() {
        return file;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
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

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setFileUploadResponse(FileUploadResponse file) {
        this.file = file;
    }
}
