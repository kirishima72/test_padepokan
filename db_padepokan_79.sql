USE tempdb
GO

DROP DATABASE IF EXISTS Padepokan_79;
GO

CREATE DATABASE Padepokan_79
GO

USE Padepokan_79;
GO

CREATE TABLE Nasabah
(
    [account_id] INT NOT NULL IDENTITY(1,1),
    [name] NVARCHAR(55),
    [created_by] NVARCHAR(255),
    [created_date] DATETIME DEFAULT GETDATE(),
    [modified_by] NVARCHAR(255),
    [modified_date] DATETIME,
    CONSTRAINT pk_account_id PRIMARY KEY(account_id)
);
GO

CREATE TABLE Transaksi
(
    [transaction_id] INT NOT NULL IDENTITY(1,1),
    [transaction_date] DATETIME NOT NULL,
    [description] NVARCHAR(255) NOT NULL,
    [debit_credit_status] NVARCHAR(1) NOT NULL CHECK(debit_credit_status IN ('D', 'C')),
    [amount] MONEY NOT NULL,
    [transaction_account_id] INT NOT NULL,
    [created_by] NVARCHAR(255),
    [created_date] DATETIME DEFAULT GETDATE(),
    [modified_by] NVARCHAR(255),
    [modified_date] DATETIME,
    CONSTRAINT pk_transaction_id PRIMARY KEY(transaction_id),
    CONSTRAINT fk_transaction_account_id FOREIGN KEY(transaction_account_id) 
    REFERENCES Nasabah(account_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
GO

CREATE TABLE Report_Print_Rekening
(
    [report_id] INT NOT NULL IDENTITY(1,1),
    [report_account_id] INT NOT NULL,
    [report_start_date] DATETIME NOT NULL,
    [report_end_date] DATETIME NOT NUll,
    [created_by] NVARCHAR(255),
    [created_date] DATETIME DEFAULT GETDATE(),
    [modified_by] NVARCHAR(255),
    [modified_date] DATETIME,
    CONSTRAINT pk_report_id PRIMARY KEY(report_id),
    CONSTRAINT fk_report_account_id FOREIGN KEY(report_account_id) REFERENCES Nasabah(account_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)

CREATE TABLE Point
(
    [point_account_id] INT NOT NULL,
    [point_nasabah] INT NOT NULL,
    [created_by] NVARCHAR(255),
    [created_date] DATETIME DEFAULT GETDATE(),
    [modified_by] NVARCHAR(255),
    [modified_date] DATETIME,
    CONSTRAINT pk_point_account_id PRIMARY KEY(point_account_id),
    CONSTRAINT fk_point_account_id FOREIGN KEY(point_account_id) REFERENCES Nasabah(account_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)