DELETE FROM student;

INSERT INTO student (id, stu_name, class_id, school_id, class_name, stu_age) VALUES
(1, 'Jone', 1, 1, 'class1', 20),
(2, 'Jack', 1, 1, 'class1', 20),
(3, 'Tom', 1, 1, 'class1', 28),
(4, 'Sandy', 3, 1, 'class2', 21),
(5, 'Billie', 5, 3, 'class3', 24);


DELETE FROM school_class;

INSERT INTO school_class (id, class_name, school_id) VALUES
(1, 'class1', 1),
(2, 'class2', 1),
(3, 'class3', 1),
(4, 'class4', 2),
(5, 'class5', 2);

DELETE FROM school;

INSERT INTO school (id, school_name) VALUES
(1, 'ccsu'),
(2, 'kksu'),
(3, 'ddas'),
(4, 'sdas'),
(5, 'aaaa');