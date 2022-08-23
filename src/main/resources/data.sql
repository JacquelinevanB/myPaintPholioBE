INSERT INTO authorities (authority, username) VALUES ('ROLE_ADMIN', 'admin');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'user');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'ellen');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'paul');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'petra');
INSERT INTO authorities (authority, username) VALUES ('ROLE_ADMIN', 'willem');

INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('admin', 'Y1h1uJ9GcLV6RBnZ8MZg', 'admin@test.nl', TRUE, 'Admin', 'Admin', '$2a$10$ldQQvV0eZFjeaoeM44NH0./awd9.mI9U9VvnnBKzO3M.Cg9i8Aclq', null);
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('user', 'hn6LjJthkXx1weLhYeRc', 'user@test.nl', TRUE, 'User', 'User', '$2a$10$TVGtbRyD5j70gaKI/AMinuUuxBBpFcGM4IZ05TOHP2sCJnVtlAhY6', null);
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('ellen', '0inyjCg0cWlAvMGEBgG8', 'ellen@test.nl', TRUE, 'Ellen', 'Testmiep', '$2a$10$pG6Wi3J7Ch7w8X76CUVleOK86XbeX8PlX7MWn3QbXhixUerI5J36S', null);
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('paul', '8iEkE28fiDkjA3sd5i62', 'paul@test.nl', TRUE, 'Paul', 'Testkees', '$2a$10$1M6Gn.Ebn5yb8UdxDdgo7efJ3/K.rs2IlOGbyUkMaMbdRSUOxprX.', null);
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('petra', 'CuZlDE13baxSDuQFDAbL', 'petra@test.nl', TRUE, 'Petra', 'Testmiep', '$2a$10$Rs5LqzoBo8t8wBijYlMULeOLZJwrZcaqmhFJ.TPPJqd8w3V/ATbAK', null);
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('willem', 'hX6CJ8dbvEQHSW2JWrIH', 'willem@test.nl', TRUE, 'Willem', 'Testkees', '$2a$10$y2NANWnYf6TkJIs1MDzhOuxWX2BjJklZymgJKGFqj1ynzO/vRyZLS', null);

INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1001, '12-6-2021', '23-9-2021', 'Een eenzame herfstboom in een weiland in de avondzon.', 50, 'Monet', FALSE, 'olieverf', 'landschap', 'Solo herfstboom', 40, 'willem');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1002, '12-6-2021', '12-6-2021', 'Een landschap en een soort kubistische weergave.', 20, 'Klee', TRUE, 'aquarel', 'landschap', 'Deconstructed landscape', 30, 'paul');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1003, '12-6-2021', '12-6-2021', 'Olieverf ontdekken met een mini stilleven.', 15, 'Helmantel', TRUE, 'olieverf', 'stilleven', 'Appel', 15, 'willem');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1004, '12-6-2021', '12-6-2021', 'Clown face in wild colors.', 80, null, TRUE, 'acrylverf', 'portret', 'Send in the clown', 60, 'ellen');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1005, '12-6-2021', '12-6-2021', 'A field of colorful summer flowers.', 70, 'Monet', TRUE, 'acrylverf', 'landschap', 'Flowerpower', 70, 'petra');

INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1001, '12-6-2021', 'Vivamus consectetur purus finibus libero pretium, eu dignissim nisi pharetra. Fusce eros lectus, malesuada ac consectetur sed, aliquam eget turpis. Vivamus in ultricies nulla, nec blandit nunc.', 1, 1003, null);
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1002, '12-6-2021', 'Nulla ac facilisis massa. Pellentesque vel magna sed erat sagittis egestas. Quisque condimentum libero dui, nec volutpat augue euismod sit amet. Integer ac ante vitae leo commodo hendrerit vitae at dolor.', 2, 1003, null);
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1003, '12-6-2021', 'Duis in felis ut augue scelerisque placerat id sed sem.', 1, 1005, null);
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1004, '12-6-2021', 'In convallis massa risus, ut volutpat orci ultrices ac. Integer nec ligula eu purus finibus fermentum.', 1, 1005, null);
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1005, '12-6-2021', 'Nullam vitae sodales urna. Etiam nisi lacus, ornare in ante sit amet, congue viverra nulla. Vivamus quis lectus augue.', 2, 1001, null);
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1006, '12-6-2021', 'Donec vel diam fermentum, accumsan ex ut, gravida massa.', 3, 1001, null);

INSERT INTO quote (id, text, source) VALUES (1001, 'There is no must in art, because art is free', 'Wassily Kandinsky');
INSERT INTO quote (id, text, source) VALUES (1002, 'Every artist was first an amateur', 'Ralph Waldo Emerson');
INSERT INTO quote (id, text, source) VALUES (1003, 'Art is a way of survival', 'Yoko Ono');
INSERT INTO quote (id, text, source) VALUES (1004, 'Creativity is nothing but a mind set free', 'Torrie T. Assai');
INSERT INTO quote (id, text, source) VALUES (1005, 'Creativity is intelligence having fun', 'Albert Einstein');


