USE vehiclemanagement;
-- Fresh start every time you run this
DROP TABLE IF EXISTS notifications;

CREATE TABLE notifications (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    vehicle        VARCHAR(100) NOT NULL,
    customer_name  VARCHAR(100) NOT NULL,
    customer_email VARCHAR(100) NOT NULL,
    message        TEXT,
    submitted_at   DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO notifications (vehicle, customer_name, customer_email, message) VALUES
('Honda Civic 2024',        'Peter Parker',     'peter.parker@dailybugle.com',   'Hi, I am interested in this vehicle. Please contact me at your earliest convenience.'),
('BMW X5 2024',             'Miles Morales',    'miles.morales@brooklyn.com',    'Yo this car looks clean, would love to know more about it.'),
('Black Cadillac Eldorado', 'Peter B. Parker',  'noir@newyork1933.com',          'I need a car. Black. Do not contact me before noon.'),
('Ford F-150 2024',         'Peter Porker',     'peter.porker@marvel.com',       'HELLO I WOULD LIKE THIS TRUCK VERY MUCH IT IS BIG AND SO AM I WELL NOT REALLY BUT ANYWAY PLEASE CALL ME!!'),
('Porsche 911 2024',        'Gwen Stacy',       'gwen.stacy@ghostspider.com',    'Interested in the Porsche. Looking for something with good handling.'),
('NIO ET7 2024',            'Peni Parker',      'peni.parker@sp//dr.com',        'The NIO ET7 autonomous driving capabilities are impressive. I would like a full technical breakdown before purchase.'),
('Jaguar F-Type 2024',      'Hobie Brown',      'hobie.brown@spiderpunk.com',    'Dont really care about the specs just want to know if it comes in anything other than boring colours innit.'),
('BMW X5 2024',             'Pavitr Prabhakar', 'pavitr.prabhakar@mumbai.com',   'Namaste, I am very interested in this vehicle and would appreciate hearing from you soon.');

SELECT * FROM notifications;