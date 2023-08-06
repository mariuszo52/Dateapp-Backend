insert into user_role (id, name) values (1, 'USER');
insert into user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about, user_id) values (1, 'Mariusz', '1994-12-26', false, 'man', 'woman', 'https://upload.wikimedia.org/wikipedia/commons/0/0a/Krzysztof_Kononowicz_w_2000.jpg', 'cos o mnie', 1);
insert into users (id, email, password, user_role_id, user_info_id) values (1, 'mar@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 1);
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about, user_id) VALUES (2, 'Sara Kurde Linn', '1990-08-15', true, 'woman', 'man', 'https://cdn.costumewall.com/wp-content/uploads/2019/10/sarah-lynn.jpg', 'Jestem Sara kurde lin!!!', 2);
insert into users (id, email, password, user_role_id, user_info_id) values (2, 'mar2@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 2);

insert into user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about, user_id) values (3, 'Jakub', '1996-12-26', false, 'man', 'woman', 'https://s3.party.pl/newsy/quebonafide-czego-o-nim-nie-wiecie-485687-4_3_800.jpg', 'Jestem sobie Jakub', 3);
insert into users (id, email, password, user_role_id, user_info_id) values (3, 'mar3@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 3);
INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about, user_id) VALUES (4, 'Ola', '1990-08-15', true, 'woman', 'man', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHXCaI3sS-7XvMtZvdbSuVjDd6zlSYNbU-HDCF6bZl6Gq1jSNIV2VDOQ8DAxAe11VsJDA&usqp=CAU', 'Opis Anny',4);
insert into users (id, email, password, user_role_id, user_info_id) values (4, 'mar4@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 4);

INSERT INTO user_info (id, first_name, date_of_birth, show_gender, gender_identity, gender_interest, url, about, user_id) VALUES (5, 'Mariusz Max Kolonko', '1990-08-15', true, 'man', 'woman', 'https://static.wirtualnemedia.pl/media/top/MaxKolonko655.png', 'Sukinsyny ukraincy',5);
insert into users (id, email, password, user_role_id, user_info_id) values (5, 'mar5@onet.pl','{bcrypt}$2a$10$uyYHtSgt6sgduTaQIvbR7OVKDqfhgRmpUiSZINlGGf/KSFg0eNInC', 1, 5);




