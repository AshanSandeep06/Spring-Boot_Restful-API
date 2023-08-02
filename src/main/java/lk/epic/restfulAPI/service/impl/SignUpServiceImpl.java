package lk.epic.restfulAPI.service.impl;

import lk.epic.restfulAPI.config.service.JwtService;
import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.entity.User;
import lk.epic.restfulAPI.repo.UserRepo;
import lk.epic.restfulAPI.roles.Role;
import lk.epic.restfulAPI.service.SignUpService;
import lk.epic.restfulAPI.util.PasswordHashing;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordHashing passwordHashing;

    @Override
    public ResponseUtil signUp(SignUpDTO signUpDTO) {
        var user = User.builder()
                .firstName(signUpDTO.getFirstName())
                .lastName(signUpDTO.getLastName())
                .email(signUpDTO.getEmail())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .role(Role.USER)
                .build();

        if (!userRepo.existsById(signUpDTO.getEmail())) {
            User userIsSaved = userRepo.save(user);

            if (userIsSaved != null) {
                var jwtToken = jwtService.generateJwtToken(user);
                return new ResponseUtil("00", "Success", null);
            } else {
                System.out.println("sdsdsdsdsdsd");
                return new ResponseUtil("06", "Bad Request", null);
            }
        } else {
            return new ResponseUtil("04", "User Already Exists", null);
        }
    }
}
