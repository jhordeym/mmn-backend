package com.mmn.account.service;

import com.mmn.account.dto.LoginDto;
import com.mmn.account.dto.PassRecoveryDto;
import com.mmn.account.model.Account;
import com.mmn.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordHandler passwordHandler;

    public Account save(final Account account,
                        final ServletUriComponentsBuilder servletUriComponentsBuilder,
                        final String enableEmail) {
        final Optional<Account> existingAccount = this.accountRepository.findByEmail(account.getEmail());
        if (existingAccount.isPresent()) {
            log.info("Account already exists -> Update...");
            final Account existingAcc = existingAccount.get();
            account.setId(existingAcc.getId());
            handleImportantFields(account, existingAcc);
        } else {
            log.info("New account -> Encrypting password and saving...");
            account.setPassword(passwordHandler.encode(account.getPassword()));
        }
        final Account savedAccount = accountRepository.save(account.newToken());
        if (enableEmail.equals("true")) {
            this.mailService.sendConfirmationEmail(savedAccount, servletUriComponentsBuilder);
        }
        return savedAccount.hidePassAndToken();
    }

    private void handleImportantFields(final Account newAcc, final Account existingAcc) {
        // Mandatory Non Null Fields
        if (Objects.isNull(newAcc.getPhone())) {
            newAcc.setPhone(existingAcc.getPhone());
        }
        if (Objects.isNull(newAcc.getResetToken()) && Objects.nonNull(existingAcc.getResetToken())) {
            newAcc.setResetToken(existingAcc.getResetToken());
        }
        if (this.passwordHandler.match(newAcc.getPassword(), existingAcc.getPassword())) {
            log.info("... -> Keeping the same password...");
            newAcc.setPassword(existingAcc.getPassword());
        } else {
            log.info("... -> Updating password as well...");
            newAcc.setPassword(passwordHandler.encode(newAcc.getPassword()));
        }
    }



    public Optional<Account> login(final LoginDto loginDto) {
        final Optional<Account> existingAccount = this.accountRepository.findByEmailOrPhone(loginDto.getLogin(), loginDto.getLogin());
        if (existingAccount.isPresent()) {
            final Account acc = existingAccount.get();
            if (this.passwordHandler.match(loginDto.getPassword(), acc.getPassword()))
                return Optional.of(acc.hidePassAndToken());
        }
        return Optional.empty();
    }

    public boolean forgot(final String login,
                          final ServletUriComponentsBuilder servletUriComponentsBuilder) {
        final Optional<Account> existingAccount = this.accountRepository.findByEmailOrPhone(login, login);
        if (existingAccount.isPresent()) {
            this.mailService.sendRecoveryEmail(existingAccount.get(), servletUriComponentsBuilder);
            return true;
        }
        return false;
    }


    public boolean confirmAccount(final String id) {
        final Optional<Account> existingAccount = this.accountRepository.findById(id);
        if (existingAccount.isPresent()) {
            accountRepository.save(existingAccount.get().confirmed());
            return true;
        }
        return false;
    }


    public boolean recoverAccount(final PassRecoveryDto passRecoveryDto) {
        final Optional<Account> existingAccount = this.accountRepository.findByIdAndResetToken(passRecoveryDto.getId(), passRecoveryDto.getToken());
        if (existingAccount.isPresent()) {
            this.accountRepository.save(existingAccount.get().updateToken());
            return true;
        }
        return false;
    }

}
