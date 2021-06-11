-- MySQL Script generated by MySQL Workbench
-- Sun Dec 27 23:55:26 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='';

-- -----------------------------------------------------
-- Schema venditor
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `venditor` ;

-- -----------------------------------------------------
-- Schema venditor
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `venditor` DEFAULT CHARACTER SET utf8mb4 ;
USE `venditor` ;

-- -----------------------------------------------------
-- Table `venditor`.`estado_caja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`estado_caja` (
                                                        `Id` INT(11) NULL AUTO_INCREMENT,
                                                        `Fecha` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
                                                        `Estado` VARCHAR(45) NOT NULL,
                                                        `Usuario` VARCHAR(45) NOT NULL,
                                                        PRIMARY KEY (`Id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `venditor`.`articulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`articulos` (
  `Id` VARCHAR(10) NOT NULL,
  `Fecha` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  `Categoria` VARCHAR(45) NOT NULL,
  `Subcategoria` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(250) NOT NULL,
  `Peso` DOUBLE NULL DEFAULT NULL,
  `Valor` INT(11) NOT NULL,
  `Estado` VARCHAR(45) NOT NULL,
  `Usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `venditor`.`caja`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`caja` (
  `Id` INT(11) NOT NULL AUTO_INCREMENT,
  `Fecha` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  `Descripcion` VARCHAR(45) NOT NULL,
  `Tipo` VARCHAR(45) NOT NULL,
  `Ingreso` FLOAT NULL,
  `Egreso` FLOAT NULL,
  `Utilidad` FLOAT NULL,
  `Total` FLOAT NOT NULL,
  `Usuario` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;
INSERT INTO Caja(descripcion,ingreso,egreso,utilidad,total,usuario)VALUES ('Inicio Caja','0','0','0','0','root');

-- -----------------------------------------------------
-- Table `venditor`.`clientes`
-- -----------------------------------------------------root
CREATE TABLE IF NOT EXISTS `venditor`.`clientes` (
  `Cedula` INT(15) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `Direccion` VARCHAR(45) NULL DEFAULT NULL,
  `Telefono1` VARCHAR(45) NOT NULL,
  `Telefono2` VARCHAR(45) NULL DEFAULT NULL,
  `Correo` VARCHAR(45) NULL DEFAULT NULL,
  `Perfil` VARCHAR(45) NULL DEFAULT 'Nuevo',
  `Huella` BLOB NULL DEFAULT NULL,
  `Foto` BLOB NULL DEFAULT NULL,
  `Fecha_registro` DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
  `Usuario` VARCHAR(45) NOT NULL,
  `Barrio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Cedula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `venditor`.`contratos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`contratos` (
  `Numero_contrato` VARCHAR(10) NOT NULL,
  `Cedula` INT(15) NOT NULL,
  `Articulo` VARCHAR(10) NOT NULL,
  `Sobreprecio_real` FLOAT NULL DEFAULT NULL,
  `Sobreprecio_cobrado` FLOAT NULL DEFAULT NULL,
  `Fecha_inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `Fecha_final` DATETIME NULL DEFAULT NULL,
  `Tiempo` VARCHAR(45) NULL DEFAULT NULL,
  `Valor` INT(15) NOT NULL,
  `Porcentaje` VARCHAR(45) NULL DEFAULT NULL,
  `Renovaciones` INT(10) NULL DEFAULT 0,
  `Estado` VARCHAR(45) NOT NULL DEFAULT 'Vigente',
  `Usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Numero_contrato`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `venditor`.`descuentos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`descuentos` (
  `Id` INT(11) NULL AUTO_INCREMENT,
  `Numero_contrato` VARCHAR(45) NOT NULL,
  `Precio_real` FLOAT NOT NULL,
  `Precio_cobrado` FLOAT NOT NULL,
  `Razon` VARCHAR(150) NOT NULL,
  `Usuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `venditor`.`registro_adicion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`registro_adicion` (
  `ID_ADICION` INT(11) NOT NULL AUTO_INCREMENT,
  `TABLA` VARCHAR(20) NOT NULL,
  `ID_VALOR_AÑADIDO` VARCHAR(20) NOT NULL,
  `FECHA` DATETIME NOT NULL,
  `USUARIO_OPERACION` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID_ADICION`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `venditor`.`registro_modificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`registro_modificacion` (
  `ID_REGISTRO_MOD` INT(11) NOT NULL AUTO_INCREMENT,
  `ID_MODIFICADO` VARCHAR(20) NOT NULL,
  `Fecha` DATETIME NOT NULL,
  `Usario_operacion` VARCHAR(20) NOT NULL,
  `Columna_modificada` VARCHAR(20) NOT NULL,
  `Valor_antiguo` VARCHAR(20) NOT NULL,
  `Valor_nuevo` VARCHAR(20) NOT NULL,
  `TABLA_MODIFICADA` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID_REGISTRO_MOD`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `venditor`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`usuario` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `cedula` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`))

ENGINE = InnoDB;
USE `venditor`;
DELIMITER $$
INSERT INTO usuario(username, password, rol)VALUES ('root','','admin');


-- -----------------------------------------------------
-- Table `venditor`.`impresion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `venditor`.`impresion` (
                                                      `Elemento` VARCHAR(45) NOT NULL,
                                                      `X` INT(11) NOT NULL,
                                                      `Y` INT(11) NOT NULL,
                                                      `Documento` VARCHAR(45) NOT NULL,
                                                      PRIMARY KEY (`Elemento`))

ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- Valores por defecto para el formato de impresion
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Fecha inicio',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Fecha final',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Numero contrato',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Nombre cliente',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Cedula cliente',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Direccion cliente',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Barrio cliente',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Telefono cliente',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Nombre vendedor',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Cedula vendedor',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Descripcion articulo',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Peso articulo',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Valor articulo',0,0,'Contrato');
INSERT INTO impresion(Elemento, X, Y, Documento)VALUES ('Sobreprecio articulo',0,0,'Contrato');

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`

TRIGGER `venditor`.`TRI_ARTICULOS_INSERT`
BEFORE INSERT ON `venditor`.`articulos`
FOR EACH ROW
BEGIN
    INSERT INTO
        registro_adicion (TABLA, ID_VALOR_AÑADIDO, FECHA, USUARIO_OPERACION)
    VALUES('articulos',new.Id,sysdate(),user());
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_CAJA_INSERT`
BEFORE INSERT ON `venditor`.`caja`
FOR EACH ROW
BEGIN
    INSERT INTO
        registro_adicion (TABLA, ID_VALOR_AÑADIDO, FECHA, USUARIO_OPERACION)
    VALUES('CAJA',new.Id,sysdate(),user());
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_CAJA_UPDATE`
BEFORE UPDATE ON `venditor`.`caja`
FOR EACH ROW
BEGIN
    IF NEW.id<>OLD.id THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'id',OLD.id,new.id,old.Id   );
    end if;
    IF NEW.fecha<>OLD.fecha THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'fecha',OLD.fecha,new.fecha,old.Id   );
    end if;
    IF NEW.descripcion<>OLD.descripcion THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'descripcion',OLD.descripcion,new.descripcion,old.Id   );
    end if;
    IF NEW.ingreso<>OLD.ingreso THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'ingreso',OLD.ingreso,new.ingreso,old.Id   );
    end if;
    IF NEW.egreso<>OLD.egreso THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'egreso',OLD.egreso,new.egreso,old.Id   );
    end if;
    IF NEW.utilidad<>OLD.utilidad THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'utilidad',OLD.utilidad,new.utilidad,old.Id   );
    end if;
    IF NEW.total<>OLD.total THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'total',OLD.total,new.total,old.Id   );
    end if;
    IF NEW.usuario<>OLD.usuario THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CAJA',sysdate(),user(),'usuario',OLD.usuario,new.usuario,old.Id   );
    end if;
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_CLIENTES_INSERT`
BEFORE INSERT ON `venditor`.`clientes`
FOR EACH ROW
BEGIN
    INSERT INTO
        registro_adicion (TABLA, ID_VALOR_AÑADIDO, FECHA, USUARIO_OPERACION)
    VALUES('CLIENTES',new.Cedula,sysdate(),user());
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_CLIENTES_UPDATE`
BEFORE UPDATE ON `venditor`.`clientes`
FOR EACH ROW
BEGIN
    IF NEW.Cedula<>OLD.CEDULA THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'cedula',OLD.Cedula,new.Cedula,old.Cedula   );
    end if;
    IF NEW.nombre<>OLD.nombre THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'nombre',OLD.nombre,new.nombre,old.Cedula   );
    end if;
    IF NEW.apellidos<>OLD.apellidos THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'apellidos',OLD.apellidos,new.apellidos,old.Cedula   );
    end if;
    IF NEW.direccion<>OLD.direccion THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'direccion',OLD.direccion,new.direccion,old.Cedula   );
    end if;
    IF NEW.telefono1<>OLD.telefono1 THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'telefono1',OLD.telefono1,new.telefono1,old.Cedula   );
    end if;
    IF NEW.telefono2<>OLD.telefono2 THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'telefono2',OLD.telefono2,new.telefono2,old.Cedula   );
    end if;
    IF NEW.correo<>OLD.correo THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'correo',OLD.correo,new.correo,old.Cedula   );
    end if;
    IF NEW.perfil<>OLD.perfil THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'perfil',OLD.perfil,new.perfil,old.Cedula   );
    end if;
    IF NEW.huella<>OLD.huella THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'huella',OLD.huella,new.huella,old.Cedula   );
    end if;
    IF NEW.foto<>OLD.foto THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'foto',OLD.foto,new.foto,old.Cedula   );
    end if;
    IF NEW.fecha_registro<>OLD.fecha_registro THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'fecha_registro',OLD.fecha_registro,new.fecha_registro,old.Cedula   );
    end if;
    IF NEW.usuario<>OLD.usuario THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'CLIENTES',sysdate(),user(),'usuario',OLD.usuario,new.usuario,old.Cedula   );
    end if;
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_CONTRATOS_INSERT`
BEFORE INSERT ON `venditor`.`contratos`
FOR EACH ROW
BEGIN
    INSERT INTO
        registro_adicion (TABLA, ID_VALOR_AÑADIDO, FECHA, USUARIO_OPERACION)
    VALUES('CONTRATOS',new.Numero_contrato,sysdate(),user());
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_DESCUENTOS_INSERT`
BEFORE INSERT ON `venditor`.`descuentos`
FOR EACH ROW
BEGIN
    INSERT INTO
        registro_adicion (TABLA, ID_VALOR_AÑADIDO, FECHA, USUARIO_OPERACION)
    VALUES('DESCUENTOS',new.Id,sysdate(),user());
