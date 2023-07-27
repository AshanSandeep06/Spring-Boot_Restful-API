package lk.epic.restfulAPI.service.impl;

import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.entity.User;
import lk.epic.restfulAPI.repo.UserRepo;
import lk.epic.restfulAPI.service.LoginService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseUtil login(LoginDTO loginDTO) {
        if (userRepo.existsById(loginDTO.getEmail())) {
            User login = userRepo.findUserByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
            if (login != null) {
                return new ResponseUtil("00", "Success", "JWT Token");
            } else {
                return new ResponseUtil("03", "Invalid Credentials", null);
            }
        } else {
            return new ResponseUtil("02", "No such user exists!", null);
        }
    }
}
