DELETE FROM student;

INSERT INTO student (id, stu_name, class_id, school_id, class_name, stu_age, deleted) VALUES
(1, 'Jone', 1, 1, 'class1', 20, 0),
(2, 'Jack', 1, 1, 'class1', 20, 0),
(3, 'Tom', 1, 1, 'class1', 28, 0),
(4, 'Sandy', 3, 1, 'class2', 21, 0),
(5, 'Billie', 5, 3, 'class3', 24, 1);


DELETE FROM school_class;

INSERT INTO school_class (id, class_name, school_id, deleted) VALUES
(1, 'class1', 1, 0),
(2, 'class2', 1, 0),
(3, 'class3', 1, 0),
(4, 'class4', 2, 0),
(5, 'class5', 2, 0);

DELETE FROM school;

INSERT INTO school (id, school_name, deleted) VALUES
(1, 'ccsu', 0),
(2, 'kksu', 0),
(3, 'ddas', 0),
(4, 'sdas', 0),
(5, 'aaaa', 0);