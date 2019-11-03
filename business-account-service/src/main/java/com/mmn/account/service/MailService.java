package com.mmn.account.service;

import com.mmn.account.config.AsyncTaskConfig;
import com.mmn.account.controller.AccountController;
import com.mmn.account.dto.MailDto;
import com.mmn.account.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(final MailDto mail) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setText(mail.getText());
        message.setTo(mail.getEmail());
        javaMailSender.send(message);
    }

    @Async(AsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendConfirmationEmail(Account account, ServletUriComponentsBuilder servletUriComponentsBuilder) {
        log.info("Sending confirmation email...");
        sendMail(MailDto.builder()
                .email(account.getEmail())
                .text(servletUriComponentsBuilder
                        .path(AccountController.MAIL_CONFIRM_URI)
                        .queryParam("id", account.getId())
                        .build()
                        .toUri().toString())
                .build());
    }

    @Async(AsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendRecoveryEmail(Account account, ServletUriComponentsBuilder servletUriComponentsBuilder) {
        log.info("Sending recovery email...");
        sendMail(MailDto.builder()
                .email(account.getEmail())
                .text(servletUriComponentsBuilder
                        .path(AccountController.MAIL_RECOVER_URI)
                        .queryParam("id", account.getId())
                        .queryParam("token", account.getResetToken())
                        .build()
                        .toUri().toString())
                .build());
    }
}
