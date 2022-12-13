DROP DATABASE IF EXISTS DATA_CINEMA;
CREATE DATABASE DATA_CINEMA; 
USE DATA_CINEMA;

DROP TABLE IF EXISTS projectData;
CREATE TABLE projectData (
	Item		int not null AUTO_INCREMENT,
	Theatre			varchar(100),
	Movie			varchar(100),
	dateM			varchar(25),
	dayM			varchar(25),
	timeM			varchar(25),
    Seats			varchar(25),
    memberOnly      boolean,
    primary key (Item)
);

INSERT INTO projectData (Theatre, Movie, dateM, dayM, timeM, Seats, memberOnly)
VALUES
('Crowfoot Cinema', 'Wakanda Forever', '23/11/2022', 'Tuesday', '2300', '10', 1),
('Crowfoot Cinema', 'Smile', '24/11/2022', 'Tuesday', '2359',  '10', 0),
('MarketMall Cinema', 'Black Adam', '25/11/2022', 'Wednesday', '1900',  '10', 0),
('Sunridge Cinema', 'Wakanda Forever', '24/11/2022', 'Wednesday', '1100', '10', 1),
('Sunridge Cinema', 'Smile', '24/11/2022', 'Wednesday', '0500', '10', 0),
('Crowfoot Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0530', '10', 1),
('Crowfoot Cinema', 'Smile', '23/11/2022', 'Wednesday', '0545', '10', 0),
('MarketMall Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0550', '10', 1),
('Sunridge Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0525', '10', 1),
('MarketMall Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0520', '10', 1),
('Crowfoot Cinema', 'Black Adam', '23/11/2022', 'Wednesday', '0521', '10', 0),
('MarketMall Cinema', 'Black Adam', '23/11/2022', 'Wednesday', '0522', '10', 0),
('Crowfoot Cinema', 'Black Adam', '23/11/2022', 'Wednesday', '0534', '10', 0),
('Sunridge Cinema', 'Black Adam', '23/11/2022', 'Wednesday', '0556', '10', 0),
('MarketMall Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0517', '10', 1),
('Crowfoot Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0538', '10', 1),
('Sunridge Cinema', 'Black Adam', '23/11/2022', 'Wednesday', '0534', '10', 0),
('Crowfoot Cinema', 'Smile', '23/11/2022', 'Wednesday', '0542', '10', 0),
('MarketMall Cinema', 'Black Adam', '23/11/2022', 'Wednesday', '0543', '10', 0),
('MarketMall Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0541', '10', 1),
('Crowfoot Cinema', 'Wakanda Forever', '23/11/2022', 'Wednesday', '0529', '10', 1),
('Crowfoot Cinema', 'Smile', '23/11/2022', 'Wednesday', '0519', '10', 0);



CREATE TABLE userData (
	Item		int not null AUTO_INCREMENT,
	Email			varchar(100),
	Password		varchar(100),
	CNumber			varchar(100),
    Cvv				varchar(100),
    Expiry			varchar(100),
    FeePaid         boolean,
    primary key (Item)
);

INSERT INTO userData (Email, Password, CNumber, Cvv, Expiry, FeePaid)
VALUES
('shah@gmail.com', 'shah', '350519263647', '333', '06/25', 0),
('muteeba@gmail.com', 'muteeba', '35054564658', '322', '12/24', 1),
('michi@gmail.com', 'michi', '350544948', '586', '11/23', 1),
('samira@gmail.com', 'samira', '35051654899', '944', '09/25', 1);


