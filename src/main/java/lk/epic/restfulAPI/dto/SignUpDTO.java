package lk.epic.restfulAPI.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class SignUpDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
