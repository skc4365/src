show databases;
use exdb;

show tables;

-- 테이블삭제 users
drop table users;

-- 테이블생성 users
create table if not exists users(
	id varchar(50),
	name varchar(100)
);

-- 테이블 결과 확인 
select * from users;

-- 추가 레코드
insert into users(id, name) values ("1", "홍길동");

-- 삭제 레코드
delete from users where id = "4";

-- 없데이트 레코드내용
update users set name = "장영실_수정후" where id = "4";