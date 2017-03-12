
DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
DELETE FROM restaurants;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO RESTAURANTS (id, name) VALUES
  (110000, 'McDonalds'),
  (110001, 'BurgerKing');

-- user
INSERT INTO users (name, email, password, restaurant_id)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 110000);

-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO meals (date_time, description,price, rest_id) VALUES
  ('2015-05-30 10:00:00', 'Завтрак', 100, 110000),
  ('2015-05-30 13:00:00', 'Обед', 500, 110000),
  ('2015-05-30 20:00:00', 'Ужин', 400, 110000),
  ('2015-05-31 10:00:00', 'Завтрак', 70, 110001),
  ('2015-05-31 13:00:00', 'Обед', 300, 110001),
  ('2015-05-31 20:00:00', 'Ужин', 300, 110001);
