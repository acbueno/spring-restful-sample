-- Estações
INSERT INTO station (name) VALUES ('Station A');
SET @station_a_id = LAST_INSERT_ID();
INSERT INTO station (name) VALUES ('Station B');
SET @station_b_id = LAST_INSERT_ID();

-- Usuários
INSERT INTO user (name, email) VALUES 
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com');

-- Bicicletas
INSERT INTO bike (model, status, price_per_minute ,station_id) VALUES
('Mountain Bike', 'AVAILABLE', 12.0 , @station_a_id),
('Road Bike', 'AVAILABLE', 13.0 , @station_a_id),
('Eletric Bike', 'AVAILABLE', 14.0 , @station_b_id);


