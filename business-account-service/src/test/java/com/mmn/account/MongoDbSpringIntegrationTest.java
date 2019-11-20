package com.mmn.account;

/*
import com.mmn.account.config.MongoTestConfiguration;
import com.mmn.account.model.Account;
import com.mmn.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Import(MongoTestConfiguration.class)
@DataMongoTest(excludeAutoConfiguration= { EmbeddedMongoAutoConfiguration.class })
@ExtendWith(SpringExtension.class)
public class MongoDbSpringIntegrationTest {
    @Autowired
    private AccountRepository accountRepository;

    private final static List<String> USER_ID_LIST;
    private final static List<String> USER_EMAIL_LIST;
    private final static List<String> USER_PHONE_LIST;
    private static final Account EXPECTED;
    private static final Account NOT_EXPECTED;

    static {
        USER_ID_LIST = Arrays.asList("b2b1f340-cba2-11e8-ad5d-873445c542a2", "bd5dd3a4-cba2-11e8-9594-3356a2e7ef10");
        USER_EMAIL_LIST = Arrays.asList("EMAIL1@GMAIL.COM", "EMAIL2@GMAIL.COM");
        USER_PHONE_LIST = Arrays.asList("+35196969696", "+35199999999");
        EXPECTED = Account.builder()
                .id(USER_ID_LIST.get(1))
                .email(USER_EMAIL_LIST.get(1))
                .phone(USER_PHONE_LIST.get(1))
                .build();

        NOT_EXPECTED = Account.builder().id("1234").email("something@gmail").phone("+155555555").build();
    }

    @BeforeEach
    public void dataSetup() {
        for (int i = 0; i < 2; i++) {
            accountRepository.save(Account.builder()
                    .id(USER_ID_LIST.get(i))
                    .email(USER_EMAIL_LIST.get(i))
                    .phone(USER_PHONE_LIST.get(i))
                    .build());
        }
    }


    @Test
    public void findByEmail_success() {
        final Optional<Account> byEmail = accountRepository.findByEmail(USER_EMAIL_LIST.get(1));
        assertThat(byEmail).isPresent();
        assertThat(byEmail.get()).isEqualTo(EXPECTED);
    }

    @Test
    public void findByEmail_fail() {
        final Optional<Account> byEmail = accountRepository.findByEmail(USER_EMAIL_LIST.get(1));
        assertThat(byEmail).isPresent();
        assertThat(byEmail.get()).isNotEqualTo(NOT_EXPECTED);
    }
}
*/
