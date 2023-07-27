package lk.epic.restfulAPI.service.impl;

import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.entity.User;
import lk.epic.restfulAPI.repo.UserRepo;
import lk.epic.restfulAPI.service.SignUpService;
import lk.epic.restfulAPI.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseUtil signUp(SignUpDTO signUpDTO) {
        if (!userRepo.existsById(signUpDTO.getEmail())) {
            User userIsSaved = userRepo.save(mapper.map(signUpDTO, User.class));
            if (userIsSaved != null) {
                return new ResponseUtil("00", "Success", null);
            } else {
                return new ResponseUtil("06", "Bad Request", null);
            }
        } else {
            return new ResponseUtil("04", "User Already Exists", null);
        }
    }
}
