CREATE TABLE station (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL 
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE bike (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(255) NOT NULL, 
    status ENUM('AVAILABLE', 'RENTED') NOT NULL DEFAULT 'AVAILABLE',
    station_id BIGINT,
    price_per_minute DECIMAL(10, 2) DEFAULT 0.00,
    CONSTRAINT fk_bike_station_id FOREIGN KEY (station_id) REFERENCES station(id) ON DELETE SET NULL
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE rental (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    bike_id BIGINT,
    station_id BIGINT NOT NULL, 
    rental_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    return_date TIMESTAMP NULL,
    rental_cost DECIMAL(10, 2) DEFAULT 0.00,
    status ENUM('ONGOING', 'COMPLETED', 'CANCELED') NOT NULL DEFAULT 'ONGOING',
    CONSTRAINT fk_rental_user_id FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL,
    CONSTRAINT fk_rental_bike_id FOREIGN KEY (bike_id) REFERENCES bike(id) ON DELETE SET NULL,
    CONSTRAINT fk_rental_station_id FOREIGN KEY (station_id) REFERENCES station(id) ON DELETE RESTRICT
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

