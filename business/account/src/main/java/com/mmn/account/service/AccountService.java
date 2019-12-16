package com.mmn.account.service;

import com.mmn.account.model.dto.*;
import com.mmn.account.model.entity.Account;
import com.mmn.account.model.entity.Level;
import com.mmn.account.repository.AccountRepository;
import com.mmn.account.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountEmailService accountEmailService;
    private final PasswordHandler passwordHandler;
    private final LevelRepository levelRepository;

    public Account save(final AccountLinkDto accountLinkDto) {
        final Account account = accountLinkDto.getAccount();
        final Optional<Account> existingAccount = this.accountRepository.findByEmail(account.getEmail());
        if (existingAccount.isPresent()) {
            log.info("Account already exists -> Update...");
            return _update(account, existingAccount.get());
        }
        log.info("New account -> Encrypting password and saving...");
        // encryptPass
        account.setPassword(
                passwordHandler.encode(account.getPassword())
        );
        final Account savedAccount = accountRepository.save(
                account.newTokens()
        );
        //controle de nível
        createLevel(account, accountLinkDto.getParentId());
        // sendEmail
        if (Objects.nonNull(accountLinkDto.getLink())) {
            accountEmailService.sendConfirmationEmail(savedAccount, accountLinkDto.getLink());
        }
        return savedAccount.hidePassAndToken();
    }

    //criar o controle de níveis
    private void createLevel(final Account account, final String parentId) {
        //exceto pra admin
        if (account.isAdmin() || parentId == null) {
            return;
        }
        levelRepository.save(
                Level.builder()
                        .child(account)
                        .parent(accountRepository.findById(parentId).get())
                        .build()
        ).getId();
    }

    private Account _update(final Account account, final Account existing) {
        account.setId(existing.getId());
        return update(account);
    }

    public Account update(final Account account) {
        return accountRepository.save(account).newTokens().hidePassAndToken();
    }

    public boolean exists(final Account account) {
        return this.accountRepository.existsByEmailOrPhone(account.getEmail(), account.getPassword());
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

    public boolean forgot(final ChangePassDto changePassDto) {
        final Optional<Account> existingAccount = this.accountRepository.findByEmailOrPhone(changePassDto.getEmail(), changePassDto.getEmail());
        if (existingAccount.isPresent()) {
            accountEmailService.sendRecoveryEmail(existingAccount.get(), changePassDto.getLink());
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
            existingAccount.get().setPassword(this.passwordHandler.encode(passRecoveryDto.getNewPassword()));
            this.accountRepository.save(existingAccount.get().updateToken());
            return true;
        }
        return false;
    }

    public void invite(final InviteDto invite) {
        accountEmailService.sendInviteEmail(invite);
    }

    @Transactional
    public int updatePassword(final Account account) {
        return accountRepository.updatePassword(
                passwordHandler.encode(
                        account.getPassword()
                ),
                account.getId()
        );
    }

    public String findByInviteToken(String inviteToken) {
        Optional<Account> optional = accountRepository.findByInviteToken(inviteToken);
        return optional.isPresent() ? optional.get().getId() : null;
    }

}
