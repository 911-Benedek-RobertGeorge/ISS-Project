use iss_project;


INSERT INTO specialization(years_of_study, name) VALUES (3, 'Informatica'),
                                                        (4, 'Calculatoare'),
                                                        (4, 'Inginerie');



INSERT INTO user_info(first_name, last_name, username,password,role) VALUES ('Matei', 'Barba', 'mateibarba','$2a$10$cLTGO3lWXPDKT84X2Gcu9eBY7pFwXEUokjtac/vCHICskd6DQxtni','TEACHER'),
                                                              ('Robert', 'Benedek', 'robertbenedek','$2a$10$cLTGO3lWXPDKT84X2Gcu9eBY7pFwXEUokjtac/vCHICskd6DQxtni','TEACHER'),
                                                              ('Marcu', 'Bale', 'marcubale','$2a$10$cLTGO3lWXPDKT84X2Gcu9eBY7pFwXEUokjtac/vCHICskd6DQxtni','STUDENT'),
                                                              ('user', 'user', 'user','$2a$10$cLTGO3lWXPDKT84X2Gcu9eBY7pFwXEUokjtac/vCHICskd6DQxtni','STUDENT'),
                                                              ('user', 'user', 'user','$2a$10$cLTGO3lWXPDKT84X2Gcu9eBY7pFwXEUokjtac/vCHICskd6DQxtni','STUDENT'),
                                                              ('Ilinca', 'Apostol', 'ilincaapostol','$2a$10$cLTGO3lWXPDKT84X2Gcu9eBY7pFwXEUokjtac/vCHICskd6DQxtni','STAFF');

insert into profile(sex,city,address,mail_address,phone_number,age)
VALUES('male','Cluj','centru','a@a.com','0745078392',12),
       ('male','Buc','centru','a@a.com','0745078311',22),
 ('male','Cluj','centru','a@a.com','0745078322',22);

INSERT INTO student(user_id,profile_id) VALUES(4,1);
INSERT INTO student(user_id,profile_id) VALUES(3,2);
INSERT INTO student(user_id,profile_id) VALUES(5,3);

INSERT INTO curriculum(year, curriculum_name, language, specialization_id) VALUES (1, 'MateAn1', 'Romana', 1),
                                                                                  (2, 'MateAn2', 'Romana', 1),
                                                                                  (3, 'MateAn3', 'Romana', 1);


INSERT INTO enroll(specialization_id, student_id) VALUES (1,1), (1,2),(1,3);
INSERT INTO contract(student_id, curriculum_id) VALUES (1,1),
                                                       (2,1),
                                                       (3,1);



/*INSERT INTO course(course_name, credits, maximum_students, curriculum_id, followers, required) VALUES
                                                                                                   ('Logică matematică si teoria mulţimilor', 6, 200, 1, 0, 'MANDATORY'),
                                                                                                   ('Analiză matematică 1 (pe r)', 6, 200, 1, 0, 'MANDATORY'),
                                                                                                   ('Geometrie 1 (geometrie analitica)', 6, 200, 1, 0, 'MANDATORY'),
                                                                                                   ('Algebra 1 (algebra liniara)', 6, 200, 1, 0, 'MANDATORY'),
                                                                                                   ('Algoritmi și programare', 6, 200, 1, 0, 'MANDATORY'),
                                                                                                   ('Matematica de baza', 6, 200, 1, 0, 'OPTIONAL'),
                                                                                                   ('Sisteme de operare', 6, 200, 1, 0, 'OPTIONAL'),

                                                                                                   ('Programare orientata obiect', 6, 200, 2, 0, 'MANDATORY'),
                                                                                                   ('Algebra 2 (structuri algebrice de baza)', 6, 200, 2, 0, 'MANDATORY'),
                                                                                                   ('Geometrie 2 (geometrie afină)', 6, 200, 2, 0, 'MANDATORY'),
                                                                                                   ('Analiză matematică 2 (calcul diferenţial şi integral în r^n)', 6, 200, 2, 0, 'MANDATORY'),
                                                                                                   ('Structuri de date', 6, 200, 2, 0, 'MANDATORY'),
                                                                                                   ('Aplicatii ale geometriei in informatica', 6, 200, 2, 0, 'OPTIONAL'),
                                                                                                   ('Probleme avansate de analiza', 6, 200, 2, 0, 'OPTIONAL');*/

