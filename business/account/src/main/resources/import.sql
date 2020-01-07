-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- ADMIN ACCOUNT
insert into account(
    id, email, phone, invite_token, reset_token, name, last_name, role, account_status, creation_date, password, payment_active
    ) values (
    'bbef6274-3a0a-4c7c-9747-324aefa267fc',
    'admin@travined.com',
    'phone_placeholder',
    'YzNiYjNiZTYtOTFmOS00YmI0LThlYjctNGU3M2YyYzBjMDZi',
    'OGMxZmRmMzktNTg3Ni00NGM1LTk5OGMtMTBjMWFjZmU5Njk2',
    'Travined',
    'Admin',
    'ADMIN',
    'Authenticated',
    '2019-12-16',
    '$2a$10$XWgghRZSzIkBzvvNYikoLupuoqRjz.TQEogCy5huxfdgvZ.os/3ui',
     true
    );

-- DEVELOPER ACCOUNT
insert into account(
    id, email, phone, password, name, last_name,
    birth_date, street, city, state, zip, country,
    invite_token, reset_token, role, account_status, creation_date, payment_active
    ) values (
    'c5e06fac-6208-4fcc-8cd2-c533da92242a',
    'jhordeym@gmail.com',
    '+351968084553',
    '$2a$10$LKAaJBLG8k4atKKvK0Uw1.GGVUyVXlVS6kf/qXy3euUCDsHhijzYO',
    'Jhordeym',
    'Santos',
    '1997-03-25',
    'Rua',
    'Estoril',
    'Lisboa',
    '9999-999',
    'Portugal',
    'NjkzNWNiOWMtYTAyNi00ZjU1LWFiYzUtZmQxMDRmZjcyYmU3',
    'MTQyYTMxZTQtODM5ZS00OTY4LWJiNzktZjNmMjg0NjEwYmNk',
    'ADMIN',
    'Authenticated',
    '2019-12-16',
     true
    );

