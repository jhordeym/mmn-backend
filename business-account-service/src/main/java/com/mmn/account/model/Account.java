package com.mmn.account.model;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.AccountStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    /* Login by either email or phone,
        but email is mandatory! */
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
