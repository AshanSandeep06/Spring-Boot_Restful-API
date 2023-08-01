package lk.epic.restfulAPI.service.impl;

import io.jsonwebtoken.io.Decoder;
import lk.epic.restfulAPI.config.service.JwtService;
import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.entity.User;
import lk.epic.restfulAPI.repo.UserRepo;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.util.AuthenticationResponse;
import lk.epic.restfulAPI.util.PasswordHashing;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseUtil authenticate(LoginDTO loginDTO) {
        if (userRepo.existsById(loginDTO.getEmail())) {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getPassword()
                    )
            );

            if (authenticate != null) {
                var login = userRepo.findByEmail(loginDTO.getEmail());
                var jwtToken = jwtService.generateJwtToken(login);
                return new ResponseUtil("00", "Success",
                        AuthenticationResponse.builder().token(jwtToken).build()
                );
            } else {
                return new ResponseUtil("03", "Invalid Credentials", null);
            }
        } else {
            return new ResponseUtil("02", "No such user exists!", null);
        }
    }
}
