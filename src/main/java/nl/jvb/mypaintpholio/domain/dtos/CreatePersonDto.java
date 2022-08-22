package nl.jvb.mypaintpholio.domain.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.jvb.mypaintpholio.domain.entities.Authority;

import java.util.Set;

public class CreatePersonDto {

//    @NotEmpty
    public String username;

//    @NotEmpty
//    @Size(min = 8)
    public String password;

//    @NotEmpty
    public Boolean enabled;

    public String apiKey;

//    @NotEmpty
    public String firstName;

//    @NotEmpty
    public String lastName;

//    @NotEmpty
//    @Email
    public String emailAddress;
    @JsonSerialize
    public Set<Authority> authorities;


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
}
