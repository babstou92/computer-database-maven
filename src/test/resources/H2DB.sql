drop schema if exists `computer-database-H2DB`;
  create schema if not exists `computer-database-H2DB`;
  use `computer-database-H2DB`;

  drop table if exists computer;
  drop table if exists company;

  create table company (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_company primary key (id))
  ;

  create table computer (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    introduced                timestamp NULL,
    discontinued              timestamp NULL,
    company_id                bigint default NULL,
    constraint pk_computer primary key (id))
  ;

  alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
  create index ix_computer_company_1 on computer (company_id);

insert into company (id,name) values (  1,'Apple Inc.');
insert into company (id,name) values (  2,'Microsoft');
insert into company (id,name) values (  3,'RCA');
insert into company (id,name) values (  4,'Netronics');


insert into computer (id,name,introduced,discontinued,company_id) values (  1,'MacBook Pro 15.4 inch',2011-06-04,2014-02-04,1);
insert into computer (id,name,introduced,discontinued,company_id) values (  2,'SurfaceBook',null,null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  3,'SurfaceLaptop1',2015-01-02,2018-01-01,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  4,'SurfaceLaptop2',null,null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  5,'Surface4','1991-01-01',null,2);
insert into computer (id,name,introduced,discontinued,company_id) values (  6,'MacBook Pro','2006-01-10',null,1);
insert into computer (id,name,introduced,discontinued,company_id) values (  7,'Apple IIe',null,null,1);
insert into computer (id,name,introduced,discontinued,company_id) values (  8,'Apple IIc',null,null,1);
insert into computer (id,name,introduced,discontinued,company_id) values (  9,'Apple IIGS',null,null,1);
insert into computer (id,name,introduced,discontinued,company_id) values ( 10,'Apple IIc Plus',null,null,null);


