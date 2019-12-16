package com.mmn.account.controller;

import com.mmn.account.model.dto.*;
import com.mmn.account.model.entity.Account;
import com.mmn.account.model.type.AccountStatus;
import com.mmn.account.model.type.RoleEnum;
import com.mmn.account.service.AccountService;
import com.mmn.account.service.LevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final LevelService levelService;

    /**
     * Account login
     *
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public Account login(@RequestBody final LoginDto loginDto) {
        return accountService.login(loginDto).orElse(null);
    }

    /**
     * Create influencers account (called by Admin)
     *
     * @param account
     * @return
     */
    @PostMapping("/influencers")
    public Account saveInfluencer(@RequestBody final AccountLinkDto account) {
        account.getAccount().setRole(RoleEnum.INFLUENCER);
        return saveAccount(account);
    }

    /**
     * Normal save account
     *
     * @param account
     * @return
     */
    @PostMapping
    public Account saveAccount(@RequestBody final AccountLinkDto account) {
        if (Objects.isNull(account.getAccount()))
            return null;
        final Account savedAccount = accountService.save(account);
        log.debug("Account saved successfully");
        return savedAccount;
    }

    /**
     * Update account
     *
     * @param account
     * @return
     */
    @PutMapping
    public Account updateAccount(@RequestBody final Account account) {
        final Account body = accountService.update(account);
        log.debug("Account updated successfully");
        return body;
    }

    /**
     * Send email to user to get recover Token
     *
     * @param changePassDto
     * @return
     */
    @PostMapping("/pass/forgot")
    public String forgot(@RequestBody final ChangePassDto changePassDto) {
        if (accountService.forgot(changePassDto)) {
            return AccountStatus.WaitingPasswordRecovery.toString();
        }
        return null;
    }

    /**
     * Update account password
     *
     * @param account
     */
    @PutMapping("/pass/update")
    public void updatePassword(@RequestBody final Account account) {
        final int body = accountService.updatePassword(account);
        log.debug("Account password updated successfully");
    }


    //
    // send to to invite new commers
    @PostMapping("/invite/send")
    public void invite(@RequestBody final InviteDto invite) {
        accountService.invite(invite);
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

    // verificar invite
    // retorna o id do account parent, pai do invite ou null, se nao existir
    @PostMapping("/invite/verify-token")
    public String verifyInviteToken(@RequestBody final String inviteToken) {
        return accountService.findByInviteToken(inviteToken);
    }

    @PutMapping("/level/active")
    public void updateLevelActive(@RequestBody final String accountId) {
        this.levelService.validateReferralCode(accountId);
    }

}
