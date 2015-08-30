-- init sequences table
insert into sequences (name, value) 
	values ('category', 1000);
insert into sequences (name, value) 
	values ('product', 1000);

--init category table
insert into category (id, name, description) 
	values (1, 'electronic', 'electronic category');
insert into category (id, name, description) 
	values (2, 'computers', 'computers category');
insert into category (id, name, description) 
	values (3, 'household appliances', 'household appliances category');
insert into category (id, name, description) 
	values (4, 'clothes', 'clothes category');
insert into category (id, name, description) 
	values (5, 'sport', 'sport category');

-- init product table
-- category electronic
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(1, '2015-05-10 00:00:00', 'Apple iPhone 5S', 'Apple iPhone 5S description', 35990.00, 'Apple', null, 1);
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(2, '2013-06-12 00:00:00', 'Samsung Galaxy A5', 'Samsung Galaxy A5 description', 17500.00, 'Samsung', null, 1);
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(3, '2014-10-04 00:00:00', 'Canon EOS 1200D Kit', 'Canon EOS 1200D Kit description', 13500.00, 'Canon', null, 1);
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(4, '2014-11-06 00:00:00', 'Nikon D5200 Kit', 'Nikon D5200 Kit description', 29990.00, 'Nikon', null, 1);
-- category computers
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(5, '2014-09-05 00:00:00', 'Lenovo B50 70', 'Lenovo B50 70 description', 32506.00, 'Lenovo', null, 2);
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(6, '2014-04-03 00:00:00', 'ASUS X200MA', 'ASUS X200MA description', 25064.00, 'ASUS', null, 2);
insert into product (id , createDate, name, description, price, producer, imageUrl, category_id) 
	values(7, '2014-02-07 00:00:00', 'HP PAVILION 15-p100', 'HP PAVILION 15-p100 description', 36957.00, 'HP', null, 2);
