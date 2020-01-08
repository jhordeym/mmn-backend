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

-- INVESTOR ACCOUNT
insert into account(
    id, email, phone, password, name, last_name,
    birth_date, street, city, state, zip, country,
    invite_token, reset_token, role, account_status, creation_date, payment_active
    ) values (
    '621d71c9-5eb9-433a-a5f7-a8d99bd6c5d8',
    'Drek44@hotmail.com',
    '+18635586377',
    '$2a$10$LKAaJBLG8k4atKKvK0Uw1.GGVUyVXlVS6kf/qXy3euUCDsHhijzYO',
    'Derick',
    'Alers',
    '1977-12-25',
    '2103 Shadow Creek',
    'Dr Kisimmee',
    'FL',
    '34746',
    'USA',
    'NGM5MjEyY2QtNDY4Yi00NmIzLTgyMDEtZjM2NzNlMDc4NTRj',
    'MDU1MTZlODQtNWI3Yi00YzRmLTg2MDItNGZjMTAxMTZiZTVm',
    'INVESTOR',
    'Authenticated',
    '2019-12-16',
     true
    );



insert into account(
    id, email, phone, password, name, last_name,
    birth_date, street, city, state, zip, country,
    invite_token, reset_token, role, account_status, creation_date, payment_active
    ) values (
    'eecaa5f8-a189-4a86-b2e6-b8cb51a1ff00',
    'pablomayuri@hotmail.com',
    '+19546084402',
    '$2a$10$LKAaJBLG8k4atKKvK0Uw1.GGVUyVXlVS6kf/qXy3euUCDsHhijzYO',
    'Pablo',
    'Mayuri',
    '1979-01-08',
    '4810 Kingston Cir.',
    'Dr Kisimmee',
    'FL',
    '34746',
    'USA',
    'MjI4OGQ3NDAtMmM2Yy00ZGYyLThmMTItYmFhZTU4MzBlZWVj',
    'OWQ2MTQzNzktMDBhNS00OTMwLWJlMjYtY2IzYmY4NDExODYx',
    'INVESTOR',
    'Authenticated',
    '2019-12-16',
     true
    );



insert into account(
    id, email, phone, password, name, last_name,
    birth_date, street, city, state, zip, country,
    invite_token, reset_token, role, account_status, creation_date, payment_active
    ) values (
    '393a6ead-bcde-4990-b74e-771f9b3a5cfd',
    'moreto.fernando@gmail.com',
    '+3512765477',
    '$2a$10$LKAaJBLG8k4atKKvK0Uw1.GGVUyVXlVS6kf/qXy3euUCDsHhijzYO',
    'Fernando',
    'Moreto',
    '1971-01-01',
    'Egas Moniz 262 2 esq',
    'Sao Joao Estoril',
    'Lisboa',
    '34746',
    'Portugal',
    'ZDQ3YTViMjAtNDQ1MS00NWRkLTk2MzQtMTc2MzY5MDUwNmI0',
    'YzhjOGM1ZGMtNDIwZi00YmUxLTg4MmYtZTRjNTdjY2EyNGI3',
    'INVESTOR',
    'Authenticated',
    '2019-12-16',
     true
    );

