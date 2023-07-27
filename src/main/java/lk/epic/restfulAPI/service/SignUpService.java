package lk.epic.restfulAPI.service;

import lk.epic.restfulAPI.dto.SignUpDTO;
import lk.epic.restfulAPI.util.ResponseUtil;

public interface SignUpService {
    ResponseUtil signUp(SignUpDTO signUpDTO);
}
