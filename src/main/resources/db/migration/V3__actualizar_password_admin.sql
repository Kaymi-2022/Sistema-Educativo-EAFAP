-- Actualiza únicamente el password inválido del usuario ADMIN inicial.
-- El valor almacenado es un hash BCrypt; la contraseña original no se persiste.
UPDATE usuario
SET password = '$2a$10$UJeLP1ZPiBLl.eoeZMY58eWvGeQ6OyA72I2sLPlaMxs8OhUK8l7Ge'
WHERE username = 'admin'
  AND password = '$2a$demo';

COMMIT;
