CREATE DATABASE MisionMascota

USE MisionMascota

CREATE TABLE Roles(
	idRol int primary key,
	descripcion varchar(20)
)

CREATE TABLE Usuarios(
	usuario varchar(12) primary key,
	clave varchar(16) not null,
	nombres varchar(30),
	apellidos varchar(30),
	dni char(8) unique not null,
	idRol int not null,
	FOREIGN KEY (idRol) REFERENCES Roles(idRol)
)

CREATE TABLE Estados(
	idEstado int primary key,
	descripcion varchar(15)
)

CREATE TABLE Mascotas(
	idMascota int primary key identity(1,1),
	nombreMascota varchar(30),
	raza varchar(20),
	peso decimal,
	fechaNac date,
	imagenMascota varchar(100),
	idEstado int not null,
	FOREIGN KEY (idEstado) REFERENCES Estados(idEstado)
)

CREATE TABLE Adoptantes(
	idAdoptante int primary key identity(1,1),
	nombreAdoptante varchar(30) not null,
	apellidoAdoptante varchar(30) not null,
	dni char(8) unique not null,
	fechaNac date,
	correo varchar(50),
	telefono varchar(15),
	direccion varchar(50),
	propietario int not null,
	permiso int not null
)

CREATE TABLE Adopciones(
	idAdopcion int primary key identity(1,1),
	idMascota int not null,
	idAdoptante int not null,
	fecha date,
	FOREIGN KEY (idMascota) REFERENCES Mascotas(idMascota),
	FOREIGN KEY (idAdoptante) REFERENCES Adoptantes(idAdoptante)
)

INSERT INTO Roles(idRol, descripcion) VALUES(1, 'Administrador')
INSERT INTO Roles(idRol, descripcion) VALUES(2, 'Asistente')
SELECT * FROM Roles

INSERT INTO Usuarios(usuario, clave, nombres, apellidos, dni, idRol) VALUES('mdavila', '123456', 'Manuel', 'Davila', '89765432', 1)
INSERT INTO Usuarios(usuario, clave, nombres, apellidos, dni, idRol) VALUES('ltrujillo', '123456', 'Laura', 'Trujillo', '98765432', 1)
INSERT INTO Usuarios(usuario, clave, nombres, apellidos, dni, idRol) VALUES('rgomez', '123456', 'Richard', 'Gomez', '90897867', 2)
INSERT INTO Usuarios(usuario, clave, nombres, apellidos, dni, idRol) VALUES('alopez', '123456', 'Ana', 'Lopez', '87766554', 2)
SELECT * FROM Usuarios

INSERT INTO Estados(idEstado, descripcion) VALUES(1, 'Nuevo')
INSERT INTO Estados(idEstado, descripcion) VALUES(2, 'Disponible')
INSERT INTO Estados(idEstado, descripcion) VALUES(3, 'Adoptado')
SELECT * FROM Estados

INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Manchas', 'Beagle', 30, '2021-09-10', 1)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Ali', 'Husky', 32, '2021-10-05', 1)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Cuellicorto', 'Pug', 31, '2021-11-13', 1)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Beffa', 'Poodle', 25, '2021-01-15', 1)

INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('King', 'Pastor', 30, '2020-08-15', 2)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Queen', 'Chitzu', 30, '2020-12-15', 2)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Aria', 'Pitbull', 30, '2021-10-10', 2)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Caye', 'Galgo', 30, '2021-12-25', 2)

INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Arturo', 'Pastor', 50, '2019-10-10', 3)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Melón', 'Beagle', 35, '2020-10-10', 3)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Chispa', 'Chitzu', 29, '2019-10-10', 3)
INSERT INTO Mascotas(nombre, raza, peso, fechaNacimiento, idEstado) VALUES('Pelusa', 'Husky', 39, '2020-10-10', 3)
SELECT * FROM Mascotas

