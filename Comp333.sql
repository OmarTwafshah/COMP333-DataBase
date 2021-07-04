CREATE DATABASE COMP333;

USE COMP333 ; 

SHOW TABLES ; 


CREATE TABLE  Employees (
	EM_ID int primary key auto_increment, 
    Date_Of_Birth date ,
    Address varchar(50),
    NameEM varchar(20),
    Gender varchar(30),
    Phone_Number long , 
    SalaryEmployees double , 
    Hiring_Date date 
    
);


CREATE TABLE Invoice(
	InvoiceID int AUTO_INCREMENT ,
	Order_ID int ,  
    TotalpaymentDisscount double ,
    primary key(InvoiceID) , 
	foreign key (Order_ID) references OrderDetails(Order_ID)
);

CREATE TABLE  Children (
	EM_ID int , 
    Serial_Number int , 
    Date_Of_Birth date ,
    NameCh varchar(20),
    Gender varchar(30),
    primary key (EM_ID,Serial_Number)
    
    );

CREATE TABLE  Department (
	DEP_ID int primary key auto_increment,
    Name_DEP varchar(50)
);

CREATE TABLE Items (
	Items_ID int auto_increment,
    DEP_ID int , 
    Date_Added date , 
    Items_Price double , 
	production_Date date ,
    expiraation_Date date ,
    itemsName varchar(30) , 
    inStock varchar(10) ,
    Provider_ID int , 
    quantity int ,
    primary key (Items_ID,DEP_ID,Provider_ID)
);

CREATE TABLE Store (
	Store_ID int auto_increment, 
    location varchar(50) ,
    Sales_Per_Month double ,
    primary key (Store_ID)
);

CREATE TABLE Provider (
	Provider_ID int auto_increment, 
    Provider_Name varchar(20),
    Provider_Price double , 
    DateP date , 
    
    primary key (Provider_ID) 
);


CREATE TABLE Customers (
	Customer_ID int primary key auto_increment, 
    City varchar(50)
);

CREATE TABLE Orders (
	Order_ID int auto_increment, 
    Customers_ID int , 
    EM_ID int , 
    Orderdate date ,
    primary key(Order_ID ,EM_ID,Customers_ID)
);

CREATE TABLE OrderDetails (

	Order_ID int , 
	Items_ID int ,
    Unit_Price double , 
    Quantity int , 
    Disscount double ,  
    primary key (Order_ID,Items_ID),
	foreign key (Order_ID) references Orders(Order_ID),
	foreign key (Items_ID) references Items(Items_ID)
    
);

CREATE TABLE Items2Provider (

	Items_ID int , 
    Provider_ID int,
    primary key (Items_ID),
	foreign key (Provider_ID) references Provider(Provider_ID)

);
select * from Provider;
CREATE TABLE Orders2Customers(

	Order_ID int , 
    Customer_ID int,
    primary key (Customer_ID),
	foreign key (Order_ID) references Orders(Order_ID)
);

CREATE TABLE Customers2Items (

	Items_ID int , 
    Customer_ID int,
    primary key (Customer_ID),
	foreign key (Items_ID) references Items(Items_ID)

);

CREATE TABLE Customers2Orders (

	Customer_ID int , 
    Items_ID int,
    primary key (Customer_ID),
	foreign key (Items_ID) references Items(Items_ID)

);

CREATE TABLE Items2Department (

	Items_ID int , 
    DEP_ID int,
    primary key (Items_ID),
	foreign key (DEP_ID) references Department(DEP_ID)

);

CREATE TABLE Store2Department (

	Store_ID int , 
    DEP_ID int,
    primary key (DEP_ID),
	foreign key (Store_ID) references Store(Store_ID)

);

CREATE TABLE Store2Employees (

	Store_ID int , 
    EM_ID int,
    stdate date , 
    primary key (EM_ID),
	foreign key (Store_ID) references Store(Store_ID)

);

CREATE TABLE Store2EmployeesWork (
	Store_ID int , 
    EM_ID int,
    primary key (EM_ID),
	foreign key (Store_ID) references Store(Store_ID)
);

CREATE TABLE Orders2Employees (
	Order_ID int , 
    EM_ID int,
    primary key (EM_ID),
	foreign key (Order_ID) references Orders(Order_ID)
);


