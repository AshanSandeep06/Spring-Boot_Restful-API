package lk.epic.restfulAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserDTO {
    @Email(message = "Email Should be Valid")
    @NotEmpty(message = "Email Shouldn't be Empty")
    @NotBlank(message = "Email Shouldn't be Blank")
    private String email;
    @NotEmpty(message = "FirstName Shouldn't be Empty")
    @NotBlank(message = "FirstName Shouldn't be Blank")
    @Size(min = 3, max = 20, message = "FirstName must be between 3 and 20 characters")
    private String firstName;
    @NotEmpty(message = "LastName Shouldn't be Empty")
    @NotBlank(message = "LastName Shouldn't be Blank")
    @Size(min = 3, max = 20, message = "LastName must be between 3 and 20 characters")
    private String lastName;
    @NotEmpty(message = "Password Shouldn't be Empty")
    @NotBlank(message = "Password Shouldn't be Blank")
    @Size(min = 5, max = 10, message = "Password must be between 5 and 10 characters")
    private String password;
}
