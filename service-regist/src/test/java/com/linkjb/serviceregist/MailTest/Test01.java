package com.linkjb.serviceregist.MailTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 {
    Logger log = LoggerFactory.getLogger(Test.class);

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void Sender(){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("马化腾真帅");
            message.setText("真的真的真的帅");

            message.setTo("sharkshen1996@gmail.com");
            message.setFrom("sharkshen@outlook.com");

            mailSender.send(message);
            log.info("eeeeeeeeeeeeee");
        }catch (Exception e){
            log.error(e.toString());
        }
    }
}
