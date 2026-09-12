-- Normaliza los estados académicos históricos sin modificar migraciones aplicadas.
UPDATE resultado_curso
SET estado_aprobacion = CASE
    WHEN UPPER(TRIM(estado_aprobacion)) IN ('A', 'APROBADO') THEN 'APROBADO'
    WHEN UPPER(TRIM(estado_aprobacion)) IN ('D', 'DESAPROBADO', 'DESAPROBADA') THEN 'DESAPROBADO'
    ELSE estado_aprobacion
END
WHERE estado_aprobacion IS NOT NULL;

COMMIT;
