package com.mmn.mail.service;

import com.mmn.mail.config.AsyncTaskConfig;
import com.mmn.mail.dto.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailService {
    private final JavaMailSender emailSender;

    @Async(AsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendHtmlMessage(final Email email)
            throws MessagingException {
        final MimeMessage msg = emailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(msg, false);
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getText(), true);
        log.info("-> Email sent = {}", email);
        emailSender.send(msg);
    }

    public boolean validate(final Email email) {
        return Objects.nonNull(email.getTo())
                && Objects.nonNull(email.getSubject())
                && Objects.nonNull(email.getText());
    }
}
