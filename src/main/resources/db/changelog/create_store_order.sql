ALTER TABLE `address` ADD COLUMN `store_id` INT NOT NULL;


CREATE TABLE `store` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  cnpj VARCHAR(50) UNIQUE NOT NULL,
  image BLOB,
  address_id BIGINT NOT NULL,
  stock INT NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (address_id) REFERENCES `address`(id)
);

ALTER TABLE `address` ADD CONSTRAINT `fk_store` FOREIGN KEY (`store_id`) REFERENCES `store`(`id`);


CREATE TABLE `category` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  PRIMARY KEY (id)
);

CREATE TABLE `product` (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  description TEXT,
  image BLOB,
  category_id INT NOT NULL,
  store_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES `category`(id),
  FOREIGN KEY (store_id) REFERENCES `store`(id)
);

CREATE TABLE `product_category` (
  product_id INT,
  category_id INT,
  PRIMARY KEY (product_id, category_id),
  FOREIGN KEY (product_id) REFERENCES `product`(id),
  FOREIGN KEY (category_id) REFERENCES `category`(id)
);

CREATE TABLE `order` (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  status VARCHAR(255) NOT NULL,
  creation_date DATETIME NOT NULL,
  payment_date DATETIME,
  delivery_address_id BIGINT NOT NULL,
  total_value DECIMAL(10,2) NOT NULL,
  shipping_cost DECIMAL(10,2),
  discount_coupon VARCHAR(255),
  store_id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (customer_id) REFERENCES `user`(id),
  FOREIGN KEY (delivery_address_id) REFERENCES `address`(id),
  FOREIGN KEY (store_id) REFERENCES `store`(id)
);

CREATE TABLE `order_product` (
  order_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES `order`(id),
  FOREIGN KEY (product_id) REFERENCES `product`(id)
);
