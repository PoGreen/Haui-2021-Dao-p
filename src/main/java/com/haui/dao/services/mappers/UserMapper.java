package com.haui.dao.services.mappers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.haui.dao.models.bos.SystemResponse;
import com.haui.dao.models.entities.User;
import com.haui.dao.models.requests.UserRequest;
import com.haui.dao.models.responses.UserLoginResponse;
import com.haui.dao.repositories.UserRepository;
import com.haui.dao.services.impls.FirebaseService;
import com.haui.dao.utils.Global;
import com.haui.dao.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class UserMapper {

    private static final Logger logger = LogManager.getLogger(UserMapper.class);
    private final FirebaseService firebaseService;
    private final UserRepository userRepository;

    public UserMapper(FirebaseService firebaseService, UserRepository userRepository) {
        this.firebaseService = firebaseService;
        this.userRepository = userRepository;
    }

    public User map(UserRequest request) {
        User userExist = null;
        userExist.setPhone(request.getPhone());
        return userExist;
    }

    public UserLoginResponse map(HttpServletRequest request, UserRequest userRequest) {
        ResponseEntity<SystemResponse<Object>> tokenResponse = firebaseService.genTokenFromFirebase(request);
        if (!tokenResponse.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        String accessToken = Global.objectMapper.convertValue(Objects.requireNonNull(tokenResponse.getBody()).getData(), String.class);
        FirebaseToken auth;
        try {
            auth = FirebaseAuth.getInstance().verifySessionCookie(accessToken);
        } catch (FirebaseAuthException e) {
            logger.error(e);
            return null;
        }
//        User userExist = userRepository.findByUserId(auth.getUid());
        User userExist = null;
        return new UserLoginResponse(userExist, accessToken);
    }

}
