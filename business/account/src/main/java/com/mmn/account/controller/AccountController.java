package com.mmn.account.controller;

import com.mmn.account.model.dto.*;
import com.mmn.account.model.entity.Account;
import com.mmn.account.model.type.AccountStatus;
import com.mmn.account.service.AccountService;
import com.mmn.account.service.LevelService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/accounts")
@Slf4j
@RequiredArgsConstructor
public class AccountController {
    
	private final AccountService accountService;
    private final LevelService levelService;
    
    @PostMapping
    public Account save(@RequestBody final AccountLinkDto account) {
        if (Objects.isNull(account.getAccount()))
            return null;
        final Account body = accountService.save(account);
        log.debug("Account saved successfully");
        return body;
    }

    @PutMapping
    public Account update(@RequestBody final Account account) {
        final Account body = accountService.update(account);
        log.debug("Account saved successfully");
        return body;
    }

    @PatchMapping
    public void updatePassword(@RequestBody final Account account) {
        final int body = accountService.updatePassword(account);
        log.debug("Account password updated successfully");
    }

    @PostMapping("/change-pass")
    public int changePassword(@RequestBody final ChangePassDto changePassDto) {
        return accountService.changePassword(changePassDto);
    }

    @PostMapping("/forgot")
    public String forgot(@RequestBody final ChangePassDto changePassDto) {
        if (accountService.forgot(changePassDto)) {
            return AccountStatus.WaitingPasswordRecovery.toString();
        }
        return null;
    }

    // send to to invite new commers
    @PostMapping("/invite")
    public void invite(@RequestBody InviteDto invite) {
    	accountService.invite(invite);
    }

    @PostMapping("/login")
    public Account login(@RequestBody final LoginDto loginDto) {
        final Optional<Account> login = accountService.login(loginDto);
        if (login.isPresent()) {
            log.debug("Login successful");
            return login.get();
        }
        log.debug("Login failed");
        return null;
    }

    // Confirm account by email;
    @GetMapping("/mail/confirm")
    public String confirmAccount(@RequestParam("id") final String id) {
        if (accountService.confirmAccount(id)) {
            log.debug("Account confirmed");
            return AccountStatus.Authenticated.name();
        }
        log.debug("Account recovered");
        return null;
    }

    // Confirm recover account by email;
    @PatchMapping("/mail/recover")
    public String recoverAccount(@RequestParam("id") final String id,
                                 @RequestParam("token") final String token) {
        if (accountService.recoverAccount(PassRecoveryDto.builder().id(id).token(token).build())) {
            log.debug("Account recovered");
            return AccountStatus.Recovered.toString();
        }
        log.debug("Failed recovering");
        return null;
    }

    //verificar invite
    //retorna o id do account parent, pai do invite ou null, se nao existir
    @GetMapping("/invite-token")
    public String verificarInviteToken(String inviteToken) {
    	return accountService.findByInviteToken(inviteToken);
    }

    @PutMapping("/level/{id}")
    public void atualizaLevelActive(@PathVariable("id") String accountId) {
    	this.levelService.validateReferralCode(accountId);
    }
    
}
