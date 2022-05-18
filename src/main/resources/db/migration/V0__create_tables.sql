use ISS_project;
create table profile(
                        id bigint not null auto_increment,
                        sex varchar(255),
                        city varchar(255),
                        address varchar(255),
                        mail_address varchar(255),
                        phone_number int,
                        age int,
                        primary key(`id`)
);
create table user_info(
                          id bigint not null auto_increment,
                          primary key(`id`),
                          first_name varchar(255),
                          last_name varchar(255),
                          username varchar(255),
                          password varchar(255)
);

create table Student(
                        id bigint not null auto_increment,
                        user_id bigint,
                        foreign key(`user_id`) references `user_info`(`id`),
                        primary key(`id`),
                        profile_id bigint,
                        foreign key(`profile_id`) references  `profile`(`id`)
);


create table Teacher(
                        id bigint not null auto_increment,
                        user_id bigint,
                        foreign key(`user_id`) references `user_info`(`id`),
                        primary key(`id`),
                        profile_id bigint,
                        foreign key(`profile_id`) references  `profile`(`id`),
                        chef_department bool
);

create table staff(
      id bigint not null auto_increment,
      user_id bigint,
      foreign key(`user_id`) references `user_info`(`id`),
      primary key(`id`),
      profile_id bigint,
      foreign key(`profile_id`) references  `profile`(`id`)
);


create table specialization(
                               id bigint not null auto_increment,
                               primary key(`id`),
                               years_of_study int,
                               name varchar (255)
);



create table curriculum(
                           id bigint not null auto_increment,
                           year int,
                           curriculum_name varchar(255),
                           language varchar(255),
                           primary key(`id`),



                           specialization_id bigint,
                           foreign key(`specialization_id`) references `specialization`(`id`)


);
create table course(
                       id bigint not null auto_increment,
                       primary key(`id`),
                       course_name varchar(255),
                       credits int,
                       maximum_students int,
                       teacher_id bigint,
                       foreign key(`teacher_id`) references `teacher`(`id`),
                       curriculum_id bigint,
                       foreign key(`curriculum_id`) references `curriculum`(`id`),
                       followers int,
                       required enum ('OPTIONAL','MANDATORY')
);
create table grade (
                       id bigint not null auto_increment,
                       student_id bigint,
                       foreign key(`student_id`) references `student`(`id`),
                       course_id bigint,
                       foreign key (`course_id`)  references `course`(`id`),
                       grade int,
                       received_date date,
                       primary key(`id`)
);

create table contract(
                         id bigint not null auto_increment,
                         primary key(`id`),

                         student_id bigint,
                         foreign key(`student_id`) references `student`(`id`),
                         curriculum_id bigint,
                         foreign key(`curriculum_id`) references `curriculum`(`id`),
                         sign_date date,
                         fisier varchar(1000)
);

create table enroll(
                       id bigint not null auto_increment,
                       primary key(`id`),
                       specialization_id  bigint,
                       foreign key (`specialization_id`) references `specialization`(`id`),
                       student_id bigint,
                       foreign key(`student_id`) references `student`(`id`)

);
