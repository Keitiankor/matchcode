package com.multicampus.matchcode.util.component;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendVerifyingMail(String mailAddress) {
        if (mailAddress == null) {
            return null;
        }
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(r.nextInt(10));
        }
        String verifyCode = sb.toString();
        sb.setLength(0);
        sb.append("<Match Code 인증 번호는 \"");
        sb.append(verifyCode);
        sb.append("\" 입니다.>");

        try {
            simpleMailMessage.setTo(mailAddress);
            simpleMailMessage.setSubject("<Match Code 인증 메일입니다>");
            simpleMailMessage.setText(sb.toString());
            javaMailSender.send(simpleMailMessage);
            return verifyCode;
        } catch (MailException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
