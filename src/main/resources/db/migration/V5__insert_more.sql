 INSERT INTO teacher(user_id,chef_department,degree) VALUE (1,1,'lecturer'),
                                                            (2,0,'lab');


 INSERT INTO course(course_name, credits, maximum_students,teacher_id, curriculum_id, followers, required)
 VALUES ('Logică matematică si teoria mulţimilor', 6, 200,1, 1, 112, 'MANDATORY'),
        ('Analiză matematică 1 (pe r)', 6, 200,1, 1, 222, 'MANDATORY'),
        ('Geometrie 1 (geometrie analitica)', 6, 200,2,2 , 111, 'MANDATORY'),
        ('Algebra 1 (algebra liniara)', 6, 200,2, 1, 111, 'MANDATORY');
