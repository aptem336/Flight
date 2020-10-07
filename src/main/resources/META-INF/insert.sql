INSERT INTO COUNTRY (ID, NAME) VALUES ('1', 'Россия'), ('2', 'Англия'), ('3', 'США')
INSERT INTO CITY (ID, NAME, COUNTRY_ID) VALUES ('1', 'Москва', '1'), ('2', 'Санкт-Питергбург', '1'), ('3', 'Лондон', '2'), ('4', 'Манчестер', '2'), ('5', 'Нью-Йорк', '3'), ('6', 'Вашингтон', '3')
INSERT INTO AIRPORT (ID, NAME, CITY_ID) VALUES ('1', 'Шереметьево', '1'), ('2', 'Домодедово', '1'), ('3', 'Пулково', '2'), ('4', 'Хитроу', '3'), ('5', 'Гатвик', '3'), ('6', 'Манчестер', '4'), ('7', 'Кеннеди', '5'), ('8', 'Даллеса', '6')
INSERT INTO USERS (LOGIN, PASSWORD, ROLE) VALUES ('SYSTEM', 'PBKDF2WithHmacSHA256:2048:WWd117hG5k9V88GL9bJj8kQhOTcDHOaEuc4QHeuJjnU=:HB9D4bekwMDONhUQvl/+0tVCUGI7nx+s31qw+lr7oAQ=', 1)
