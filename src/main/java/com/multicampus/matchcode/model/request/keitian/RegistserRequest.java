package com.multicampus.matchcode.model.request.keitian;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.servlet.tags.form.CheckboxTag;

import java.awt.*;

@Data
@Builder
public class RegistserRequest {

    String account;
    String password;
    String name;
    String phone;
    String mailAddress;
    String birthday;
    boolean isAccountNotDup;
    boolean isVerifyied;
}
