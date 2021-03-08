package com.haui.dao.services;



import com.haui.dao.models.bos.SystemResponse;
import com.haui.dao.models.requests.Login;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IUserService {


    ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, Login login);
    boolean verifyRole(String roleId, String api);
}
