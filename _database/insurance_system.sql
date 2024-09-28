#########################################################
### create database
#########################################################

create database insurance_system;
use insurance_system;

#########################################################
### create tables
#########################################################

create table if not exists customer
(
    id                        varbinary(255) not null primary key,
    password                  varchar(255) not null,
    name                      varchar(255) not null,
    birth                     varchar(100) not null,
    gender                    tinyint(1)   not null,
    occupational_hazard       tinyint(1)   not null,
    smoking                   tinyint(1)   not null
);

create table if not exists employee
(
    id                        varbinary(255) not null primary key,
    password                  varchar(255) not null,
    name                      varchar(255) not null,
    gender                    tinyint(1)   not null,
    department  enum ('MARKETING', 'INVESTIGATING', 'SUPPORTING', 'DEVELOPMENT', 'CONTRACT', 'UNDERWRITING') not null
);

create table if not exists product
(
    id                        varbinary(255) not null primary key,
    name                      varchar(255) not null,
    premium                   int not null,
    senior_rate               float null,
    male_rate                 float null,
    female_rate               float null,
    occupational_hazard_rate  float null,
    smoking_rate              float null,
    released                  tinyint(1) not null
);

create table if not exists contract
(
    id		      varbinary(255) not null primary key,
    customer_id               varbinary(255) not null,
    product_id                varbinary(255) not null,
    premium                   int not null,
    foreign key (customer_id) references customer (id),
    foreign key (product_id)  references product (id)
);

create table if not exists claim
(
    id           varbinary(255) not null primary key,
    compensation int null,
    contract_id  varbinary(255) not null, 
    date         varchar(100) not null,
    description  text not null,
    investigator varchar(255) not null,
    reviewer     varchar(255) not null,
    report       varchar(255)                                                    null,
    status       enum ('REPORTING', 'REVIEWING', 'ACCEPTED', 'REJECTED', 'PAID') not null,
    foreign key (contract_id) references contract(id)
);

create table if not exists board
(
    id           varbinary(255) not null primary key,
    author       varbinary(255) not null,
    title        varchar(255) not null,
    content      text         not null,
    answer       varchar(255) null,
    answerer     varchar(255) null,
    is_answered  tinyint(1) not null,
    foreign key (author) references customer (id)
);

CREATE TABLE customer_server_metric (
    id varchar(60) PRIMARY KEY,
    cpu_usage DOUBLE,
    used_memory DOUBLE,
    http_request_count DOUBLE,
    uptime DOUBLE,
    timestamp TIMESTAMP
);


#########################################################
### drop tables
#########################################################

drop table board;
drop table claim_manage;
drop table contract;
drop table customer;
drop table employee;
drop table product;

#########################################################
### populate records
#########################################################

insert into customer values('C001', '1234', '호준서', '1999-10-29', 0, 0, 0);
insert into customer values('C002', '1234', '고양이', '1972-08-21', 0, 1, 1);
insert into customer values('C003', '1234', '강아지', '1966-01-02', 1, 1, 1);
insert into customer values('C004', '1234', '코끼리', '1994-09-29', 1, 0, 1);
insert into customer values('C005', '1234', '기이린', '1990-11-09', 0, 1, 1);
insert into customer values('C006', '1234', '햄스터', '2002-05-05', 1, 0, 0);
insert into customer values('C007', '1234', '곰탱이', '1950-04-21', 0, 0, 1);


insert into product values('P1', 'Health Care', 70000, 1.5, 1.0, 1.1, 1.8, 1.3, 1);
insert into product values('P2', 'Teeth Care', 50000, 2.0, 1.0, 1.0, 1.2, 2.0, 1);
insert into product values('P3', 'Cancer Care', 90000, 1.8, 1.0, 1.3, 1.2, 2.3, 1);


insert into employee values('E001', '1234', '김개발', 1, 'DEVELOPMENT');
insert into employee values('E002', '1234', '박요율', 1, 'UNDERWRITING');
insert into employee values('E003', '1234', '최검토', 0, 'INVESTIGATING');
insert into employee values('E004', '1234', '정분석', 1, 'INVESTIGATING');
insert into employee values('E005', '1234', '강지원', 0, 'SUPPORTING');
insert into employee values('E006', '1234', '조홍보', 0, 'MARKETING');


insert into board values('B001', 'C001', '안녕하세요', '내용입니다', '', '', 0);
insert into board values('B002', 'C002', '반갑습니다', '내용입니다', '저도 반갑습니다.', 'E001', 1);
insert into board values('B003', 'C003', '감사합니다', '내용입니다', '', '', 0);
