create table if not exists citizen(
aadhar_id integer not null primary key,
name varchar(60),
age integer,
gender enum('M','F','Others'),
district varchar (60),
state varchar (60),
area enum('Rural', 'Urban'),
bank_account boolean,
emp_type enum('Self-employed', 'Private', 'Public', 'Unemployed'),
emp_sector varchar (60),
household_income integer,
family_size integer,
education_level enum('Uneducated', 'School', 'Undergraduate', 'Graduate'),
permanent_housing boolean
);

create table if not exists schemes(
scheme_id integer not null primary key, 
scheme_name varchar(100),
sector varchar(60),
monetary boolean,
scheme_type enum('Individual', 'Household')
);

create table if not exists gov(
gov_name varchar(60) not null primary key,
no_of_residents integer,
no_of_districts integer);

create table if not exists is_eligible(
aadhar_id integer, 
scheme_id integer,
foreign key (aadhar_id) references citizen(aadhar_id),
foreign key (scheme_id) references schemes(scheme_id),
primary key (aadhar_id, scheme_id)
);

create table if not exists is_availing(
aadhar_id integer, 
scheme_id integer,
start_date date,
end_date date,
foreign key (aadhar_id) references citizen(aadhar_id),
foreign key (scheme_id) references schemes(scheme_id),
primary key (aadhar_id, scheme_id, start_date)
);

create table if not exists managed_by(
scheme_id integer,
gov_name varchar(60), 
foreign key (scheme_id) references schemes(scheme_id),
foreign key (gov_name) references gov(gov_name),
primary key (gov_name, scheme_id)
);

create table if not exists scheme_stats(
scheme_id integer,
per_capita_cost integer, 
foreign key (scheme_id) references schemes(scheme_id)
);

create table if not exists gov_stats(
gov_name varchar(60),
total_budget integer, 
foreign key (gov_name) references gov(gov_name)
);


