INSERT INTO room (number, type, price_per_night, available) VALUES ('101', 'Single', 89.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('102', 'Single', 95.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('103', 'Single', 99.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('104', 'Double', 129.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('105', 'Double', 139.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('106', 'Double', 149.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('201', 'Suite', 229.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('202', 'Suite', 249.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('203', 'Suite', 269.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('301', 'Deluxe', 179.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('302', 'Deluxe', 189.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('303', 'Deluxe', 199.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('401', 'Executive', 219.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('402', 'Executive', 229.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('403', 'Executive', 239.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('501', 'Family', 199.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('502', 'Family', 209.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('601', 'Penthouse', 399.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('602', 'Penthouse', 429.00, b'1')
ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('700', 'Accessible', 129.00, b'1')
ON DUPLICATE KEY UPDATE number = number;

-- Extra rooms up to 100
INSERT INTO room (number, type, price_per_night, available) VALUES ('701', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('702', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('703', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('704', 'Double', 139.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('705', 'Double', 139.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('706', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('707', 'Suite', 269.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('708', 'Suite', 279.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('709', 'Suite', 289.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('710', 'Deluxe', 209.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('711', 'Deluxe', 209.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('712', 'Deluxe', 219.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('713', 'Executive', 249.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('714', 'Executive', 259.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('715', 'Executive', 269.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('716', 'Family', 219.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('717', 'Family', 229.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('718', 'Family', 239.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('719', 'Penthouse', 449.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('720', 'Penthouse', 479.00, b'1') ON DUPLICATE KEY UPDATE number = number;
-- Bulk fill more standard rooms
INSERT INTO room (number, type, price_per_night, available) VALUES ('721', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('722', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('723', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('724', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('725', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('726', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('727', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('728', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('729', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('730', 'Single', 99.00, b'1') ON DUPLICATE KEY UPDATE number = number;
-- up to 800 series
INSERT INTO room (number, type, price_per_night, available) VALUES ('731', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('732', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('733', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('734', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('735', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('736', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('737', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('738', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('739', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;
INSERT INTO room (number, type, price_per_night, available) VALUES ('740', 'Double', 149.00, b'1') ON DUPLICATE KEY UPDATE number = number;

