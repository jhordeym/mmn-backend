package com.mmn.account.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super();
        log.error(message);
    }
}
