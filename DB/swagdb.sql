-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rentaswagdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `rentaswagdb` ;

-- -----------------------------------------------------
-- Schema rentaswagdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rentaswagdb` DEFAULT CHARACTER SET utf8 ;
USE `rentaswagdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `password` TEXT NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NOT NULL DEFAULT 'standard',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vendor` ;

CREATE TABLE IF NOT EXISTS `vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `image_url` VARCHAR(45) NULL,
  `about` TEXT NULL,
  `display_name` VARCHAR(45) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `vendor-user-fk_idx` (`user_id` ASC),
  CONSTRAINT `vendor-user-fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `display_name` VARCHAR(45) NOT NULL,
  `avatar_url` TEXT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `customer_user_FK_idx` (`user_id` ASC),
  CONSTRAINT `customer_user_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment_type` ;

CREATE TABLE IF NOT EXISTS `payment_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(500) NOT NULL,
  `provider` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vendor_payment_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vendor_payment_type` ;

CREATE TABLE IF NOT EXISTS `vendor_payment_type` (
  `vendor_id` INT NOT NULL,
  `payment_type_id` INT NOT NULL,
  `account_number` VARCHAR(250) NOT NULL,
  `expiration_date` DATE NULL,
  `Account_holder_name` VARCHAR(500) NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `vendor-payment-type_vendor_FK_idx` (`vendor_id` ASC),
  INDEX `vendor-payment-type_payment-type-id_idx` (`payment_type_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `vendor-payment-type_vendor_FK`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vendor-payment-type_payment-type-id`
    FOREIGN KEY (`payment_type_id`)
    REFERENCES `payment_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment_method`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment_method` ;

CREATE TABLE IF NOT EXISTS `payment_method` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `payment_type_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `name` VARCHAR(100) NULL,
  `account_number` VARCHAR(250) NULL,
  `cvc` VARCHAR(10) NULL,
  `expiration_date` VARCHAR(250) NULL,
  `account_holder_name` VARCHAR(250) NULL,
  PRIMARY KEY (`id`),
  INDEX `payment-method_customer_FK_idx` (`customer_id` ASC),
  INDEX `payment-methods_payment-type_fk_idx` (`payment_type_id` ASC),
  CONSTRAINT `payment-method_customer_FK`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `payment-methods_payment-type_fk`
    FOREIGN KEY (`payment_type_id`)
    REFERENCES `payment_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item` ;

CREATE TABLE IF NOT EXISTS `inventory_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vendor_id` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `name` VARCHAR(150) NOT NULL,
  `description` TEXT NULL,
  `image_url` TEXT NULL,
  `item_condition` VARCHAR(100) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT 1,
  `rented` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `inventory-item_vendor_FK_idx` (`vendor_id` ASC),
  CONSTRAINT `inventory-item_vendor_FK`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `inventory_item_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `inventory_item_category` ;

