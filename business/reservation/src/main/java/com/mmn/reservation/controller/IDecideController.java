package com.mmn.reservation.controller;

import com.mmn.reservation.model.IDecideAccountCreateDto;
import com.mmn.reservation.model.entity.IDecideAccount;
import com.mmn.reservation.service.IDecideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/idecide")
public class IDecideController {
    private final IDecideService service;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody final IDecideAccountCreateDto iDecideAccountDto)
            throws IOException, URISyntaxException {
        return ResponseEntity.ok(this.service.create(iDecideAccountDto));
    }

    @PostMapping("/idecide-account")
    public IDecideAccount saveIDecide(@RequestBody final IDecideAccount iDecideAccount) {
        return this.service.save(iDecideAccount);
    }
}
