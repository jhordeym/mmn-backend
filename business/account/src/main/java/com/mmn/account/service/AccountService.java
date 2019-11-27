package com.mmn.account.service;

import com.mmn.account.exceptions.AlreadyActiveEmailInviteException;
import com.mmn.account.model.dto.*;
import com.mmn.account.model.entity.Account;
import com.mmn.account.model.entity.Level;
import com.mmn.account.model.type.LevelStatus;
import com.mmn.account.repository.AccountRepository;
import com.mmn.account.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
        account.setPassword(passwordHandler.encode(account.getPassword()));
        final Account savedAccount = accountRepository.save(account.newToken());
        // sendEmail
        if (Objects.nonNull(accountLinkDto.getLink())) {
            accountEmailService.sendConfirmationEmail(savedAccount, accountLinkDto.getLink());
        }
        return savedAccount.hidePassAndToken();
    }

    private Account _update(final Account account, final Account existing) {
        account.setId(existing.getId());
        return accountRepository.save(account).hidePassAndToken();
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

    public Level invite(final InviteDto invite) {
        final Optional<Level> optional = levelRepository.findByEmailInvitedAndStatus(invite.getEmailInvited(), LevelStatus.Active);
        if (optional.isPresent()) {
            throw new AlreadyActiveEmailInviteException();
        }
        //cria quantos niveis forem emitidos, enquanto não ativar um convite específico
        final Level level = Level.builder()
                .parent(invite.getAccount())
                .emailInvited(invite.getEmailInvited()).build();
        final Level savedLevel = levelRepository.save(level);
        accountEmailService.sendInviteEmail(invite, savedLevel);
        return savedLevel;
    }

    public Level validateReferralCode(final InviteDto inviteDto) {
        final Optional<Level> optional = levelRepository.findById(
                UUID.fromString(inviteDto.getId())
        );
        if (optional.isPresent() && optional.get().isActive()) {
            throw new AlreadyActiveEmailInviteException();
        }
        final Level level = optional.get();
        level.setActiveDate(LocalDate.now());
        level.setStatus(LevelStatus.Active);
        level.setScore(scoreValidateInvite());
        return levelRepository.save(level);
    }

    private Integer scoreValidateInvite() {
        // TODO Auto-generated method stub
        return 1;
    }

    public int changePassword(final ChangePassDto changePassDto) {
        final Optional<Account> existingAccount = this.accountRepository.findByEmail(changePassDto.getEmail());
        if (existingAccount.isPresent()) {
            return this.accountRepository.updatePassword(this.passwordHandler.encode(changePassDto.getNewPass()), existingAccount.get().getId());
        }
        return 0;
    }
    
    public Account update(final Account account) {
    	return accountRepository.save(account);
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
    
}
