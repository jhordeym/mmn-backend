package com.mmn.account.client;

import com.mmn.account.dto.MailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="emailClient", url="http://localhost:8184")
public interface EmailClient {
    @PostMapping("/mail/send")
    void sendEmail(@RequestBody MailDto email);
}
