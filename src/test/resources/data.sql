CREATE SCHEMA IF NOT EXISTS testdb;

USE testdb;

DROP TABLE IF EXISTS cuentas;
CREATE TABLE IF NOT EXISTS cuentas (
    id bigint(20) NOT NULL,
    cliente varchar(50) NOT NULL,
    cliente_id bigint(20) NOT NULL,
    estado bit(1) NOT NULL,
    numero bigint(20) NOT NULL,
    saldo_disponible float NOT NULL,
    saldo_inicial float NOT NULL,
    tipo varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK7sa6xm9anjkmpftqttuyhko56 (numero),
    KEY index_client (cliente_id),
    KEY index_numero (numero)
    );

INSERT INTO cuentas (id, cliente, cliente_id, estado, numero, saldo_disponible, saldo_inicial, tipo) VALUES  (361, 'Alejandro', 1, '1', 1315133, 2500, 2500, 'ahorro');
INSERT INTO cuentas (id, cliente, cliente_id, estado, numero, saldo_disponible, saldo_inicial, tipo) VALUES  (362, 'Alejandro', 1, '1', 1315134, 2500, 2500, 'corriente');
INSERT INTO cuentas (id, cliente, cliente_id, estado, numero, saldo_disponible, saldo_inicial, tipo) VALUES  (363, 'Alejandro', 1, '1', 1315135, 2500, 2500, 'corriente');
INSERT INTO cuentas (id, cliente, cliente_id, estado, numero, saldo_disponible, saldo_inicial, tipo) VALUES  (364, 'Alejandro', 1, '1', 1315136, 2500, 2500, 'ahorro');


DROP TABLE IF EXISTS movimientos;
CREATE TABLE IF NOT EXISTS movimientos (
    id bigint(20) NOT NULL,
    fecha datetime(6) NOT NULL,
    numero_cuenta bigint(20) NOT NULL,
    saldo float NOT NULL,
    tipo varchar(50) NOT NULL,
    valor float NOT NULL,
    cuenta_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    KEY index_numero2 (numero_cuenta),
    KEY FK4moe88hxuohcysas5h70mdc09 (cuenta_id)
    );

INSERT INTO movimientos (id, fecha, numero_cuenta, saldo, tipo, valor, cuenta_id) VALUES
                                                                                                      (1000, '2024-12-12 12:00:00.000000', 1315133, 3075, 'retiro', 575, 1),
                                                                                                      (2000, '2024-12-12 12:00:00.000000', 1315133, 2500, 'retiro', -575, 1),
                                                                                                      (300, '2024-12-12 12:00:00.000000', 1315133, 1925, 'retiro', -575, 1),
                                                                                                      (4000, '2024-12-12 12:00:00.000000', 1315133, 1350, 'retiro', -575, 1),
                                                                                                      (5000, '2024-12-12 12:00:00.000000', 1315133, 775, 'retiro', -575, 1),
                                                                                                      (6000, '2024-12-12 12:00:00.000000', 1315133, 200, 'retiro', -575, 1);
