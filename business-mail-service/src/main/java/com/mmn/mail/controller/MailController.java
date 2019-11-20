package com.mmn.mail.controller;

import com.mmn.mail.dto.Email;
import com.mmn.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@CrossOrigin("*")
@RequestMapping("/mail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailController {
    private final MailService service;

    @PostMapping("/send")
    public void sendEmail(@RequestBody Email email) throws MessagingException {
        if (this.service.validate(email)) {
            this.service.sendHtmlMessage(email);
        }
    }
}
