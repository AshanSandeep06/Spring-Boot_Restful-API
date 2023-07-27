package lk.epic.restfulAPI.service;

import lk.epic.restfulAPI.dto.LoginDTO;
import lk.epic.restfulAPI.util.ResponseUtil;

public interface LoginService {
    ResponseUtil login(LoginDTO loginDTO);
}
