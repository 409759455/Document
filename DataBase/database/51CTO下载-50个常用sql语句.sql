create database school

create table Student(Sid int identity primary key,Sname varchar(20),Sage varchar(20),Ssex varchar(20))-- ѧ���� 
create table Course(Cid int identity primary key,Cname  varchar(20),Tid int references Teacher(Tid))-- �γ̱� 
create table SC(Sid int primary key identity,Cid int references Course(Cid),score int)-- �ɼ��� 
create table Teacher(Tid int primary key identity,Tname varchar(20)) --��ʦ�� 

--Student(Sid,Sname,Sage,Ssex) ѧ���� 
--Course(Cid,Cname,Tid) �γ̱� 
--SC(Sid,Cid,score) �ɼ��� 
--Teacher(Tid,Tname) ��ʦ�� 

select tid from (select * from sc where Sid=1) a,(select score from sc where sid=2) b,Course where a.score>b.score and a.cid=Course.Cid
select sid,AVG(score) as  'ƽ���ɼ�' from sc group by sid having AVG(score)>60
select Student.Sid,Student.Sname,count(SC.Cid) 'zongkeshu',sum(score) 'zongchengji' from Student left join sc on Student.Sid=sc.Sid group by Student.Sid,Sname
select COUNT(Teacher.Tname) from Teacher where Tname like '��%' 
select Student.Sid,Student.Sname from Student where Student.Sid not in (select sc.Sid from sc,Teacher,Course where Teacher.Tname='Ҷƽ' and Teacher.Tid=Course.Tid and Course.Cid=sc.Cid)
select Student.Sid,Student.Sname from Student,SC where Student.Sid=SC.Sid and SC.Cid='001'and exists( Select * from SC as SC_2 where SC_2.Sid=SC.Sid and SC_2.Cid='002'); 


















