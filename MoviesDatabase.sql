use master
GO

drop database "DataMovies"
GO

create database "DataMovies"
GO

use "DataMovies"
GO

create table Movie
(
	IDMovie int primary key identity,
	Name nvarchar(max) not null,
	Media nvarchar(100) not null,
	DurationInMinutes int not null,
	Director nvarchar(100) not null, 
	LeadingActor nvarchar(100) not null,
	Genre nvarchar(100) not null,
	ShortDescription nvarchar(max) null
)

create table Member
(
	IDMember int primary key identity,
	Name nvarchar(100) not null,
	PermanentAddress nvarchar(100) not null,
	Telephone nvarchar(100) not null
)

create table Loan
(
	IDLoan int primary key identity,
	MovieID int,
	MemberID int, 
	DateLoan date not null,
	DateBack date not null,

	CONSTRAINT FK_LoanMovie FOREIGN KEY(MovieID) REFERENCES Movie(IDMovie),
	CONSTRAINT FK_LoanMember FOREIGN KEY(MemberID) REFERENCES Member(IDMember)
)

create table Overdue
(
	IDOverdue int primary key identity,
	MemberID int,
	MovieID int,
	ChargedOverdue int not null,

	CONSTRAINT FK_OverdueMember FOREIGN KEY(MemberID) REFERENCES Member(IDMember),
	CONSTRAINT FK_OverdueMovie FOREIGN KEY(MovieID) REFERENCES Movie(IDMovie)
)

create proc InsertMovie
	@NameMovie nvarchar(100),
	@Medij nvarchar(100),
	@DurInMinutes int,
	@DirectorMovie nvarchar(100),
	@LeadingActorMovie nvarchar(100),
	@Zanr nvarchar(100),
	@ShortDescr nvarchar(max)
AS
insert into Movie(Name, Media, DurationInMinutes, Director, LeadingActor, Genre, ShortDescription)
values (@NameMovie, @Medij, @DurInMinutes, @DirectorMovie, @LeadingActorMovie, @Zanr, @ShortDescr)
GO

exec InsertMovie 'Gas do daske 1', 'VHS', 98, 'Brett Ratner', 'Jackie Chan', 'Akcijski', 'Spašavanje kæeri kineskog konzultanta'
exec InsertMovie 'Gas do daske 2', 'VHS', 90, 'Brett Ratner', 'Jackie Chan', 'Akcijski', 'Odmor u Hong Kongu postane uplitanje u krivotvorenu prijevaru novca'
exec InsertMovie 'Gas do daske 3', 'VHS', 91, 'Brett Ratner', 'Jackie Chan', 'Akcijski', 'Spašavanje francuske žene koja poznaje tajne voðe trijade'
exec InsertMovie 'Gravitacija', 'DVD', 91, 'Alfonso Cuaron', 'Sandra Bullock', 'SF', 'Dva astronauta rade zajedno kako bi preživjeli nakon nesreæe koja ih ostavlja nasukanom u prostoru'
exec InsertMovie 'Social Network', 'DVD', 120, 'David Fincher', 'Jesse Eisenberg', 'Biografski', 'Dvojica braæe optužuju Zuckerberga da je ukrao njihovu ideju u stvaranju danas popularnog Facebooka'
GO


create proc GetMovies 
AS
select * from Movie
GO

exec GetMovies
GO

create proc UpdateMovie 
	@AjdiMovie int, 
	@NameMovie nvarchar(100),
	@Medij nvarchar(100),
	@DurInMinutes int,
	@DirectorMovie nvarchar(100),
	@LeadingActorMovie nvarchar(100),
	@Genre nvarchar(100),
	@ShortDescr nvarchar(max)
AS
update Movie
set Name = @NameMovie,
	Media = @Medij,
	DurationInMinutes = @DurInMinutes,
	Director = @DirectorMovie,
	LeadingActor = @LeadingActorMovie,
	Genre = @Genre,
	ShortDescription = @ShortDescr

where IDMovie = @AjdiMovie
GO
	
