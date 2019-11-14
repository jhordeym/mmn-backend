package com.mmn.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mmn.account.type.AccountStatus;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    @Id
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
    private Address address;
    private Date birthDate;

    @DBRef
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
        setResetToken(Base64.encode(UUID.randomUUID().toString().getBytes()));
        return this;
    }

    public Account hidePassAndToken() {
        setPassword(null);
        setResetToken(null);
        return this;
    }

}
