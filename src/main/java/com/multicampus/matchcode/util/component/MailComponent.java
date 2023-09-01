package com.multicampus.matchcode.util.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class MailComponent extends Thread {


    private final JavaMailSender javaMailSender;
    private final Executor executor;

    public MailComponent(JavaMailSender javaMailSender, Executor executor) {
        this.javaMailSender = javaMailSender;
        this.executor = executor;
    }

    protected void sendMailThread(String... params) {
        Runnable runnable = () -> {
            log.info("Thread Name : {}", Thread.currentThread()
                                               .getName());
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            if (params == null) {
                return;
            }
            try {
                simpleMailMessage.setTo(params[0]);
                simpleMailMessage.setSubject(params[1]);
                simpleMailMessage.setText(params[2]);
                javaMailSender.send(simpleMailMessage);
            } catch (MailException ex) {
                log.error("Mail Error ! {}", ex.getMessage());
            }
            log.info("Thread Name : {} Done!", Thread.currentThread()
                                                     .getName());
        };
        executor.execute(runnable);
    }

    public String sendVerifyingMail(String mailAddress) {
        if (mailAddress == null) {
            return null;
        }
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
        String subject = "<Match Code 인증 메일 입니다>";

        sendMailThread(mailAddress, subject, sb.toString());
        return verifyCode;
    }

    public void sendTempPassword(String mailAddress, String tempPassword) {

        String subject = "<Match Code 임시 비밀번호 입니다.>";
        StringBuilder sb= new StringBuilder();
        sb.append("<임시 비밀번호는 [");
        sb.append(tempPassword);
        sb.append("] 입니다");
        sendMailThread(mailAddress, subject, sb.toString());
    }
}
