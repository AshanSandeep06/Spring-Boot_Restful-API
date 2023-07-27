package lk.epic.restfulAPI.service.impl;

import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.service.SignUpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

    @Override
    public void signUp(SignUpDTO signUpDTO) {

    }
}
