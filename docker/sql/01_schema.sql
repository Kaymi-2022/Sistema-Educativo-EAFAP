-- ==============================
-- CREAR USUARIO DE LA APLICACIÓN
-- ==============================

CREATE USER eafap_user IDENTIFIED BY eafap123
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA UNLIMITED ON users;

-- ==============================
-- ASIGNAR PRIVILEGIOS
-- ==============================

GRANT CONNECT, RESOURCE TO eafap_user;
GRANT CREATE TABLE TO eafap_user;
GRANT CREATE SEQUENCE TO eafap_user;
GRANT CREATE VIEW TO eafap_user;