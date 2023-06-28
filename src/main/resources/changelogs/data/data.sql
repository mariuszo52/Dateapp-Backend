insert into user_role (id, name) values (1, 'USER');
insert into user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about, user_id) values (1, 'Mariusz', '1994-12-26', false, 'man', 'woman', 'https://upload.wikimedia.org/wikipedia/commons/0/0a/Krzysztof_Kononowicz_w_2000.jpg', 'cos o mnie', 1);
insert into users (id, email, password, user_role_id, user_info_id) values (1, 'mar@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 1);

INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (2, 'Anna', '1990-08-15', true, 'woman', 'man', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHXCaI3sS-7XvMtZvdbSuVjDd6zlSYNbU-HDCF6bZl6Gq1jSNIV2VDOQ8DAxAe11VsJDA&usqp=CAU', 'Opis Anny');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (3, 'Jan', '1985-05-03', true, 'man', 'woman', 'https://img.a.transfermarkt.technology/portrait/big/28003-1671435885.jpg?lm=1', 'Opis Jana');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (4, 'Katarzyna', '1992-11-20', true, 'woman', 'man', 'https://cloudfront-us-east-1.images.arcpublishing.com/infobae/WXB3A5YNXNFU5JZZSYPESPHHEA.jpg', 'Opis Katarzyny');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (5, 'Piotr', '1997-03-10', true, 'man', 'woman', 'https://cloudfront-us-east-1.images.arcpublishing.com/infobae/WXB3A5YNXNFU5JZZSYPESPHHEA.jpg', 'Opis Piotra');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (6, 'Karolina', '1993-07-02', true, 'woman', 'man', 'https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg', 'Opis Karoliny');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (7, 'Marek', '1988-09-18', true, 'man', 'woman', 'https://img.a.transfermarkt.technology/portrait/big/28003-1671435885.jpg?lm=1', 'Opis Marka');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (8, 'Natalia', '1995-12-05', true, 'woman', 'man', 'https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg', 'Opis Natalii');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (9, 'Kamil', '1991-04-27', true, 'man', 'woman', 'https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg', 'Opis Kamila');
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about) VALUES (10, 'Magdalena', '1996-02-13', true, 'woman', 'man', 'https://upload.wikimedia.org/wikipedia/commons/c/c1/Lionel_Messi_20180626.jpg', 'Opis Magdaleny');

-- users
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (4, 'katarzyna@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 4);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (5, 'piotr@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 5);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (6, 'karolina@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 6);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (7, 'marek@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 7);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (8, 'natalia@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 8);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (9, 'kamil@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 9);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (10, 'magdalena@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 10);

INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (11, 'katarzynaaa@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 4);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (12, 'piotar@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 5);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (13, 'karoalina@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 6);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (14, 'mareka@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 7);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (15, 'nataliaa@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 8);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (16, 'kamil@eaxample.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 9);
INSERT INTO users (id, email, password, user_role_id, user_info_id) VALUES (17, 'magdalenaa@example.com', '{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 10);

insert into likes (id, sending_user_id, receiving_user_id, time) values (1, 4, 1, '2022-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (2, 5, 1, '2021-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (3, 6, 1, '2020-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (4, 7, 1, '2023-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (5, 4, 1, '2022-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (6, 5, 1, '2021-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (7, 6, 1, '2020-06-23T12:30:45');
insert into likes (id, sending_user_id, receiving_user_id, time) values (8, 7, 1, '2023-06-23T12:30:45');