INSERT INTO authorities (authority, username) VALUES ('ROLE_ADMIN', 'admin');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'user');
INSERT INTO authorities (authority, username) VALUES ('ROLE_ADMIN', 'marcel');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'ingrid');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'paul');
INSERT INTO authorities (authority, username) VALUES ('ROLE_USER', 'petra');

INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('abstract1.jpg', 'image/jpeg', 'http://localhost:8080/download/abstract1.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('abstract3.jpg', 'image/jpeg', 'http://localhost:8080/download/abstract3.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('abstract4.jpg', 'image/jpeg', 'http://localhost:8080/download/abstract4.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('abstract5.jpg', 'image/jpeg', 'http://localhost:8080/download/abstract5.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('painting2.jpg', 'image/jpeg', 'http://localhost:8080/download/painting2.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('painting6.jpg', 'image/jpeg', 'http://localhost:8080/download/painting6.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('painting7.jpg', 'image/jpeg', 'http://localhost:8080/download/painting7.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('profilepic1.jpg', 'image/jpeg', 'http://localhost:8080/download/profilepic1.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('profilepic2.jpg', 'image/jpeg', 'http://localhost:8080/download/profilepic2.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('profilepic3.jpg', 'image/jpeg', 'http://localhost:8080/download/profilepic3.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('profilepic4.jpg', 'image/jpeg', 'http://localhost:8080/download/profilepic4.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('profilepic6.jpg', 'image/jpeg', 'http://localhost:8080/download/profilepic6.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('profilepic7.jpg', 'image/jpeg', 'http://localhost:8080/download/profilepic7.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('quanyin1.jpg', 'image/jpeg', 'http://localhost:8080/download/quanyin1.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('quanyin2.jpg', 'image/jpeg', 'http://localhost:8080/download/quanyin2.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('quanyin3.jpg', 'image/jpeg', 'http://localhost:8080/download/quanyin3.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('quanyin4.jpg', 'image/jpeg', 'http://localhost:8080/download/quanyin4.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('schaap1.jpg', 'image/jpeg', 'http://localhost:8080/download/schaap1.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('schaap2.jpg', 'image/jpeg', 'http://localhost:8080/download/schaap2.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('schaap3.jpg', 'image/jpeg', 'http://localhost:8080/download/schaap3.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('schaap4.jpg', 'image/jpeg', 'http://localhost:8080/download/schaap4.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('schaap5.jpg', 'image/jpeg', 'http://localhost:8080/download/schaap5.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('snowwhite2.jpg', 'image/jpeg', 'http://localhost:8080/download/snowwhite2.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('snowwhite3.jpg', 'image/jpeg', 'http://localhost:8080/download/snowwhite3.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('snowwhite4.jpg', 'image/jpeg', 'http://localhost:8080/download/snowwhite4.jpg');
INSERT INTO file_upload_response (file_name, content_type, url) VALUES ('snowwhite5.jpg', 'image/jpeg', 'http://localhost:8080/download/snowwhite5.jpg');

INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('admin', 'Y1h1uJ9GcLV6RBnZ8MZg', 'admin@test.nl', TRUE, 'Admin', 'Admin', '$2a$10$ldQQvV0eZFjeaoeM44NH0./awd9.mI9U9VvnnBKzO3M.Cg9i8Aclq', 'profilepic6.jpg');
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('user', 'hn6LjJthkXx1weLhYeRc', 'user@test.nl', TRUE, 'User', 'User', '$2a$10$TVGtbRyD5j70gaKI/AMinuUuxBBpFcGM4IZ05TOHP2sCJnVtlAhY6', 'profilepic4.jpg');
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('ingrid', 'Oadn6pWgawAp5WUDGFoQ', 'ingrid@test.nl', TRUE, 'Ingrid', 'Testmiep', '$2a$10$bE7zA3Ow28m.nJQY1r9UM.JDA40T/N9UlQQjS99o6wCeOGy//K/yO', 'profilepic2.jpg');
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('paul', '8iEkE28fiDkjA3sd5i62', 'paul@test.nl', TRUE, 'Paul', 'Testkees', '$2a$10$1M6Gn.Ebn5yb8UdxDdgo7efJ3/K.rs2IlOGbyUkMaMbdRSUOxprX.', 'profilepic3.jpg');
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('petra', 'CuZlDE13baxSDuQFDAbL', 'petra@test.nl', TRUE, 'Petra', 'Testmiep', '$2a$10$Rs5LqzoBo8t8wBijYlMULeOLZJwrZcaqmhFJ.TPPJqd8w3V/ATbAK', 'profilepic1.jpg');
INSERT INTO person (username, api_key, email_address, enabled, first_name, last_name, password, file_file_name) VALUES ('marcel', 'hX6CJ8dbvEQHSW2JWrIH', 'marcelm@test.nl', TRUE, 'Marcel', 'Testkees', '$2a$10$y2NANWnYf6TkJIs1MDzhOuxWX2BjJklZymgJKGFqj1ynzO/vRyZLS', 'profilepic7.jpg');

INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1001, '23-9-2021', '20-1-2021', 'Aliquam vitae vulputate', 50, 'Maecenas', TRUE, 'olieverf', 'tristique', 'Etiam sit', 40, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1002, '12-12-2021', '8-2-2021', 'Nunc nec justo ullamcorper, consequat erat molestie.', 20, 'Curabitur', TRUE, 'aquarel', 'aliquet', 'Duis gravida arcu', 30, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1003, '28-6-2022', '24-3-2021', 'Mauris pharetra justo iaculis, placerat sem sit amet, dapibus velit.', 15, 'Aenean', TRUE, 'olieverf', 'placerat', 'Phasellus', 15, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1004, null, '16-04-2022', 'Quisque hendrerit, mi nec pharetra dignissim, massa augue iaculis erat.', 80, 'Mauris', TRUE, 'acrylverf', 'rutrum', 'Aliquam erat volutpat', 60, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1005, null, '9-5-2022', 'Etiam nisl arcu, cursus eget nisi volutpat, vulputate ornare augue. Pellentesque ultricies ligula.', 70, 'Pellentesque', TRUE, 'acrylverf', 'ultricies', 'Mauris pharetra', 70, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1006, '23-9-2021', '12-6-2021', 'Aliquam vitae vulputate', 50, 'Maecenas', TRUE, 'olieverf', 'tristique', 'Etiam sit', 40, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1007, '12-12-2021', '6-7-2021', 'Nunc nec justo ullamcorper, consequat erat molestie.', 20, 'Curabitur', TRUE, 'aquarel', 'aliquet', 'Duis gravida arcu', 30, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1008, '24-11-2021', '5-8-2021', 'Mauris pharetra justo iaculis, placerat sem sit amet, dapibus velit.', 15, 'Aenean', TRUE, 'olieverf', 'placerat', 'Phasellus', 15, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1009, null, '09-12-2021', 'Quisque hendrerit, mi nec pharetra dignissim, massa augue iaculis erat.', 80, 'Mauris', FALSE, 'acrylverf', 'rutrum', 'Aliquam erat volutpat', 60, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1010, null, '30-5-2022', 'Etiam nisl arcu, cursus eget nisi volutpat, vulputate ornare augue. Pellentesque ultricies ligula.', 70, 'Pellentesque', FALSE, 'acrylverf', 'ultricies', 'Mauris pharetra', 70, 'ingrid');
INSERT INTO project (id, date_end, date_start, description, height, inspiration, is_finished, medium_type, subject, title, width, person) VALUES (1011, null, '24-8-2022', 'Aliquam vitae vulputate', 10, 'Curabitur', FALSE, 'acrylverf', 'ultricies', 'Etiam sit', 10, 'ingrid');

INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1001, '20-01-2021', 'Vivamus consectetur purus finibus libero pretium, eu dignissim nisi pharetra. Fusce eros lectus, malesuada ac consectetur sed, aliquam eget turpis.', 1, 1001, 'painting7.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1002, '08-02-2021', 'Nulla ac facilisis massa. Pellentesque vel magna sed erat sagittis egestas. Quisque condimentum libero dui, nec volutpat augue euismod sit amet. Integer ac ante vitae leo commodo hendrerit vitae at dolor.', 1, 1002, 'painting6.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1003, '24-3-2021', 'Duis in felis ut augue scelerisque placerat id sed sem.', 1, 1003, 'painting2.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1004, '16-4-2021', 'In convallis massa risus, ut volutpat orci ultrices ac. Integer nec ligula eu purus finibus fermentum.', 1, 1004, 'abstract5.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1005, '09-5-2021', 'Nullam vitae sodales urna. Etiam nisi lacus, ornare in ante sit amet, congue viverra nulla. Vivamus quis lectus augue.', 1, 1005, 'abstract4.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1006, '12-6-2021', 'Donec vel diam fermentum, accumsan ex ut, gravida massa.', 1, 1006, 'abstract3.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1007, '06-07-2021', 'Vivamus consectetur purus finibus libero pretium, eu dignissim nisi pharetra. Fusce eros lectus, malesuada ac consectetur sed, aliquam eget turpis.', 1, 1007, 'abstract1.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1008, '5-8-2021', 'Nulla ac facilisis massa. Pellentesque vel magna sed erat sagittis egestas. Quisque condimentum libero dui, nec volutpat augue euismod sit amet. Integer ac ante vitae leo commodo hendrerit vitae at dolor.', 1, 1008, 'quanyin1.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1009, '15-9-2021', 'Duis in felis ut augue scelerisque placerat id sed sem.', 2, 1008, 'quanyin2.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1010, '21-10-2021', 'Donec vel diam fermentum, accumsan ex ut, gravida massa.', 3, 1008, 'quanyin3.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1011, '24-11-2021', 'In convallis massa risus, ut volutpat orci ultrices ac. Integer nec ligula eu purus finibus fermentum.', 4, 1008, 'quanyin4.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1012, '9-12-2021', 'Nullam vitae sodales urna. Etiam nisi lacus, ornare in ante sit amet, congue viverra nulla. Vivamus quis lectus augue.', 1, 1009, 'schaap1.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1013, '17-12-2021', 'Donec vel diam fermentum, accumsan ex ut, gravida massa.', 2, 1009, 'schaap2.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1014, '8-2-2022', 'Nulla ac facilisis massa. Pellentesque vel magna sed erat sagittis egestas. Quisque condimentum libero dui, nec volutpat augue euismod sit amet. Integer ac ante vitae leo commodo hendrerit vitae at dolor.', 3, 1009, 'schaap3.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1015, '18-3-2022', 'In convallis massa risus, ut volutpat orci ultrices ac. Integer nec ligula eu purus finibus fermentum.', 4, 1009, 'schaap4.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1016, '13-4-2022', 'Duis in felis ut augue scelerisque placerat id sed sem.', 5, 1009, 'schaap5.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1017, '30-5-2022', 'In convallis massa risus, ut volutpat orci ultrices ac. Integer nec ligula eu purus finibus fermentum.', 1, 1010, 'snowwhite2.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1018, '16-6-2022', 'Nullam vitae sodales urna. Etiam nisi lacus, ornare in ante sit amet, congue viverra nulla. Vivamus quis lectus augue.', 2, 1010, 'snowwhite3.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1019, '4-7-2022', 'Donec vel diam fermentum, accumsan ex ut, gravida massa.', 3, 1010, 'snowwhite4.jpg');
INSERT INTO reflection (id, date_made, reflexion_text, sequence_number, project, file_file_name) VALUES (1020, '09-8-2022', 'Nullam vitae sodales urna. Etiam nisi lacus, ornare in ante sit amet, congue viverra nulla. Vivamus quis lectus augue.', 4, 1010, 'snowwhite5.jpg');

INSERT INTO quote (id, text, source) VALUES (1001, 'There is no must in art, because art is free', 'Wassily Kandinsky');
INSERT INTO quote (id, text, source) VALUES (1002, 'Every artist was first an amateur', 'Ralph Waldo Emerson');
INSERT INTO quote (id, text, source) VALUES (1003, 'Art is a way of survival', 'Yoko Ono');
INSERT INTO quote (id, text, source) VALUES (1004, 'Creativity is nothing but a mind set free', 'Torrie T. Assai');
INSERT INTO quote (id, text, source) VALUES (1005, 'Creativity is intelligence having fun', 'Albert Einstein');
INSERT INTO quote (id, text, source) VALUES (1006, 'Art should comfort the disturbed and disturb the comfortable', 'Carlo Cruz');
INSERT INTO quote (id, text, source) VALUES (1007, 'Creativity is the biggest rebellion in life', 'Osho');


