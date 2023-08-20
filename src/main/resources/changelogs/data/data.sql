insert into user_role (name) values ('USER');
INSERT INTO location (name, country, latitude, longitude)
VALUES ('Almaty', 'KZ', 43.25, 76.9);
INSERT INTO location (name, country, latitude, longitude)
VALUES ('Warsaw', 'PL', 52.2167, 21.0333);
insert into user_info (first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, user_id) values ('Mariusz', '1994-12-26', 2, 'man', 'woman', 'https://upload.wikimedia.org/wikipedia/commons/0/0a/Krzysztof_Kononowicz_w_2000.jpg', 'cos o mnie', 1);
insert into users (email, password, user_role_id, user_info_id) values ('mar@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 1);
INSERT INTO user_info (first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, user_id) VALUES ('Sara Kurde Linn', '1990-08-15', 1, 'woman', 'man', 'https://cdn.britannica.com/35/238335-050-2CB2EB8A/Lionel-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg', 'Jestem Sara kurde lin!!!', 2);
insert into users (email, password, user_role_id, user_info_id) values ('mar2@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 2);

insert into user_info (first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, user_id) values ('Jakub', '1996-12-26', 1, 'man', 'woman', 'https://s3.party.pl/newsy/quebonafide-czego-o-nim-nie-wiecie-485687-4_3_800.jpg', 'Jestem sobie Jakub', 3);
insert into users (email, password, user_role_id, user_info_id) values ('mar3@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 3);
INSERT INTO user_info (first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, user_id) VALUES ('Ola', '1990-08-15', 1, 'woman', 'man', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHXCaI3sS-7XvMtZvdbSuVjDd6zlSYNbU-HDCF6bZl6Gq1jSNIV2VDOQ8DAxAe11VsJDA&usqp=CAU', 'Opis Anny',4);
insert into users (email, password, user_role_id, user_info_id) values ('mar4@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 4);

INSERT INTO user_info (first_name, date_of_birth, location_id, gender_identity, gender_interest, url, about, user_id) VALUES ('Mariusz Max Kolonko', '1990-08-15', 1, 'man', 'woman', 'https://static.wirtualnemedia.pl/media/top/MaxKolonko655.png', 'Sukinsyny ukraincy',5);
insert into users (email, password, user_role_id, user_info_id) values ('mar5@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 5);