end$$

USE `venditor`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `venditor`.`TRI_DESCUENTOS_UPDATE`
BEFORE UPDATE ON `venditor`.`descuentos`
FOR EACH ROW
BEGIN
    IF NEW.ID<>OLD.ID THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'DESCUENTOS',sysdate(),user(),'ID',
                                                                                                OLD.ID,new.ID,old.ID);
    end if;
    IF NEW.numero_contrato<>OLD.numero_contrato THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'DESCUENTOS',sysdate(),user(),'numero_contrato',
                                                                                                OLD.numero_contrato,new.numero_contrato,old.ID);
    end if;
    IF NEW.precio_real<>OLD.precio_real THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'DESCUENTOS',sysdate(),user(),'precio_real',
                                                                                                OLD.precio_real,new.precio_real,old.ID);
    end if;
    IF NEW.precio_cobrado<>OLD.precio_cobrado THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'DESCUENTOS',sysdate(),user(),'precio_cobrado',
                                                                                                OLD.precio_cobrado,new.precio_cobrado,old.ID);
    end if;
    IF NEW.razon<>OLD.razon THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'DESCUENTOS',sysdate(),user(),'razon',
                                                                                                OLD.razon,new.razon,old.ID);
    end if;
    IF NEW.usuario<>OLD.usuario THEN
        INSERT INTO registro_modificacion( TABLA_MODIFICADA, Fecha, Usario_operacion, Columna_modificada,
                                           Valor_antiguo, Valor_nuevo, ID_MODIFICADO)VALUES (
                                                                                                'DESCUENTOS',sysdate(),user(),'usuario',
                                                                                                OLD.usuario,new.usuario,old.ID);
    end if;
end$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