--AMBASSADOR
insert into account(id, account_status, city, country, state, street, zip, birth_date, creation_date, email, invite_token, last_name, name, password, payment_active, phone, RESET_TOKEN, role) values('2aaad9ae-8fae-4241-8433-a63df49b3b4e','New','Orlando','USA','Fl','208 W. Sand Lake Rd? ?Suite 305','32819','2000-02-17 21:00:00','2020-01-07','polilandim@gmail.com','ODA2ZWVhYzAtZDMyMC00MmFiLThlZTgtOGE2ZDAzYTA1NWI2','LANDIM','POLIANA','$2a$10$Sr/VrxT/YZQ59PTW8u.zDukJhX7qvBeyxJg0nRCla4vfdwDHm0aVi','FALSE','+17722849682?','Y2UyNjA5MmMtMTM1ZC00YzJkLTgzMDgtZGEwNjJjYWM1NTMz','AMBASSADOR');
insert into account(id, account_status, city, country, state, street, zip, birth_date, creation_date, email, invite_token, last_name, name, password, payment_active, phone, RESET_TOKEN, role) values('4e9204e3-65cb-4245-a87b-76b61d8305ed','New','Orlando','USA','Fl','208 W. Sand Lake Rd? ?Suite 305','32819','2000-02-17 21:00:00','2020-01-07','mcgarren14@gmail.com','MDNlYjIwMzEtMWU3MS00MzNmLTkwNTEtODUwMzI5NGNkZTQx','JONES','WENDY','$2a$10$BYjsJdfPwS6XIJQb/TYgO.Yj0yMb3gEKRE.LYAXlEwbCZL0k0P.Cq','FALSE','+18018348674?','NTRjODJiNTctNDdlMi00NzRiLWE5ZWYtMGZiNjA4ZDU4ZWYy','AMBASSADOR');
insert into account(id, account_status, city, country, state, street, zip, birth_date, creation_date, email, invite_token, last_name, name, password, payment_active, phone, RESET_TOKEN, role) values('8053e8fa-6ccd-4172-8bba-6183ceeb39f9','New','Orlando','USA','Fl','208 W. Sand Lake Rd? ?Suite 305','32819','1986-01-08 21:00:00','2020-01-07','nossavidausa@gmail.com','NTUwYjUzZGMtZDlkMC00MmRjLTlmM2ItZmFmZGQ1ZDYzNDBm','SILVA','ANDERSON','$2a$10$JhkPgN4LLoC1EyZFuDF2duFTAAaDbM/s9CSyB4bahgabjb1qnDtWG','FALSE','+18573029846?','MDNkMTg1M2UtZjFkZC00MjVhLTkwMzQtZjI2MGQ5MDkwYTdm','AMBASSADOR');
insert into account(id, account_status, city, country, state, street, zip, birth_date, creation_date, email, invite_token, last_name, name, password, payment_active, phone, RESET_TOKEN, role) values('760a0481-34e9-40d6-8233-2a0aa7303654','New','Orlando','USA','Fl','2742 SAND ARBOR CIR','32819','1984-04-05 21:00:00','2020-01-07','julianealmeida@me.com','MzdjNTVlMmMtYjBiZi00NjhkLTg4MWMtNDU2NWU0NGNhZDg5','ALMEIDA','JULIANE','$2a$10$ZCX.k5QU29S6RhAD4/U36.e2dfINaGWYELTJa7Dyv6jj0X6CRy6Um','FALSE','+18134492627?','OGFlODQxNDEtMWRhMy00YjQxLTgxNDItZTA0OTNiZWZkZWQ1','AMBASSADOR');
insert into account(id, account_status, city, country, state, street, zip, birth_date, creation_date, email, invite_token, last_name, name, password, payment_active, phone, RESET_TOKEN, role) values('2529540b-1962-45a7-ab78-c8a0409866cb','New','Orlando','USA','Fl','7208 W. Sand Lake Rd? ?Suite 305?','32819','1990-04-05 21:00:00','2020-01-07','annalayzabusiness@gmail.com','NzBlZDU1ODQtY2M2Ny00NDM4LTk2MzMtM2NjZDFmZTI4ODFk','LAYZA','ANNA','$2a$10$OJsA5lzcj3A7SripUTr3WOhWOOUEMOw.aAGj12wrKEFaDqzXK8iV2','FALSE','+14077795225?','NzQ0MjVmYjgtZDQ0Zi00MThhLTgxZGUtZGVhNGUwMDZkOGZj','AMBASSADOR');

--LEVEL    
insert into level(status, child_id, parent_id) values('Inactive','2aaad9ae-8fae-4241-8433-a63df49b3b4e','621d71c9-5eb9-433a-a5f7-a8d99bd6c5d8');
insert into level(status, child_id, parent_id) values('Inactive','4e9204e3-65cb-4245-a87b-76b61d8305ed','621d71c9-5eb9-433a-a5f7-a8d99bd6c5d8');
insert into level(status, child_id, parent_id) values('Inactive','8053e8fa-6ccd-4172-8bba-6183ceeb39f9','621d71c9-5eb9-433a-a5f7-a8d99bd6c5d8');
insert into level(status, child_id, parent_id) values('Inactive','760a0481-34e9-40d6-8233-2a0aa7303654','eecaa5f8-a189-4a86-b2e6-b8cb51a1ff00');
insert into level(status, child_id, parent_id) values('Inactive','2529540b-1962-45a7-ab78-c8a0409866cb','393a6ead-bcde-4990-b74e-771f9b3a5cfd');
