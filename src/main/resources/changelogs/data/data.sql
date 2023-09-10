insert into user_role (name)
values ('USER');
INSERT INTO location (name, country, latitude, longitude)
VALUES ('Nowy Targ', 'PL', 49.4833, 20.0333);
INSERT INTO location (name, country, latitude, longitude)
VALUES ('Warsaw', 'PL', 52.2167, 21.0333);
INSERT INTO location (name, country, latitude, longitude)
VALUES ('Gdansk', 'PL', 54.3667, 18.6333);
INSERT INTO location (name, country, latitude, longitude)
VALUES ('Zakopane', 'PL', 49.2994, 19.9519);
insert into user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
values ('Krzysztof', '1994-12-26', 2, 'man', 'woman',
        'https://upload.wikimedia.org/wikipedia/commons/0/0a/Krzysztof_Kononowicz_w_2000.jpg',
        'My name is Krzyszof, Im from Białystok.', 50.0);
insert into users
    (email, password, user_role_id, user_info_id)
values ('example1@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 1);
INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Ala', '1990-08-15', 1, 'woman', 'man',
        'https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
        'I like to watch netflix.', 60.0);
insert into users
    (email, password, user_role_id, user_info_id)
values ('example2@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 2);

insert into user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
values ('Jakub', '1996-12-26', 3, 'man', 'woman',
        'https://s3.party.pl/newsy/quebonafide-czego-o-nim-nie-wiecie-485687-4_3_800.jpg',
        'Im Jakub, I like dance and playing football.', 100.0);
insert into users
    (email, password, user_role_id, user_info_id)
values ('example3@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 3);
INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Ola', '1990-08-15', 4, 'woman', 'man',
        'https://images.pexels.com/photos/733872/pexels-photo-733872.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1',
        'I like to play volleyball.', 10000.0);
insert into users
    (email, password, user_role_id, user_info_id)
values ('example4@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 4);

INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Stanisław', '1990-08-15', 1, 'man', 'woman', 'https://static.wirtualnemedia.pl/media/top/MaxKolonko655.png',
        'I like to running', 50.0);
insert into users
    (email, password, user_role_id, user_info_id)
values ('example5@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 5);
INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Robert', '1990-08-15', 1, 'man', 'woman', 'https://s.hs-data.com/bilder/spieler/gross/119750.jpg?fallback=png',
        'I like to play football.', 50.0);
insert into users
(email, password, user_role_id, user_info_id)
values ('example6@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 6);
INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Miley', '1990-08-15', 1, 'woman', 'man', 'https://www.lavanguardia.com/files/content_image_desktop_filter/files/fp/uploads/2023/03/11/640c7d28b4326.r_d.304-248.jpeg',
        'I like to sing.', 50.0);
insert into users
(email, password, user_role_id, user_info_id)
values ('example7@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 7);
INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Dua Lipa', '1990-08-15', 1, 'woman', 'man', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/DuaLipaO2020522_%28101_of_110%29_%2852052470251%29_%28cropped%29.jpg/247px-DuaLipaO2020522_%28101_of_110%29_%2852052470251%29_%28cropped%29.jpg',
        'I like to sing.', 50.0);
insert into users
(email, password, user_role_id, user_info_id)
values ('example8@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 8);
INSERT INTO user_info
(first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, max_distance)
VALUES ('Johny', '1990-08-15', 1, 'man', 'woman', 'https://scontent-waw1-1.xx.fbcdn.net/v/t1.6435-9/123376263_183579083329554_2380279890496455925_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=Wqz7c3InYPQAX8rA8Ip&_nc_ht=scontent-waw1-1.xx&oh=00_AfBYZGQXv9ik3QwCR8imuctEXBHvO9zqEjdKYwhDKrCeNg&oe=6524D605',
        'I am an actor.', 50.0);
insert into users
(email, password, user_role_id, user_info_id)
values ('example9@onet.pl', '{bcrypt}$2a$12$chxm2i8.g1fky70RnMqGHeBAXxHV/02dXyqg.TipoKiO5v/HTvYk.', 1, 9);





