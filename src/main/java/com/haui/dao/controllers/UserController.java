package com.haui.dao.controllers;
import com.haui.dao.models.bos.SystemResponse;
import com.haui.dao.models.requests.Login;
import com.haui.dao.services.IUserService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "USER")
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<SystemResponse<Object>> login(HttpServletRequest request, @RequestBody Login login) {
        return service.login(request,login);
    }

}
