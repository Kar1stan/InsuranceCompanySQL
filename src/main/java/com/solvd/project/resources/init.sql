create table PolicyHolders
(
  PolicyHoldersId int Primary Key identity(1,1),
  Name varchar(50),
  Contact varchar(50),
  DOB Date,
)

insert into PolicyHolders 
(Name,Contact,DOB) values ('John','03056789','1985-07-22')

insert into PolicyHolders 
(Name,Contact,DOB) values ('Yoda','06058780','2000-02-15')

insert into PolicyHolders 
(Name,Contact,DOB) values ('Bill','02026781','1992-05-25')

insert into PolicyHolders 
(Name,Contact,DOB) values ('Bum','04057780','1200-02-15')

insert into PolicyHolders 
(Name,Contact,DOB) values ('Mikey','01026787','1980-06-20')

insert into PolicyHolders 
(Name,Contact,DOB) values ('Jessica','080125409','1987-09-23')

create table Policies
(
  PolicyId int Primary Key identity(1,1),
  Type varchar(50),
  Coverage int,
  StartDate Date,
  EndDate Date,
  Status varchar(20),
  PolicyHolderId INT,
  FOREIGN KEY (PolicyHolderId) REFERENCES PolicyHolders(PolicyHoldersId)
)

insert into Policies
(Type,Coverage,StartDate,EndDate,Status) values ('Auto',10000,'2000-02-15','2001-05-12','Active')

insert into Policies
(Type,Coverage,StartDate,EndDate,Status) values ('Home',900,'2001-07-28','2001-08-11','Canceled')

insert into Policies
(Type,Coverage,StartDate,EndDate,Status) values ('Health',1500,'2015-07-21','2018-02-08','Active')

insert into Policies
(Type,Coverage,StartDate,EndDate,Status) values ('Travel',2000,'2011-01-16','2011-01-29','Expired')

insert into Policies
(Type,Coverage,StartDate,EndDate,Status) values ('Travel',2000,'2013-06-18','2017-10-12','Active')


create table Vehicles
(
  VehicleId int Primary Key identity(1,1),
  Model varchar(50),
  Registration_Year varchar(10),
  VIN varchar(50),
  PolicyID INT,
  DriverID INT,
  FOREIGN KEY (PolicyID) REFERENCES Policies(PolicyId),
  FOREIGN KEY (DriverID) REFERENCES Drivers(DriverId)
)

insert into Vehicles
(Model,Registration_Year,VIN) values ('BMW','2021','8S3BMHB683226050')

insert into Vehicles
(Model,Registration_Year,VIN) values ('Audi','2020','6A3AUDB683327010')

insert into Vehicles
(Model,Registration_Year,VIN) values ('Honda','2019','6H3HONB683628018')

insert into Vehicles
(Model,Registration_Year,VIN) values ('Ferrari','2023','6F3FERB263631001')

insert into Vehicles
(Model,Registration_Year,VIN) values ('Kia','2018','6F3KIAB264648002')

create table Drivers
(
  DriverId int Primary Key identity(1,1),
  License varchar(20),
  Experience varchar(50),
  VehicleID INT,
  FOREIGN KEY (VehicleID) REFERENCES Vehicles(VehicleId)
)

insert into Drivers
(License,Experience) values ('Class A','7 years')

insert into Drivers
(License,Experience) values ('Class B','10 years')

insert into Drivers
(License,Experience) values ('Class C','1 year')

insert into Drivers
(License,Experience) values ('Class T','4 years')

insert into Drivers
(License,Experience) values ('Class CE','11 years')

insert into Drivers
(License,Experience) values ('Class B+','8 years')


create table Claims
(
  ClaimId int Primary Key identity(1,1),
  Amount int,
  Status varchar(50),
  Date_Filled Date,
  PolicyID INT,
  VehicleID INT,
  DriverID INT,
  FOREIGN KEY (PolicyID) REFERENCES Policies(PolicyId),
  FOREIGN KEY (VehicleID) REFERENCES Vehicles(VehicleId),
  FOREIGN KEY (DriverID) REFERENCES Drivers(DriverId)
)

insert into Claims
(Amount,Status,Date_Filled) values (15000,'Vehicle Damage','2020-02-12')

insert into Claims
(Amount,Status,Date_Filled) values (1370,'Theft','2022-09-20')

insert into Claims
(Amount,Status,Date_Filled) values (20000,'Medical','2020-04-14')

insert into Claims
(Amount,Status,Date_Filled) values (111,'Liability','2023-03-19')

insert into Claims
(Amount,Status,Date_Filled) values (900,'Injury','2024-01-18')