CREATE TABLE IF NOT EXISTS `inventory_item_category` (
  `inventory_item_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  INDEX `inventory-item-category_inventory-item_fk_idx` (`inventory_item_id` ASC),
  INDEX `inventory-item-category_category_fk_idx` (`category_id` ASC),
  PRIMARY KEY (`inventory_item_id`, `category_id`),
  CONSTRAINT `inventory-item-category_inventory-item_fk`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `inventory-item-category_category_fk`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `item_rental`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `item_rental` ;

CREATE TABLE IF NOT EXISTS `item_rental` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inventory_item_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `paid` TINYINT(1) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NULL,
  `paid_amount` DECIMAL NULL,
  `active` TINYINT(1) NOT NULL,
  `transaction_info` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `inventory-item-id_idx` (`inventory_item_id` ASC),
  INDEX `item-rental_customer_fk_idx` (`customer_id` ASC),
  CONSTRAINT `item-rental_inventory-item_fk`
    FOREIGN KEY (`inventory_item_id`)
    REFERENCES `inventory_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item-rental_customer_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating_of_vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating_of_vendor` ;

CREATE TABLE IF NOT EXISTS `rating_of_vendor` (
  `item_rental_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `rating` INT NOT NULL,
  `comment` TEXT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `rating-of-vendor-customer_idx` (`customer_id` ASC),
  INDEX `rating-of-vendor_item-rental_fk_idx` (`item_rental_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `rating-of-vendor_customer_fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rating-of-vendor_item-rental_fk`
    FOREIGN KEY (`item_rental_id`)
    REFERENCES `item_rental` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating_of_customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating_of_customer` ;

CREATE TABLE IF NOT EXISTS `rating_of_customer` (
  `item_rental_id` INT NOT NULL,
  `vendor_id` INT NOT NULL,
  `rating` INT NOT NULL,
  `comment` TEXT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  INDEX `rating-of-customer_vendor_fk_idx` (`vendor_id` ASC),
  INDEX `rating-of-customer_item-rental_fk_idx` (`item_rental_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `rating-of-customer_vendor_fk`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rating-of-customer_item-rental_fk`
    FOREIGN KEY (`item_rental_id`)
    REFERENCES `item_rental` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_from_vendor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment_from_vendor` ;

CREATE TABLE IF NOT EXISTS `comment_from_vendor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vendor_id` INT NOT NULL,
  `item_rental_id` INT NOT NULL,
  `comment` TEXT NOT NULL,
  `post_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `comment_from_vendor-vendor-fk_idx` (`vendor_id` ASC),
  INDEX `comment_from_vendor-item_rental-fk_idx` (`item_rental_id` ASC),
  CONSTRAINT `comment_from_vendor-vendor-fk`
    FOREIGN KEY (`vendor_id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_from_vendor-item_rental-fk`
    FOREIGN KEY (`item_rental_id`)
    REFERENCES `item_rental` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment_from_customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment_from_customer` ;

CREATE TABLE IF NOT EXISTS `comment_from_customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NOT NULL,
  `item_rental_id` INT NOT NULL,
  `comment` TEXT NOT NULL,
  `post_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `comment_from_customer-customer-fk_idx` (`customer_id` ASC),
  INDEX `comment_from_customer-item_rental-fk_idx` (`item_rental_id` ASC),
  CONSTRAINT `comment_from_customer-customer-fk`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_from_customer-item_rental-fk`
    FOREIGN KEY (`item_rental_id`)
    REFERENCES `item_rental` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS user@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'user'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (1, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'customer@user.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (2, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'vendor@user.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (3, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'b', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (4, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'SwagDad@gmail.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (5, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'catlover45@aol.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (6, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'gmail@hotmail.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (7, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'hotmail@gmail.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (8, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'Big&Tall@gmail.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (9, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'Novak@frontend.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (10, '$2a$10$iMp5YPAxCZajml5Bw5EDr.F3tH4WQoFxPwVclYzPLvkyYCUlIh5Ci', 'iliketurtles@me.com', 1, 'standard');
COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `vendor` (`id`, `user_id`, `image_url`, `about`, `display_name`, `active`) VALUES (1, 2, NULL, 'Lowkey clothes, for the refined gentlman', 'theSeller', 1);
INSERT INTO `vendor` (`id`, `user_id`, `image_url`, `about`, `display_name`, `active`) VALUES (2, 3, NULL, 'I have quite the collection of dapper garments, for the classy gentleman.', 'DapperDude124', 1);
INSERT INTO `vendor` (`id`, `user_id`, `image_url`, `about`, `display_name`, `active`) VALUES (3, 4, NULL, 'Pant suits for the business lady that doesn\'t take no for an answer', 'CallYourManager76', 1);
INSERT INTO `vendor` (`id`, `user_id`, `image_url`, `about`, `display_name`, `active`) VALUES (4, 5, NULL, 'Ski suits for those who don\t want to pay those outrageous resort prices!', 'RadicalBeans', 1);
INSERT INTO `vendor` (`id`, `user_id`, `image_url`, `about`, `display_name`, `active`) VALUES (5, 6, NULL, 'Tuxes for weddings, prom, or even a proposal!', 'SuitThySelf200OK', 1);




COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (1, 1, 'blake', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (2, 2, 'swagg3r', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (3, 3, 'Davenport90', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (4, 4, 'KanyeDressed', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (5, 5, 'SteveHarwell', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (6, 6, 'Uzumaki', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (7, 7, 'BabyGirl25', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (8, 8, 'Tom', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (9, 9, 'ShranTheMan', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (10, 10, 'effrom23', NULL, 1);



COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (1, 'credit card', 'Visa');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (2, 'credit card', 'Discover');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (3, 'credit card', 'Mastercard');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (4, 'credit card', 'American Express');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (5, 'PayPal', 'PayPal');


COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor_payment_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `vendor_payment_type` (`vendor_id`, `payment_type_id`, `account_number`, `expiration_date`, `Account_holder_name`, `id`) VALUES (1, 1, '1234567899999999', '2025-11-11', 'David Hasselhoff', 1);
INSERT INTO `vendor_payment_type` (`vendor_id`, `payment_type_id`, `account_number`, `expiration_date`, `Account_holder_name`, `id`) VALUES (2, 2, '4400449944884499', '2025-11-11', 'Ricky Bobby', 2);
INSERT INTO `vendor_payment_type` (`vendor_id`, `payment_type_id`, `account_number`, `expiration_date`, `Account_holder_name`, `id`) VALUES (3, 3, '5678904378657676', '2025-11-11', 'Barry Knox', 3);
INSERT INTO `vendor_payment_type` (`vendor_id`, `payment_type_id`, `account_number`, `expiration_date`, `Account_holder_name`, `id`) VALUES (4, 4, '5678904378657676', '2025-11-11', 'Tom Haverford', 4);
INSERT INTO `vendor_payment_type` (`vendor_id`, `payment_type_id`, `account_number`, `expiration_date`, `Account_holder_name`, `id`) VALUES (5, 2, '5678904378657676', '2025-11-11', 'Leslie Knope', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_method`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `payment_method` (`id`, `payment_type_id`, `customer_id`, `name`, `account_number`, `cvc`, `expiration_date`, `account_holder_name`) VALUES (1, 1, 1, 'Da card', '123456789102300', '223', '11/2023', 'Bruce Springsteen');
INSERT INTO `payment_method` (`id`, `payment_type_id`, `customer_id`, `name`, `account_number`, `cvc`, `expiration_date`, `account_holder_name`) VALUES (2, 2, 2, 'Debit Card', '5678909065657890', '321', '11/2023', 'Tucker Mavis');
INSERT INTO `payment_method` (`id`, `payment_type_id`, `customer_id`, `name`, `account_number`, `cvc`, `expiration_date`, `account_holder_name`) VALUES (3, 3, 3, 'My Credit', '3434222044004040', '221', '11/2023', 'Trisha Caspian');
INSERT INTO `payment_method` (`id`, `payment_type_id`, `customer_id`, `name`, `account_number`, `cvc`, `expiration_date`, `account_holder_name`) VALUES (4, 4, 4, 'Points Card', '2828999002029938', '303', '11/2023', 'Marty McFly');
INSERT INTO `payment_method` (`id`, `payment_type_id`, `customer_id`, `name`, `account_number`, `cvc`, `expiration_date`, `account_holder_name`) VALUES (5, 1, 5, 'Clothes Card', '2200440055000099', '505', '11/2023', 'Rhonda Romano');


COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (1, 1, 3.99, 'Page Boy Hat', 'A nice hat all year round, keeps the sun out of your eyes or can provide some extra warmth in the winter.', 'https://images-na.ssl-images-amazon.com/images/I/61KToCWpZSL._UY445_.jpg', 'Good', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (2, 1, 10.99, 'Classy Suit Jacket', 'A great to nail that next job interview.', 'https://i5.walmartimages.com/asr/0d8e874d-4f7f-41bb-b882-fe3924de2c7f_1.614bef2f893531901e84103469411b9c.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF', 'Perfect', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (3, 1, 12.99, 'Dress Pants', 'A great pair of pants for almost any occasion in which jeans just won\'t cut it', 'https://uniqlo.scene7.com/is/image/UNIQLO/goods_09_408503?$prod$', 'Great', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (4, 2, 9.99, 'Blue Suit', 'A great suit to impress at your next job interview.', 'https://images.burton.co.uk/i/Burton/BUNDLE_AW17_02S01MBLU_4_M_1.jpg?$Large$', 'Perfect', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (5, 2, 8.99, 'Black Suit', 'A black and white suit for a more formal occasion.', 'http://www.menstuxedosuits.com/photo/pl17047757-korea_style_mens_two_piece_suits_blazer_pants_full_black_notch_lapel_polyester.jpg', 'New', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (6, 2, 9.99, 'Red Suit With Shorts', 'A Red suit with shorts for more entertainment focused affairs. Create the perfect first impression with this ensemble!', 'https://cdn.shopify.com/s/files/1/0234/5963/products/ss_reddevil_studio_rgb_a_1024x1024.jpg?v=1519262534', 'Perfect', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (7, 3, 9.99, 'Grey Pant Suit', 'Grey pant suit for a more neutral look.', 'https://s7d4.scene7.com/is/image/JCPenney/DP1018201811012674M.tif?wid=350&hei=350&op_usm=.4,.8,0,0&resmode=sharp2', 'New', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (8, 3, 9.99, 'Green Pant Suit', 'A green pant suit to leave an impression.', 'https://images.nyandcompany.com/is/image/NewYorkCompany/productlist2/7th-Avenue-Soft-Madie-Blazer_02254875_225.jpg', 'New', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (9, 3, 9.99, 'Black Pant Suit', 'Black pant suit for more formal occasions', 'https://ak1.ostkcdn.com/images/products/is/images/direct/9af2b7e0b32f9c24c4b54a83841dd3563f476726/Tahari-ASL-Womens-Missy-Pant-Suit-Pinstripe-Professional.jpg', 'Perfect', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (10, 4, 12.99, 'Men\'s Ski Suit', 'Full ski suit with accessories.', 'https://cdn-4c48.kxcdn.com/media/catalog/product/cache/5/image/580x850/85e4522595efc69f496374d01ef2bf13/o/n/oneskee_yellowblack_markiv_mens_front_full.jpg', 'Good', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (11, 4, 6.99, 'Kid\'s Ski Jacket', 'Kid\'s ski jacket to keep them warm on the slopes.', 'https://www.trespass.com/media/catalog/product/cache/6/small_image/420x/0dc2d03fe217f8c83829496872af24a0/c/r/crawley-ucsuskk20001-blr-a_4.jpg', 'New', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (12, 4, 12.99, 'Women\'s Ski Suit', 'Women\'s ski suit with all the accessories.', 'https://www.ski-chic.com/media/catalog/product/cache/2/image/1800x/040ec09b1e35df139433887a97daa66f/v/1/v12.jpg', 'good', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (13, 5, 20.99, 'Wedding Dress', 'A beautiful dress guaranteed to grab eyes at your wedding.', 'https://i.etsystatic.com/16183357/d/il/d9b2c1/1512119656/il_340x270.1512119656_62ar.jpg?version=0', 'good', 1, 0);

INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `item_condition`, `active`, `rented`) VALUES (14, 5, 15.99, 'Tuxedo with Tie', 'A fantastic tux, perfect for prom. Tie Included in rental.', 'https://i.ebayimg.com/images/g/QsQAAOSwb3NZqDSN/s-l300.jpg', 'good', 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Hats');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Pants');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Ties');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'Shirts');
INSERT INTO `category` (`id`, `name`) VALUES (6, 'Shoes');
INSERT INTO `category` (`id`, `name`) VALUES (7, 'Accessories');
INSERT INTO `category` (`id`, `name`) VALUES (8, 'Sport');
INSERT INTO `category` (`id`, `name`) VALUES (9, 'Dresses');
INSERT INTO `category` (`id`, `name`) VALUES (10, 'Suits');
INSERT INTO `category` (`id`, `name`) VALUES (11, 'Tuxedo');
INSERT INTO `category` (`id`, `name`) VALUES (12, 'Suit Jacket');
INSERT INTO `category` (`id`, `name`) VALUES (13, 'Formal');
INSERT INTO `category` (`id`, `name`) VALUES (14, 'Social');
INSERT INTO `category` (`id`, `name`) VALUES (15, 'Wedding');





COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (1, 1);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (2, 12);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (3, 2);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (3, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (4, 10);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (4, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (5, 10);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (5, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (6, 10);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (6, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (7, 10);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (7, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (8, 10);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (8, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (9, 10);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (9, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (10, 8);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (11, 8);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (12, 8);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (13, 9);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (13, 14);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (14, 13);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (14, 11);







COMMIT;


-- -----------------------------------------------------
-- Data for table `item_rental`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (1, 1, 1, 1, '2018-12-13', '2018-12-14', 56, 0, 'Send it to my address. I\'ll pay ya.!');
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (2, 5 , 3, 0, '2018-12-13', '2018-12-14', 56, 0, 'Ship it to my address');
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (3, 10 , 2, 1, '2018-12-13', '2018-12-14', 56, 1, 'Ship it to my address');
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (4, 11 , 2, 1, '2018-12-13', '2018-12-14', 56, 1, 'Ship it to my address');
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (5, 12 , 2, 1, '2018-12-13', '2018-12-14', 56, 1, 'Ship it to my address');
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (6, 6 , 8, 1, '2018-12-13', '2018-12-14', 56, 0, 'Ship it to my address, can\'t wait to try this out!');



COMMIT;


-- -----------------------------------------------------
-- Data for table `rating_of_vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `rating_of_vendor` (`item_rental_id`, `customer_id`, `rating`, `comment`, `id`) VALUES (1, 1, 3, 'He give da man, me, 4 stars, I give him 3!', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating_of_customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `rating_of_customer` (`item_rental_id`, `vendor_id`, `rating`, `comment`, `id`) VALUES (1, 1, 4, 'Decent experience', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_from_vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `comment_from_vendor` (`id`, `vendor_id`, `item_rental_id`, `comment`, `post_date`) VALUES (1, 1, 1, 'cool, cool', '2018-12-12 10-29-42');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_from_customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `comment_from_customer` (`id`, `customer_id`, `item_rental_id`, `comment`, `post_date`) VALUES (1, 1, 1, 'Cool dude', '2018-11-02 10:29:14');

COMMIT;
