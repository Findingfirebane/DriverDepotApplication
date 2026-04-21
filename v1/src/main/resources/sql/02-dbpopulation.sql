-- Active: 1768854464261@@127.0.0.1@3306@vehiclemanagement
-- inserts for vehicles 
INSERT INTO vehicles (make, model, year, mileage, msrp, stock, details) VALUES
('Toyota','Corolla',2020,30000,20000,5,'Reliable sedan'),
('Honda','Civic',2019,40000,19000,3,'Fuel efficient'),
('Ford','F-150',2021,20000,35000,2,'Pickup truck'),
('Chevrolet','Malibu',2018,50000,18000,4,'Comfortable ride'),
('Nissan','Altima',2020,35000,21000,3,'Smooth driving'),
('BMW','3 Series',2022,15000,42000,2,'Luxury sedan'),
('Audi','A4',2021,18000,41000,2,'Premium feel'),
('Mercedes','C-Class',2022,12000,45000,1,'Luxury performance'),
('Hyundai','Elantra',2021,25000,20000,6,'Affordable sedan'),
('Kia','Optima',2019,42000,19500,4,'Great value'),

('Toyota','Camry',2021,22000,24000,5,'Mid-size sedan'),
('Honda','Accord',2020,30000,25000,4,'Reliable and spacious'),
('Ford','Escape',2021,28000,26000,3,'Compact SUV'),
('Chevrolet','Equinox',2020,35000,27000,3,'Family SUV'),
('Nissan','Rogue',2021,27000,26000,4,'Comfortable SUV'),
('BMW','X3',2022,14000,48000,2,'Luxury SUV'),
('Audi','Q5',2021,16000,47000,2,'Premium SUV'),
('Mercedes','GLC',2022,13000,50000,1,'Luxury SUV'),
('Hyundai','Tucson',2021,24000,25000,5,'Modern SUV'),
('Kia','Sportage',2020,30000,24000,4,'Compact SUV'),

('Toyota','RAV4',2022,20000,28000,5,'Popular SUV'),
('Honda','CR-V',2021,22000,27000,4,'Reliable SUV'),
('Ford','Explorer',2020,40000,32000,3,'Large SUV'),
('Chevrolet','Tahoe',2021,25000,55000,2,'Full-size SUV'),
('Nissan','Pathfinder',2020,35000,33000,3,'Family SUV'),
('BMW','5 Series',2022,10000,55000,2,'Luxury sedan'),
('Audi','A6',2021,15000,53000,2,'Executive sedan'),
('Mercedes','E-Class',2022,12000,58000,1,'Premium sedan'),
('Hyundai','Sonata',2021,26000,23000,6,'Efficient sedan'),
('Kia','K5',2022,18000,24000,5,'Sporty sedan'),

('Toyota','Highlander',2021,28000,35000,3,'3-row SUV'),
('Honda','Pilot',2020,32000,34000,3,'Family SUV'),
('Ford','Edge',2021,27000,31000,4,'Mid SUV'),
('Chevrolet','Traverse',2020,35000,33000,3,'Spacious SUV'),
('Nissan','Murano',2021,29000,32000,4,'Comfort SUV'),
('BMW','X5',2022,12000,65000,2,'Luxury SUV'),
('Audi','Q7',2021,15000,67000,2,'Large SUV'),
('Mercedes','GLE',2022,11000,70000,1,'Premium SUV'),
('Hyundai','Santa Fe',2021,24000,28000,5,'Modern SUV'),
('Kia','Sorento',2020,30000,29000,4,'Family SUV'),

('Toyota','Yaris',2018,60000,15000,5,'Compact car'),
('Honda','Fit',2019,50000,16000,4,'Small hatchback'),
('Ford','Focus',2018,55000,17000,3,'Compact car'),
('Chevrolet','Cruze',2019,48000,16500,4,'Fuel efficient'),
('Nissan','Sentra',2020,35000,18000,5,'Compact sedan'),
('BMW','2 Series',2021,20000,38000,2,'Compact luxury'),
('Audi','A3',2021,22000,37000,2,'Small luxury'),
('Mercedes','CLA',2022,15000,39000,1,'Entry luxury'),
('Hyundai','Accent',2020,40000,17000,6,'Budget car'),
('Kia','Rio',2021,30000,17500,5,'Compact car');

