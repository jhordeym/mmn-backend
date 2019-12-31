package com.mmn.reservation.controller;

import com.mmn.reservation.config.SorProperties;
import com.mmn.reservation.model.AccountDto;
import com.mmn.reservation.model.FullLoginDto;
import com.mmn.reservation.model.LoginDto;
import com.mmn.reservation.model.LoginResponseDto;
import com.mmn.reservation.service.SorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/sor")
public class SorController {
    private static final int TEN_MINUTES = 600000;
    private final SorProperties config;
    private final SorService service;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestHeader("subscriptionId") final String subscriptionId,
                                    @RequestBody final AccountDto accountDto) throws IOException {
        final SorProperties.Pack pack = getPackBySubscriptionId(subscriptionId);
        if (pack == null) return ResponseEntity.badRequest().body("Invalid ID");
        return ResponseEntity.ok(clientV2.create(pack.getUsername(), pack.getPassword(), accountDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestHeader("subscriptionId") final String subscriptionId,
                                   @RequestBody final LoginDto loginDto) throws IOException {
        log.info(loginDto.toString());
        final SorProperties.Pack pack = getPackBySubscriptionId(subscriptionId);
        if (pack == null) return ResponseEntity.badRequest().body("Invalid ID");
        final FullLoginDto fullLoginDto = FullLoginDto.builder()
                .APIUsername(pack.getUsername())
                .APIPassword(pack.getPassword())
                .Email(loginDto.getEmail())
                .ContractNumber(loginDto.getContractNumber())
                .build();
        log.info(fullLoginDto.toString());
        final String login = this.client.login(fullLoginDto);
        final String[] split = login.replaceAll("\"", "").split(":");
        if (split.length == 2) {
            return ResponseEntity.ok(
                    LoginResponseDto.builder()
                            .token(split[1])
                            .generateTimeMilis(System.currentTimeMillis())
                            .expireTimeMilis(System.currentTimeMillis() + TEN_MINUTES)
                            .build());
        } else if (login.replaceAll("\"", "").equals("Member not found.")) {
            throw new RuntimeException("Member not found.");
        }
        return ResponseEntity.ok(login);
    }

    private SorProperties.Pack getPackBySubscriptionId(final String subscriptionId) {
        try {
            int id = Integer.parseInt(subscriptionId);
            final List<SorProperties.Pack> packs = config.getPacks();
            if (!(id >= 0 && id < packs.size())) {
                return null;
            }
            return packs.get(id);
        } catch (Exception e) {
            return null;
        }
    }
}
