package lk.epic.restfulAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class SignUpDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