SELECT * FROM vehicles;

-- role insert
INSERT INTO roles (role_name) VALUES
('STAFF'),
('USER');

SELECT * from roles;

-- users insert
INSERT INTO users (user_name, email, password, role_id) VALUES
('john','john@email.com','password123',1),
('sarah','sarah@email.com','secure456',2),
('mike','mike@email.com','mikepass789',2),
('emma','emma@email.com','emma12345',1),
('alex','alex@email.com','alexpass111',2),
('lisa','lisa@email.com','lisa22222',2),
('david','david@email.com','david3333',1),
('chris','chris@email.com','chris4444',2),
('peterparker','peter.parker@dailybugle.com','friendly123',2),
('milesmorales','miles.morales@brooklyn.com','exaggeratedswagger',2),
('peterb','noir@newyork1933.com','1933',2),
('peterporker','peter.porker@marvel.com','thatsallfolks',2),
('gwenstacy','gwen.stacy@ghostspider.com','gw3n!',2),
('peniparker','peni.parker@spdr.com','a1d2f!34_gvfQrhf',2),
('hobiebrown','hobie.brown@spiderpunk.com','anarchy!',2),
('pavitraprabhakar','pavitr.prabhakar@mumbai.com','Mumbattan',2),
('miguelohara','miguel.ohara@alchemax2099.com','eliminateanomalies',2);

SELECT * from users;

-- notifications
INSERT INTO notifications (vehicle, customer_name, customer_email, message) VALUES
('Honda Civic 2019',        'Peter Parker',     'peter.parker@dailybugle.com',   'Hi, I am interested in this vehicle. It seems practical and reliable which is important to me. Please contact me at your earliest convenience.'),
('BMW 3 Series 2022',       'Miles Morales',    'miles.morales@brooklyn.com',    'Yo this 3 Series looks clean. Been eyeing it for a while. What is the best price you can do?'),
('Mercedes C-Class 2022',   'Peter B. Parker',  'noir@newyork1933.com',          'I need this car. Dark colour only. Do not contact me before noon.'),
('Ford F-150 2021',         'Peter Porker',     'peter.porker@marvel.com',       'HELLO I WOULD LIKE THIS TRUCK VERY MUCH IT IS BIG AND SO AM I WELL NOT REALLY BUT ANYWAY PLEASE CALL ME!!'),
('Audi A4 2021',            'Gwen Stacy',       'gwen.stacy@ghostspider.com',    'Interested in the A4. Looking for something with sharp handling and a clean interior. Does it come in white?'),
('BMW X3 2022',             'Peni Parker',      'peni.parker@spdr.com',          'I would like the full technical specification sheet for the X3 before proceeding. Particularly interested in the onboard computing and personalization capabilities.'),
('Ford Focus 2018',         'Hobie Brown',      'hobie.brown@spiderpunk.com',    'Dont care about the specs. Does it run? Does it come in anything that is not boring? Let me know innit.'),
('Toyota Camry 2021',       'Pavitr Prabhakar', 'pavitr.prabhakar@mumbai.com',   'Namaste, I am very interested in the Camry. It is a well regarded vehicle and I would appreciate the opportunity to discuss further.'),
('BMW X5 2022',             'Miguel O Hara',    'miguel.ohara@alchemax2099.com', 'I need a vehicle capable of handling high performance conditions. The X5 specs are acceptable. Please advise on availability.');

-- vehicles that a user has saved to track
INSERT INTO user_saved_vehicles (user_id, vehicle_id) VALUES
(9,2),(9,11),
(10,6),(10,36),
(11,8),(11,28),
(12,3),(12,23),
(13,7),(13,17),
(14,16),(14,9),
(15,43),(15,44),
(16,11),(16,21),
(17,36),(17,38);