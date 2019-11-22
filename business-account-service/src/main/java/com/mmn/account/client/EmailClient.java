package com.mmn.account.client;

import com.mmn.mail.api.service.EmailService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("${MMN_BUSINESS_MAIL_NAME:business-mail-service}")
public interface EmailClient extends EmailService {

}
