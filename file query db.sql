
create database newdb;
use newdb;

drop table if exists veritication_token;
select * from local_user;

select* from veritication_token;

UPDATE newdb.local_user
SET email_verified = false
WHERE id = 3;

select * from veritication_token;

SET @userId1 = 1;
-- Replace the id here with the second user id you want to have ownership of the orders.
SET @userId2 = 2;

DELETE FROM web_order_quantities;
DELETE FROM web_order;
DELETE FROM inventory;
DELETE FROM product;
DELETE FROM address;

INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #1', 'Product one short description.', 'This is a very long description of product #1.', 5.50);
INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #2', 'Product two short description.', 'This is a very long description of product #2.', 10.56);
INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #3', 'Product three short description.', 'This is a very long description of product #3.', 2.74);
INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #4', 'Product four short description.', 'This is a very long description of product #4.', 15.69);
INSERT INTO product (name, short_description, long_description, price) VALUES ('Product #5', 'Product five short description.', 'This is a very long description of product #5.', 42.59);


set @product1=0;
set @product2=0;
set @product3=0;
set @product4=0;
set @product5=0;


SELECT id into @product1 FROM product WHERE name = 'Product #1';
SELECT id into @product2 FROM product WHERE name = 'Product #2';
SELECT id into @product3 FROM product WHERE name = 'Product #3';
SELECT id into @product4 FROM product WHERE name = 'Product #4';
SELECT id into @product5 FROM product WHERE name = 'Product #5';



INSERT INTO inventory (id_product, quantities) VALUES (@product1, 5);
INSERT INTO inventory (id_product, quantities) VALUES (@product2, 8);
INSERT INTO inventory (id_product, quantities) VALUES (@product3, 12);
INSERT INTO inventory (id_product, quantities) VALUES (@product4, 73);
INSERT INTO inventory (id_product, quantities) VALUES (@product5, 2);


INSERT INTO address (address_line_1,address_line_2, user_id) VALUES ('123 Tester Hill',"QL1A" ,@userId1);
INSERT INTO address (address_line_1,address_line_2, user_id) VALUES ('312 Spring Boot',"QL1A" ,@userId2);


Set @address1 =0;
Set @address2 =0;

SELECT id into @address1 FROM address WHERE user_id = @userId1 ORDER BY id DESC limit 1;
SELECT id into @address2 FROM address WHERE user_id = @userId2 ORDER BY id DESC limit 1;



INSERT INTO web_order (address_id, local_user_id) VALUES (@address1, @userId1);
INSERT INTO web_order (address_id, local_user_id) VALUES (@address1, @userId1);
INSERT INTO web_order (address_id, local_user_id) VALUES (@address1, @userId1);
INSERT INTO web_order (address_id, local_user_id) VALUES (@address2, @userId2);
INSERT INTO web_order (address_id, local_user_id) VALUES (@address2, @userId2);



Set @order1 =0;
Set @order2 =0;
Set @order3 =0;
Set @order4 =0;
Set @order5 =0;


SELECT id into @order1 FROM web_order WHERE address_id = @address1 AND local_user_id = @userId1 ORDER BY id DESC limit 1;
SELECT id into @order2 FROM web_order WHERE address_id = @address1 AND local_user_id = @userId1 ORDER BY id DESC limit 1;
SELECT id into @order3 FROM web_order WHERE address_id = @address1 AND local_user_id = @userId1 ORDER BY id DESC limit 1;
SELECT id into @order4 FROM web_order WHERE address_id = @address2 AND local_user_id = @userId2 ORDER BY id DESC limit 1;
SELECT id into @order5 FROM web_order WHERE address_id = @address2 AND local_user_id = @userId2 ORDER BY id DESC limit 1;



INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order1, @product1, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order1, @product2, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order2, @product3, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order2, @product2, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order2, @product5, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order3, @product3, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order4, @product4, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order4, @product2, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order5, @product3, 5);
INSERT INTO web_order_quantites (web_order, product_id, quantity) VALUES (@order5, @product1, 5);

select * from web_order_quantites