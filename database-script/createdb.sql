CREATE DATABASE TheCoffeeBreak;
GO

USE TheCoffeeBreak;

CREATE TABLE Customers(
	Id INT PRIMARY KEY NOT NULL, 
	Name NVARCHAR(50) NOT NULL, 
	Phone CHAR(12) NULL,
	Email NVARCHAR(128) NOT NULL
);

CREATE TABLE Products(
	Id int PRIMARY KEY NOT NULL,
	Name nvarchar(50) UNIQUE NOT NULL, 
	Description nvarchar(MAX), 
	Price decimal(18,2)
);

CREATE TABLE Orders(
	Id int PRIMARY KEY NOT NULL,
	CustomerId int FOREIGN KEY REFERENCES Customers(Id) NOT NULL,
	Discount decimal(18,2) DEFAULT(0)
);

CREATE TABLE OrderLines(
	Id int PRIMARY KEY NOT NULL,
	OrderId int FOREIGN KEY REFERENCES Orders(Id) NOT NULL,
	ProductId int FOREIGN KEY REFERENCES Products(Id) NOT NULL,
	Quantity int NOT NULL,
	SubTotal decimal(18,2) NOT NULL
);
GO 

-- Customers
INSERT INTO Customers VALUES (1, 'Rick Sanchez', '202-555-0413', 'quisque.ornare@atauctor.edu');
INSERT INTO Customers VALUES (2, 'Morty Smith', '202-555-0909', 'non.lacinia@ut.com');
INSERT INTO Customers VALUES (3, 'Tammy Guetermann', '202-555-0678', 'leo.elementum@aliquamarcu.co.uk');
INSERT INTO Customers VALUES (4, 'Scroopy Noopers', '202-555-0852', 'lobortis.risus.in@dignissimmagnaa.edu');
INSERT INTO Customers VALUES (5, 'Reverse Giraffe', '202-555-0434', 'sed.neque@praesent.org');
INSERT INTO Customers VALUES (6, 'Summer Smith', '202-555-0754', 'nulla@tincidunt.com');
INSERT INTO Customers VALUES (7, 'Beth Smith', '202-555-0467', 'in.cursus.et@elit.edu');
INSERT INTO Customers VALUES (8, 'Jessica Palmer', '202-555-0943', 'ac@vestibulummassarutrum.com');
INSERT INTO Customers VALUES (9, 'Jerry Smith', '202-555-0262', 'integer.vulputate.risus@eratsednunc.net');

-- Products
INSERT INTO Products VALUES (1, 'Americano', 'Espresso shots topped with hot water create a light layer of crema culminating in this wonderfully rich cup with depth and nuance.', 22.5);
INSERT INTO Products VALUES (2, 'Dark Roast Coffee', 'This full-bodied dark roast coffee with bold, robust flavors showcases our roasting and blending artistry—an essential blend of balanced and lingering flavors.', 32.5);
INSERT INTO Products VALUES (3, 'Misto', 'A one-to-one combination of fresh-brewed coffee and steamed milk add up to one distinctly delicious coffee drink remarkably mixed.', 32.5);
INSERT INTO Products VALUES (4, 'Cappuccino', 'Dark, rich espresso lies in wait under a smoothed and stretched layer of thick milk foam. An alchemy of barista artistry and craft.', 29.5);
INSERT INTO Products VALUES (5, 'Espresso', 'Our smooth signature Espresso Roast with rich flavor and caramelly sweetness is at the very heart of everything we do.', 19.5);
INSERT INTO Products VALUES (6, 'Latte', 'Our dark, rich espresso balanced with steamed milk and a light layer of foam. A perfect milk-forward warm-up.', 29.5);
INSERT INTO Products VALUES (7, 'Cinnamon Dolce Latte', 'We add freshly steamed milk and cinnamon dolce-flavored syrup to our classic espresso, topped with sweetened whipped cream and a cinnamon dolce topping to bring you specialness in a treat.', 32.5);
INSERT INTO Products VALUES (8, 'Flat White', 'Smooth ristretto shots of espresso get the perfect amount of steamed whole milk to create a not-too-strong, not-too-creamy, just-right flavor.', 29.5);
INSERT INTO Products VALUES (9, 'Caramel Macchiato', 'Freshly steamed milk with vanilla-flavored syrup marked with espresso and topped with a caramel drizzle for an oh-so-sweet finish.', 29.5);

-- Orders
INSERT INTO Orders VALUES (1, 2, 0);
INSERT INTO Orders VALUES (2, 3, 0.1);
INSERT INTO Orders VALUES (3, 6, 0);
INSERT INTO Orders VALUES (4, 7, 0);
INSERT INTO Orders VALUES (5, 4, 0.1);
INSERT INTO Orders VALUES (6, 5, 0.25);
INSERT INTO Orders VALUES (7, 6, 0);
INSERT INTO Orders VALUES (8, 1, 0);

-- OrderLines
INSERT INTO OrderLines VALUES  (1, 1, 1, 1, 0.0)
INSERT INTO OrderLines VALUES  (2, 1, 2, 1, 0.0)
INSERT INTO OrderLines VALUES  (3, 2, 1, 1, 0.0)
INSERT INTO OrderLines VALUES  (4, 3, 4, 2, 0.0)
INSERT INTO OrderLines VALUES  (5, 4, 6, 1, 0.0)
INSERT INTO OrderLines VALUES  (6, 5, 6, 2, 0.0)
INSERT INTO OrderLines VALUES  (7, 5, 7, 3, 0.0)
INSERT INTO OrderLines VALUES  (8, 6, 1, 3, 0.0)
INSERT INTO OrderLines VALUES  (9, 7, 9, 1, 0.0)
INSERT INTO OrderLines VALUES (10, 8, 3, 2, 0.0)
