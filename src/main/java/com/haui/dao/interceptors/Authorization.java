package com.haui.dao.interceptors;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.haui.dao.models.bos.UserJwt;
import com.haui.dao.models.entities.Api;
import com.haui.dao.models.entities.RoleApi;
import com.haui.dao.repositories.ApiRepository;
import com.haui.dao.repositories.RoleApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class Authorization {

    @Autowired
    private RoleApiRepository roleApiRepository;

    @Autowired
    private ApiRepository apiRepository;

    public boolean verifyRole(DecodedJWT decodedJWT, HttpServletRequest request) {
        String uri = request.getRequestURI();
        UserJwt userJwt = UserJwt.from(decodedJWT);

        RoleApi roleApi = roleApiRepository.existsByRoleAndApiUrl(uri,userJwt.getRole());

        if(Objects.isNull(roleApi)) return false;
        return true;
    }
}
