-- CLIENTES
INSERT INTO PERSONA (id, dtype, nombre, ap_paterno, ap_materno, fecha_nacimiento, direccion, carnet, email, telefono, ocupacion, estado) VALUES (1, 'Cliente', 'Ronald', 'Guarachi', 'Enriquez', '1995-03-18', 'DireccionEjemplo', 9086429, 'aligatorbol@gmail.com', 79145207, 'Informatico', 'ACTIVO');
INSERT INTO PERSONA (id, dtype, nombre, ap_paterno, ap_materno, fecha_nacimiento, direccion, carnet, email, telefono, ocupacion, estado) VALUES (2, 'Cliente', 'Sergio', 'Paucara', 'Mamani', '1993-03-18', 'DireccionEjemplo', 1234567, 'raguarachi@gmail.com', 23234525, 'Abogado', 'ACTIVO');
INSERT INTO PERSONA (id, dtype, nombre, ap_paterno, ap_materno, fecha_nacimiento, direccion, carnet, email, telefono, ocupacion, estado) VALUES (3, 'Cliente', 'Claudia', 'Yupanqui', 'Rojas', '1992-03-18', 'DireccionEjemplo', 1234567, 'cyupanqui@gmail.com', 23234525, 'Arquitecto', 'BLOQUEADO');
-- PERSONAS
INSERT INTO PERSONA (id, dtype, nombre, ap_paterno, ap_materno, fecha_nacimiento, direccion, carnet) VALUES (11, 'Persona', 'Ruben', 'Hernandez', 'Torrez', '1992-03-18', 'DireccionEjemplo', 2324535);
INSERT INTO PERSONA (id, dtype, nombre, ap_paterno, ap_materno, fecha_nacimiento, direccion, carnet) VALUES (12, 'Persona', 'Maria', 'Magdalena', 'Fernandez', '1991-03-18', 'DireccionEjemplo', 1646344);
INSERT INTO PERSONA (id, dtype, nombre, ap_paterno, ap_materno, fecha_nacimiento, direccion, carnet) VALUES (13, 'Persona', 'Pablo', 'Escobar', 'Escobar', '1990-03-18', 'DireccionEjemplo', 1646344);
-- REFERENCIAS
INSERT INTO REFERENCIA (id, eliminado, motivo_eliminado, cliente_id, persona_referencia_id) VALUES (21, false, 'Sin eliminar', 1, 11);
INSERT INTO REFERENCIA (id, eliminado, motivo_eliminado, cliente_id, persona_referencia_id) VALUES (22, false, 'Sin eliminar', 2, 12);
INSERT INTO REFERENCIA (id, eliminado, motivo_eliminado, cliente_id, persona_referencia_id) VALUES (23, false, 'Sin eliminar', 2, 13);