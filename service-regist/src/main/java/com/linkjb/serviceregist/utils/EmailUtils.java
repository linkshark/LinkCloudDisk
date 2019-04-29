package com.linkjb.serviceregist.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @description 邮件发送工具类
 * @data 2019/4/29 10:08
 */
@Component
public class EmailUtils {
    Logger log = LoggerFactory.getLogger(EmailUtils.class);
    @Autowired
    JavaMailSenderImpl mailSender;

    public void sendSimpleEmail(String subject,String simpleText,String sendTo){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendTo);
        message.setSubject(subject);
        message.setText(simpleText);
        message.setFrom("sharkshen@outlook.com");
        mailSender.send(message);

    }

}
