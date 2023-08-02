package lk.epic.restfulAPI.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class LoginDTO {
    @Email(message = "Email is not valid", regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
    @NotEmpty(message = "Email Shouldn't be Empty")
    @NotBlank(message = "Email Shouldn't be Blank")
    private String email;
    @NotEmpty(message = "Password Shouldn't be Empty")
    @NotBlank(message = "Password Shouldn't be Blank")
    @Size(min = 5, max = 10, message = "Password must be between 5 and 10 characters")
    private String password;
}
