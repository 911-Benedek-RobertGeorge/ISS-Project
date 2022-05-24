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



