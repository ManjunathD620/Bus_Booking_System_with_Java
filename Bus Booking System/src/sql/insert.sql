insert into employee(salary,driverName,dob) values(10000,"mani",'2001-12-12');
insert into employee(salary,driverName,dob) values(1000,"manjunath",'2001-11-12');
insert into employee(salary,driverName,dob) values(100,"manjunath2",'2001-10-12');
insert into employee(salary,driverName,dob) values(10,"naveen",'2001-09-12');
insert into employee(salary,driverName,dob) values(1,"manoj",'2001-08-12');






insert into vehicle values("kasdw2",1,"dandeli","banglore",'12:55:00',2000);
insert into vehicle values("kasdw3",2,"mysore","tumkur",'11:55:00',1000);
insert into vehicle values("kasdw4",3,"ballari","sirsi",'10:55:00',3000);
insert into vehicle values("kasdw5",4,"unga","bunga",'09:55:00',4000);
insert into vehicle values("kasdw6",5,"kong","godzilla",'08:55:00',5000);

select * from ticket;
select count(seatNo) from ticket where userName="manoj";

select * from employee;
select destination from vehicle;
select userId,userName,phoneNo from clients where userName='Manoj' AND userPassword=md5('1234');