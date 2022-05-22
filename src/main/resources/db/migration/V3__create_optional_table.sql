Create table optional_list(
         id bigint not null auto_increment,
         primary key(`id`),
         student_id bigint,
         foreign key(`student_id`) references `student`(`id`),
         course_id bigint,
         foreign key (`course_id`)  references `course`(`id`),
         UNIQUE (`student_id`,`course_id`),
         priority int
)