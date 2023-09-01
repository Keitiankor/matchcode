package com.multicampus.matchcode.util.constants;

import org.springframework.beans.factory.annotation.Value;

public class StringConstant {
    @Value("${const.string.NO_ACCOUNT}")
    public static String NO_ACCOUNT = "없는 아이디 입니다.";
    @Value("${const.string.NO_MATCH_EMAIL}")
    public static String NO_MATCH_EMAIL = "이메일 주소가 일치하지 않습니다.";
}
