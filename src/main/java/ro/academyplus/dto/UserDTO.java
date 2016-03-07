package ro.academyplus.dto;

import ro.academyplus.dto.validators.Email;
import ro.academyplus.dto.validators.MatchFields;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by agheboianu on 04.03.2016.
 */
@MatchFields(first = "password", second = "confirmPassword", message = "Passwords do not match.")
public class UserDTO {

    @NotNull(message = "Field is null")
    @Size(min = 1, max = 30, message = "Size not ok.")
    @Email(message = "One user already has this email.")
    @org.hibernate.validator.constraints.Email
    private String email;
    @NotNull
    @Size(min = 8, max = 25, message = "Password must be between 8 and 25 characters.")
    private String password;
    private String confirmPassword;
    @NotNull
    @Size(min = 3, max = 25, message = "Size must be between 3 and 25 characters.")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
