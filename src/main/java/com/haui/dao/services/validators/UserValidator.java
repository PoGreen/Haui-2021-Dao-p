package com.haui.dao.services.validators;

import com.haui.dao.models.requests.UserRequest;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public boolean validate(UserRequest request) {
        return !StringUtil.isNullOrEmpty(request.getPhone()) && !StringUtil.isNullOrEmpty(request.getOtp());
    }
}
