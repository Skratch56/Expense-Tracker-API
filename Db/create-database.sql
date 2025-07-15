CREATE DATABASE [expensetracker]
GO

USE [expensetracker];
GO

CREATE TABLE Expense (
                         Id INT NOT NULL IDENTITY,
                         title TEXT NOT NULL,
                         amount TEXT NOT NULL,
                         transactionDate date NOT NULL
                         PRIMARY KEY (Id)
);
GO
