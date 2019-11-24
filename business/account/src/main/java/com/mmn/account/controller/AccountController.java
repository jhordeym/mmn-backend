package com.mmn.account.controller;

import com.mmn.account.dto.InviteDto;
import com.mmn.account.dto.LoginDto;
import com.mmn.account.dto.PassRecoveryDto;
import com.mmn.account.model.Account;
import com.mmn.account.model.Level;
import com.mmn.account.service.AccountService;
import com.mmn.account.type.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/accounts")
@Slf4j
public class AccountController {

    public static final String MAIL_CONFIRM_URI = "/confirm";
    public static final String MAIL_RECOVER_URI = "/recover";
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody final Account account,
                                        // optional (to skip to spam)
                                        @RequestParam(value = "useMail", defaultValue = "true", required = false) final String enableMail) {

        final Account body = accountService.save(account, ServletUriComponentsBuilder.fromCurrentRequest(), enableMail);
        log.debug("Account saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PostMapping("/change-pass")
    public ResponseEntity<Account> changePassword(@RequestBody final Account account) {
        // TODO: create service to changepassword
        return null;
    }

    @PostMapping("/forgot")
    public ResponseEntity<String> forgot(@RequestBody final String login) {
        if (accountService.forgot(login, ServletUriComponentsBuilder.fromCurrentRequest())) {
            return ResponseEntity.status(HttpStatus.OK).body(AccountStatus.WaitingPasswordRecovery.toString());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT.toString());
    }

    // Already got the mail
    @PostMapping("/referal")
    public Level validateReferralCode(@RequestBody InviteDto inviteDto) {
        try {
            return accountService.validateReferralCode(inviteDto);
        } catch (Exception e) {
            return null;
        }
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
    public ResponseEntity<?> login(@RequestBody final LoginDto loginDto) {
        final Optional<Account> login = accountService.login(loginDto);
        if (login.isPresent()) {
            log.debug("Login successful");
            return ResponseEntity.status(HttpStatus.OK).body(login.get());
        }
        log.debug("Login failed");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.toString());
    }

    @GetMapping(MAIL_CONFIRM_URI)
    public ResponseEntity<String> confirmAccount(@RequestParam("id") final String id) {
        if (accountService.confirmAccount(id)) {
            log.debug("Account confirmed");
            return ResponseEntity.status(HttpStatus.OK).body(AccountStatus.Authenticated.toString());
        }
        log.debug("Account recovered");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.toString());
    }

    @GetMapping("/forgot/" + MAIL_RECOVER_URI)
    public ResponseEntity<String> recoverAccount(@RequestParam("id") final String id,
                                                 @RequestParam("token") final String token) {
        if (accountService.recoverAccount(PassRecoveryDto.builder().id(id).token(token).build())) {
            log.debug("Account recovered");
            return ResponseEntity.status(HttpStatus.OK).body(AccountStatus.Recovered.toString());
        }
        log.debug("Failed recovering");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(HttpStatus.EXPECTATION_FAILED.toString());
    }


}
