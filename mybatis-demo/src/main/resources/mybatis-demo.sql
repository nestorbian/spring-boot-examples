drop table if exists grade_param;
create table grade_param (
	id bigint auto_increment primary key comment "主键",
    name varchar(255) not null comment "年级名称",
    manager_name varchar(50) not null comment "年级主任",
    level tinyint not null comment "等级",
    create_time timestamp not null default current_timestamp on update current_timestamp comment "创建时间",
    update_time timestamp not null default current_timestamp on update current_timestamp comment "更新时间"
) engine=InnoDB charset=utf8mb4 collate=utf8mb4_bin;
drop table if exists school;
create table school (
	id bigint auto_increment primary key comment "主键",
    name varchar(255) not null comment "名称",
    address varchar(50) not null comment "地址",
    build_date date not null comment "建校时间",
    description varchar(1000) null comment "描述",
    create_time timestamp not null default current_timestamp on update current_timestamp comment "创建时间",
    update_time timestamp not null default current_timestamp on update current_timestamp comment "更新时间"
) engine=InnoDB charset=utf8mb4 collate=utf8mb4_bin;
drop table if exists student;
create table student (
	id bigint auto_increment primary key comment "主键",
    name varchar(255) not null comment "名称",
    age smallint(50) not null comment "年龄",
    sex varchar(1) not null comment "性别",
    school_id bigint null comment "学校",
    create_time timestamp not null default current_timestamp on update current_timestamp comment "创建时间",
    update_time timestamp not null default current_timestamp on update current_timestamp comment "更新时间"
) engine=InnoDB charset=utf8mb4 collate=utf8mb4_bin;


CREATE PROCEDURE `student_procedure`(IN inputId bigint, OUT enterScore decimal(8,2))
BEGIN
  select enter_score into enterScore from student where id = inputId;
	select id,
    name,
    age,
    sex,
    school_id,
    enter_score,
    create_time,
    update_time from student where id = inputId;
END

CREATE PROCEDURE `school_procedure`(IN inputId bigint)
begin
	select * from school where id = inputId;
  select * from student where school_id = inputId;
end