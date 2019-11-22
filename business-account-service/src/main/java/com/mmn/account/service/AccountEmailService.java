package com.mmn.account.service;

import com.mmn.account.client.EmailClient;
import com.mmn.account.config.AccountAsyncTaskConfig;
import com.mmn.account.controller.AccountController;
import com.mmn.account.dto.InviteDto;
import com.mmn.account.exceptions.EmailException;
import com.mmn.account.model.Account;
import com.mmn.account.model.Level;
import com.mmn.mail.api.dto.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountEmailService {
    private static final String CONFIRMATION_SUB = "Travined - Welcome! just need to confirm your Account! :)";
    private static final String RECOVERY_SUB = "Travined - Forgot your password? don't worry! :)";
    private static final String INVITE_SUB = "Travined - You received an invitation to join us! :)";

    // Use real service or feign client
    //@Autowired
    //private EmailServiceImpl emailService;
    @Autowired
    private EmailClient emailService;

    @Async(AccountAsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendConfirmationEmail(Account account, ServletUriComponentsBuilder servletUriComponentsBuilder) {
        log.info("Sending confirmation to...");
        try {
            emailService.sendEmail(
                    Email.builder()
                            .to(account.getEmail())
                            .subject(CONFIRMATION_SUB)
                            .text(servletUriComponentsBuilder
                                    .path(AccountController.MAIL_CONFIRM_URI)
                                    .queryParam("id", account.getId())
                                    .build()
                                    .toUri().toString())
                            .build());
        } catch (final MessagingException e) {
            throw new EmailException(e.getStackTrace().toString());
        }
    }

    @Async(AccountAsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendRecoveryEmail(Account account, ServletUriComponentsBuilder servletUriComponentsBuilder) {
        log.info("Sending recovery to...");
        try {
            emailService.sendEmail(
                    Email.builder()
                            .to(account.getEmail())
                            .subject(RECOVERY_SUB)
                            .text(servletUriComponentsBuilder
                                    .path(AccountController.MAIL_RECOVER_URI)
                                    .queryParam("id", account.getId())
                                    .queryParam("token", account.getResetToken())
                                    .build()
                                    .toUri().toString())
                            .build());
        } catch (final MessagingException e) {
            throw new EmailException(e.getStackTrace().toString());
        }
    }

    @Async(AccountAsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendInviteEmail(InviteDto invite, Level level) {
        log.info("Sending invite to...");
        try {
            emailService.sendEmail(
                    Email.builder()
                            .to(invite.getEmailInvited())
                            .subject(INVITE_SUB)
                            .text(invite.getLink() + "/" + level.getId().toString())
                            .build()
            );
        } catch (final MessagingException e) {
            throw new EmailException(e.getStackTrace().toString());
        }
    }
}