exec UpdateMovie 4,'Gravitacija', 'DVD', 93, 'Alfonso Cuaron', 'Sandra Bullock', 'SF', 'Dva astronauta rade zajedno kako bi preživjeli nakon nesreæe koja ih ostavlja nasukanom u prostoru'
GO

exec GetMovies
GO

create proc InsertMember 
	@NameMember nvarchar(100),
	@PAddress nvarchar(100),
	@Tel nvarchar(100)
AS 
insert into Member(Name, PermanentAddress, Telephone)
values (@NameMember, @PAddress, @Tel)
GO

exec InsertMember 'Pero Periæ', 'Ilica 242', '5513233'
exec InsertMember 'Maja Majiæ', 'Ilica 145', '2325666'
exec InsertMember 'Marko Mariæ', 'Horvaæanska 5', '798984'
exec InsertMember 'Lea Leiæ', 'Savska 123', '4533465'
GO

create proc GetMembers
AS
select * from Member
GO

exec GetMembers
GO

create proc UpdateMember
	@AjdiMember int,
	@NameMember nvarchar(100),
	@PAddress nvarchar(100),
	@Tel nvarchar(100)
AS
update Member
set Name = @NameMember,
	PermanentAddress = @PAddress,
	Telephone = @Tel

where IDMember = @AjdiMember
GO

exec UpdateMember 3, 'Ela Elic', 'Horvaæanska 5', '6532136'
GO

exec GetMembers
GO

create proc InsertLoan
	@MovieAjdi int, 
	@MemberAjdi int, 
	@DejtLoan date,
	@DejtBack date
AS
insert into Loan (MovieID, MemberID, DateLoan, DateBack)
values (@MovieAjdi, @MemberAjdi, @DejtLoan, @DejtBack)
GO

exec InsertLoan 4, 3, '20170107', '20170110'
exec InsertLoan 1, 4, '20180527', '20180601'
exec InsertLoan 4, 3, '20170315', '20170322'
exec InsertLoan 4, 3, '20171221', '20170103'
exec InsertLoan 4, 3, '20180423', '20180501'
GO

create proc GetLoans
AS
select * from Loan
GO

exec GetLoans
GO

create proc UpdateLoan
	@AjdiLoan int,
	@MovieAjdi int, 
	@MemberAjdi int, 
	@DejtLoan date,
	@DejtBack date
AS
update Loan
set MovieID = @MovieAjdi,
	MemberID = @MemberAjdi,
	DateLoan = @DejtLoan,
	DateBack = @DejtBack

where IDLoan = @AjdiLoan
GO

exec UpdateLoan 4, 2, 1, '20171221', '20170103'
exec UpdateLoan 3, 4, 2, '20170315', '20170322'
exec UpdateLoan 5, 1, 4, '20180423', '20180501'
GO

exec GetLoans
GO

alter proc InsertOverdue
	@MemberAjdi int,
	@MovieAjdi int,
	@Charged int
AS
insert into Overdue (MemberID, MovieID, ChargedOverdue)
values (@MemberAjdi, @MovieAjdi, @Charged)
GO

exec InsertLoan 5,4,'20170220','20170722'
GO

exec InsertOverdue 4, 5, 1000
GO

create proc GetOverdues
AS
select * from Overdue
GO

exec GetOverdues
GO

alter proc UpdateOverdue
	@OverdueAjdi int,
	@MemberAjdi int,
	@MovieAjdi int,
	@Charged int
AS
update Overdue
set MemberID = @MemberAjdi,
	MovieID = @MovieAjdi,
	ChargedOverdue = @Charged

where IDOverdue = @OverdueAjdi
GO

exec UpdateOverdue 1,4,5, 1020.35
GO

exec GetOverdues
GO

create proc GetNameMovie 
AS
select m.Name
from Movie as m
go

exec GetNameMovie
go

select Name, Media, DurationInMinutes, Director, LeadingActor, Genre
from Movie
go

exec GetMovies
GO






