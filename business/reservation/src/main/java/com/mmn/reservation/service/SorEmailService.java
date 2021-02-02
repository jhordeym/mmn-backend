package com.mmn.reservation.service;

import com.google.common.base.Throwables;
import com.mmn.mail.api.dto.Email;
import com.mmn.mail.impl.service.MailServiceImpl;
import com.mmn.reservation.config.SorAsyncTaskConfig;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SorEmailService {
    private static final String INVITATION_SUB = "Travined - Become an affiliate today!";
    private static final String INVITATION_BODY = "Hello $PASSPORT_USER! </br>" +
            "<p>If you already used your passport, you can become an affiliate to keep saving while traveling! <p></br>" +
            "<p>You can create your club account by clicking the link bellow: </p></br>" +
            "<p>https://login.travined.com:8080/#/signup?inviteToken=$TOKEN </p>";

    private static final String TEMP_SUB = "Acesse a plataforma de viagens aqui!";
    private static final String TEMP_BODY = "<p>Bem vindo a TRAVINED!</p></br>" +
            "</br>" +
            "<p>Abaixo segue a credencial temporária para que você possa ter acesso imediato a nossa plataforma de viagens.</p></br>" +
            "</br>" +
            "<p>Em até 2 dias úteis você receberá outro e-mail com a confirmação do login que você criou através do passaporte.</p></br>" +
            "</br>" +
            "<p>Então vamos navegar na plataforma?</p></br>" +
            "</br>" +
            "<p>Para se conectar hoje, acesse www.travined.com e use o login temporário abaixo:</p></br>" +
            "</br>" +
            "<p>Login: demo@mytrip.com</p></br>" +
            "<p>Senha: travined123</p></br>" +
            "</br>" +
            "<p>Após o login, vá na coluna esquerda e clique em MYTRIP 360. </p></br>" +
            "</br>" +
            "<p>Escolha o serviço que quer utilizar e boa viagem!</p></br>" +
            "</br>" +
            "<p>Equipe Travined.</p>";


    private final MailServiceImpl emailService;

    @Async(SorAsyncTaskConfig.THREAD_POOL_TASK_EXECUTOR)
    public void sendInvitationEmail(
            @NonNull final String referUserToken,
            @NonNull final String passportUserEmail,
            @NonNull final String passportUserName
    ) {
        log.info("Sending sor email to...");
        try {
            emailService.sendEmail(
                    Email.builder()
                            .to(passportUserEmail)
                            .subject(INVITATION_SUB)
                            .text(INVITATION_BODY
                                    .replace("$PASSPORT_USER", passportUserName)
                                    .replace("$TOKEN", referUserToken)
                            )
                            .build());
        } catch (final MessagingException e) {
            throw new RuntimeException(Throwables.getStackTraceAsString(e));
        }
    }

    public void sendTemporaryEmail(@NonNull final String email) {
        log.info("Sending SOR TEMPORARY email to...");
        try {
            emailService.sendEmail(
                    Email.builder()
                            .to(email)
                            .subject(TEMP_SUB)
                            .text(TEMP_BODY)
                            .build());
        } catch (final MessagingException e) {
            throw new RuntimeException(Throwables.getStackTraceAsString(e));
        }
    }
}
