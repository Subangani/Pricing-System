DROP TABLE IF EXISTS product;
CREATE TABLE product(id serial PRIMARY KEY, product_name VARCHAR(255), carton_capacity integer,carton_price integer );

insert into product (id,product_name, carton_capacity, carton_price) values (1,'Penguin-ears',20,175);
insert into product (id,product_name, carton_capacity, carton_price) values (2,'Horseshoe',5,825);