package com.linkjb.servicebase.mqTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
public class SendController {
    @Autowired
    private FirstSender firstSender;

    @GetMapping("/send")
    public String send(String message) throws IOException {
        String uuid = UUID.randomUUID().toString();
        firstSender.send(uuid,message);
        return uuid;
    }

}
