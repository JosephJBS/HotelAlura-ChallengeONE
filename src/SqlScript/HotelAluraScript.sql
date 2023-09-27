/*
 * CREDENCIALES PARA LOGIN DE LA APLICACION
 * admin -> password: admin 
 * jbs -> pasword: 12456
 * */

create database hotel_alura;

use hotel_alura;


CREATE TABLE `huesped` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `nacionalidad` varchar(30) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `docIdentidad` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_login` (
  `username` varchar(20) NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rol` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ;


CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `fechaEntrada` datetime NOT NULL,
  `fechaSalida` datetime NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `formaPago` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ;


INSERT INTO `huesped` (`nombre`, `apellido`, `fechaNacimiento`, `nacionalidad`, `telefono`, `docIdentidad`)
VALUES
  ('Juan', 'Perez', '1990-05-15', 'francés-francesa', '1234567890', '88154886'),
  ('María', 'González', '1985-12-03', 'dominicano-dominicana', '9876543210', '88844886'),
  ('Luis', 'Martínez', '1992-08-20', 'peruano-peruana', '5678901234', '88156986'),
  ('Ana', 'López', '1980-04-10', 'salvadoreño-salvadoreña', '6543210987', '98745886'),
  ('Pedro', 'Ramírez', '1988-09-28', 'chileno-chilena', '7890123456', '95364886');

 
 INSERT INTO `reserva` (`idCliente`, `fechaEntrada`, `fechaSalida`, `valor`, `formaPago`)
VALUES
  (1, '2023-10-01', '2023-10-05', 480, 'Tarjeta de Crédito'),
  (2, '2023-11-15', '2023-11-20', 600, 'Tarjeta de Débito'),
  (3, '2023-12-10', '2023-12-15', 600, 'Dinero en Efectivo'),
  (4, '2023-12-20', '2023-12-25', 600, 'Tarjeta de Débito'),
  (5, '2023-11-01', '2023-11-05', 480, 'Dinero en Efectivo');

 
INSERT INTO `user_login` (`username`, `password`, `rol`)
VALUES
  ('admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'ADMINISTRADOR'), 
  ('jbs', 'WZRHGrsBESr8wYFZ9sx0tPURuZgG2lmzyvWpwXPKz8U=', 'LOCAL_USER');

select * from huesped h ;
select * from reserva r ;
select * from user_login ul;




