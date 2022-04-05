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

create table Student(
                        id bigint not null auto_increment,
                        first_name varchar(255),
                        last_name varchar(255),
                        username varchar(255),
                        password varchar(255),
                        primary key(`id`),
                        profile_id bigint,
                        foreign key(`profile_id`) references  `profile`(`id`)
);


create table Teacher(
                        id bigint not null auto_increment,
                        first_name varchar(255),
                        last_name varchar(255),
                        username varchar(255),
                        password varchar(255),
                        primary key(`id`),
                        profile_id bigint,
                        foreign key(`profile_id`) references  `profile`(`id`)

);

create table staff(
                      id bigint not null auto_increment,
                      first_name varchar(255),
                      last_name varchar(255),
                      username varchar(255),
                      password varchar(255),
                      primary key(`id`),
                      profile_id bigint,
                      foreign key(`profile_id`) references  `profile`(`id`)
);

create table curriculum(
                           id bigint not null auto_increment,
                           year int,
                           curriculum_name varchar(255),
                           language varchar(255),
                           primary key(`id`),
                           student_id bigint,
                           foreign key(`student_id`) references `student`(`id`)

);
create table course(
                       id bigint not null auto_increment,
                       primary key(`id`),
                       course_name varchar(255),
                       credits int,
                       maximum_students int,
                       teacher_id bigint,
                       foreign key(`teacher_id`) references `teacher`(`id`),
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
                         course_id bigint,
                         foreign key(`course_id`) references `course`(`id`),
                         curriculum_id bigint,
                         foreign key(`curriculum_id`) references `curriculum`(`id`)
);

