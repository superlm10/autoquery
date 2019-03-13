-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS student;
CREATE TABLE student
(
  id      BIGINT (20) NOT NULL COMMENT '主键ID',
  stu_name    VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
  class_id    BIGINT (20) NOT NULL COMMENT '课程ID',
  school_id    BIGINT (20) NOT NULL COMMENT '学校ID',
  class_name VARCHAR(30) NULL DEFAULT NULL COMMENT '课程名',
  stu_age     INT (11) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS school_class;
CREATE TABLE school_class
(
  id      BIGINT (20) NOT NULL COMMENT '主键ID',
  class_name    VARCHAR(30) NULL DEFAULT NULL COMMENT '课程名',
  school_id    BIGINT (20) NOT NULL COMMENT '学校ID',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS school;
CREATE TABLE school
(
  id      BIGINT (20) NOT NULL COMMENT '主键ID',
  school_name    VARCHAR(30) NULL DEFAULT NULL COMMENT '学校名',
  PRIMARY KEY (id)
);
