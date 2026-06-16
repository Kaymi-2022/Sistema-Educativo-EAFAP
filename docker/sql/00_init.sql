-- 00_init.sql - Script maestro
-- Ejecuta todos los scripts en orden

PROMPT ========================================
PROMPT Iniciando configuración de base de datos
PROMPT ========================================

-- Ejecutar scripts en orden
@01_schema.sql
@02_tables.sql
@03_data.sql

PROMPT ========================================
PROMPT Configuración completada exitosamente
PROMPT ========================================