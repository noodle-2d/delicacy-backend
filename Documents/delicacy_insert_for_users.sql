select * from users;
delete from users;

-- Field: user_id, login, password_hash, name, surname, phone_number, ip_address, last_visited_date, role, registration_date, email, link

insert into users values(1, 'admin_user', '$2a$10$2ye26xEjQA9heyLYPTAkiOA1ZqMC0CuPPVOac0avLn4TCapxLv7u6', null, null, null, null, null, 'ADMIN', null, null, null);
insert into users values(2, 'unauthorized_user', null, null, null, null, null, null, 'UNAUTHORIZED', null, null, null);
insert into users values(3, null, null, null, null, null, '0:0:0:0:0:0:0:1', null, 'UNAUTHORIZED_WITH_IP', null, null, null);
insert into users values(4, 'customer_user', '$2a$10$2ye26xEjQA9heyLYPTAkiOA1ZqMC0CuPPVOac0avLn4TCapxLv7u6', 'Name', 'Surname', null, null, null, 'CUSTOMER', null, null, null);
insert into users values(5, 'moderator_user', '$2a$10$2ye26xEjQA9heyLYPTAkiOA1ZqMC0CuPPVOac0avLn4TCapxLv7u6', 'Name', 'Surname', null, null, null, 'MODERATOR', null, null, null);

create sequence hibernate_sequence start with 6;