INSERT INTO Adoptantes(nombres, apellidos, dni, fechaNacimiento, correo, telefono, direccion, propietario, permiso) VALUES('Lucas', 'Donald', '11111111', '2000-01-01', 'lucas@correo.com', '989989989', 'Av. Calle principal', 0, 1)
INSERT INTO Adoptantes(nombres, apellidos, dni, fechaNacimiento, correo, telefono, direccion, propietario, permiso) VALUES('Juan', 'Caballero', '13131313', '2002-01-01', 'juan@correo.com', '979979979', 'Av. Calle gris', 0, 1)
INSERT INTO Adoptantes(nombres, apellidos, dni, fechaNacimiento, correo, telefono, direccion, propietario, permiso) VALUES('Luciana', 'Torres', '15151515', '2001-01-01', 'luciana@correo.com', '959959959', 'Av. Calle celeste', 1, 0)
INSERT INTO Adoptantes(nombres, apellidos, dni, fechaNacimiento, correo, telefono, direccion, propietario, permiso) VALUES('Pablo', 'Pelaez', '17171717', '1999-01-01', 'pablo@correo.com', '999888777', 'Av. Calle roja', 1, 0)
INSERT INTO Adoptantes(nombres, apellidos, dni, fechaNacimiento, correo, telefono, direccion, propietario, permiso) VALUES('Luis', 'Dominguez', '18181818', '1998-12-01', 'luis@correo.com', '919919919', 'Av. Calle azul', 1, 0)
INSERT INTO Adoptantes(nombres, apellidos, dni, fechaNacimiento, correo, telefono, direccion, propietario, permiso) VALUES('Luisa', 'Cardenas', '19191919', '1998-10-25', 'luisa@correo.com', '909909909', 'Av. Calle azul', 1, 1)
SELECT * FROM Adoptantes

INSERT INTO Adopciones(idMascota, idAdoptante, fecha) VALUES(9, 1, '2020-03-15')
INSERT INTO Adopciones(idMascota, idAdoptante, fecha) VALUES(10, 2, '2021-05-18')
INSERT INTO Adopciones(idMascota, idAdoptante, fecha) VALUES(11, 3, '2020-01-10')
INSERT INTO Adopciones(idMascota, idAdoptante, fecha) VALUES(12, 4, '2021-10-03')
SELECT * FROM Adopciones

--Procedimiento para imprimir reporte de mascotas según estado

CREATE PROCEDURE sp_mascotas_estado (@estado varchar(15))
as
SELECT mas.* FROM Mascotas mas
INNER JOIN Estados e
ON mas.idEstado = e.idEstado
WHERE e.descripcion = @estado

exec sp_mascotas_estado 'Adoptado'

--Procedimiento para imprimir adopciones según fecha de inicio y fecha fin

CREATE PROCEDURE sp_adopciones_fechas (@fechaInicio date, @fechaFin date)
as
SELECT adopc.idAdopcion, mas.nombreMascota, adopt.nombreAdoptante, adopc.fecha FROM Adopciones adopc
INNER JOIN Adoptantes adopt
ON adopc.idAdoptante = adopt.idAdoptante
INNER JOIN Mascotas mas
ON adopc.idMascota = mas.idMascota
WHERE adopc.fecha BETWEEN @fechaInicio AND @fechaFin

exec sp_adopciones_fechas '2020-01-01','2020-12-30'

--Funcion para calcular edad del adoptante
CREATE FUNCTION calcular_edad(@fecha date)
returns int
as
begin
	declare @edad int
	if (MONTH(@fecha) <= MONTH(GETDATE())) and (DAY(@fecha) <= DAY(GETDATE()))
		set @edad = YEAR(GETDATE()) - YEAR(@fecha)
	else
		set @edad = YEAR(GETDATE()) - YEAR(@fecha) - 1
	return @edad
end

--Trigger para validar que el adopante sea mayor de edad y sea propietario de una casa o tenga el permiso del dueño del departamento
CREATE TRIGGER t_validar_adoptante
ON Adoptantes
FOR INSERT
as
	Declare @fecha date = (SELECT fechaNac FROM inserted)
	if(dbo.calcular_edad(@fecha) < 18)
		begin
			rollback transaction
			print 'El adoptante no puede ser menor de edad'
		end
	else
		Declare @permiso int = (SELECT permiso FROM inserted)
		Declare @propietario int = (SELECT propietario FROM inserted)
		begin
		if(@permiso = 0) AND (@propietario = 0)
			begin
				rollback transaction
				print 'El adoptante debe ser propietario de una casa o tener permiso del dueño del departamento'
			end
		end
