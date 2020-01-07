package com.mmn.reservation.service;

import com.mmn.reservation.model.entity.PassportUser;
import com.mmn.reservation.repository.PassportUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassportUserService {
    private final PassportUserRepository passportUserRepository;

    public PassportUser save(final PassportUser passportUser) {
        return this.passportUserRepository.save(passportUser);
    }
}
