package com.mmn.account.controller;

import com.mmn.account.model.dto.*;
import com.mmn.account.model.entity.Account;
import com.mmn.account.model.entity.Level;
import com.mmn.account.model.type.AccountStatus;
import com.mmn.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account save(@RequestBody final AccountLinkDto account) {
        if (Objects.isNull(account.getAccount()))
            return null;
        final Account body = accountService.save(account);
        log.debug("Account saved successfully");
        return body;
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
    public Level invite(@RequestBody InviteDto invite) {
        try {
            return accountService.invite(invite);
        } catch (Exception e) {
            return null;
        }
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

    // Validate referral code by email;
    @PostMapping("/mail/referral")
    public Level validateReferralCode(@RequestBody InviteDto inviteDto) {
        try {
            return accountService.validateReferralCode(inviteDto);
        } catch (Exception e) {
            return null;
        }
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


}
