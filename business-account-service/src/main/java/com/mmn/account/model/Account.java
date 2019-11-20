package com.mmn.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /* Login by either to or phone,
        but to is mandatory! */
    private String email;
    private String phone;

    private String password;
    private String resetToken;

    // Additional fields
    private String name;
    private String lastName;
    @Embedded
    private Address address;
    private Date birthDate;

    @OneToMany
    private List<Role> roles;
    private AccountStatus accountStatus = AccountStatus.New;
    private LocalDate creationDate = LocalDate.now();
    private LocalDate updatedDate;

    public Account confirmed() {
        setAccountStatus(AccountStatus.Authenticated);
        setUpdatedDate(LocalDate.now());
        return this;
    }


    public Account newToken() {
        if (Objects.isNull(getResetToken()))
            return this.updateToken();
        return this;
    }

    public Account updateToken() {
        setResetToken(
                Base64.getEncoder().encodeToString(
                        UUID.randomUUID().toString().getBytes()
                )
        );
        return this;
    }


    public Account hidePassAndToken() {
        setPassword(null);
        setResetToken(null);
        return this;
    }

}
