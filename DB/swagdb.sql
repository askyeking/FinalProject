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
  `password` VARCHAR(45) NOT NULL,
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
-- Table `vendor-payment-type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vendor-payment-type` ;

CREATE TABLE IF NOT EXISTS `vendor-payment-type` (
  `vendor-id` INT NOT NULL,
  `payment-type-id` INT NOT NULL,
  `account-number` VARCHAR(250) NOT NULL,
  `expiration-date` DATE NULL,
  `Account-holder-name` VARCHAR(500) NOT NULL,
  INDEX `vendor-payment-type_vendor_FK_idx` (`vendor-id` ASC),
  INDEX `vendor-payment-type_payment-type-id_idx` (`payment-type-id` ASC),
  PRIMARY KEY (`vendor-id`, `payment-type-id`),
  CONSTRAINT `vendor-payment-type_vendor_FK`
    FOREIGN KEY (`vendor-id`)
    REFERENCES `vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `vendor-payment-type_payment-type-id`
    FOREIGN KEY (`payment-type-id`)
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
  `condition` VARCHAR(100) NOT NULL,
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
  INDEX `rating-of-vendor-customer_idx` (`customer_id` ASC),
  INDEX `rating-of-vendor_item-rental_fk_idx` (`item_rental_id` ASC),
  PRIMARY KEY (`item_rental_id`, `customer_id`),
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
  INDEX `rating-of-customer_vendor_fk_idx` (`vendor_id` ASC),
  INDEX `rating-of-customer_item-rental_fk_idx` (`item_rental_id` ASC),
  PRIMARY KEY (`item_rental_id`, `vendor_id`),
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
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (1, 'customer', 'customer@user.com', 1, 'standard');
INSERT INTO `user` (`id`, `password`, `email`, `active`, `role`) VALUES (2, 'vendorcustomer', 'vendor@user.com', 1, 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `vendor` (`id`, `user_id`, `image_url`, `about`, `display_name`, `active`) VALUES (1, 2, NULL, 'I sell swag + you buy swag = We Swag, bro!', 'theSeller', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (1, 1, 'blake', NULL, 1);
INSERT INTO `customer` (`id`, `user_id`, `display_name`, `avatar_url`, `active`) VALUES (2, 2, 'swagg3r', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (1, 'credit card', 'Visa');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (2, 'credit card', 'Discover');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (3, 'credit card', 'Mastercard');
INSERT INTO `payment_type` (`id`, `type`, `provider`) VALUES (4, 'PayPal', '');

COMMIT;


-- -----------------------------------------------------
-- Data for table `vendor-payment-type`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `vendor-payment-type` (`vendor-id`, `payment-type-id`, `account-number`, `expiration-date`, `Account-holder-name`) VALUES (1, 1, '1234567899999999', '2025-11-11', 'Bro Bro');

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment_method`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `payment_method` (`id`, `payment_type_id`, `customer_id`, `name`, `account_number`, `cvc`, `expiration_date`, `account_holder_name`) VALUES (1, 1, 1, 'Da card', '123456789102300', '223', '11/2023', 'Bruce Springsteen');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `inventory_item` (`id`, `vendor_id`, `price`, `name`, `description`, `image_url`, `condition`) VALUES (1, 1, 3.99, 'swagger hat for swaggy men', 'get that swag bro! I know you want it, you know I want you.', 'https://www.google.com/url?sa=i&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwjI097ekv3eAhXI6oMKHdE4AW4QjRx6BAgBEAU&url=https%3A%2F%2Fwww.gangstagroup.com%2Fgangstagroup-sorry-im-swag-snapback-cap-navy-red%2F&psig=AOvVaw0K8zMcx0wimPAOr3YTc7bE&ust=1543702665988705', 'good');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'hats');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'pants');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'ties');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'shirts');

COMMIT;


-- -----------------------------------------------------
-- Data for table `inventory_item_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (1, 1);
INSERT INTO `inventory_item_category` (`inventory_item_id`, `category_id`) VALUES (1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `item_rental`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `item_rental` (`id`, `inventory_item_id`, `customer_id`, `paid`, `start_date`, `end_date`, `paid_amount`, `active`, `transaction_info`) VALUES (1, 1, 1, 1, '2018-12-13', '2018-12-14', 56, 1, 'send it to me address mate. I\'ll pay ya. That stuff is real swag!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating_of_vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `rating_of_vendor` (`item_rental_id`, `customer_id`, `rating`, `comment`) VALUES (1, 1, 3, 'He give da man, me, 4 stars, I give him 3!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating_of_customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `rating_of_customer` (`item_rental_id`, `vendor_id`, `rating`, `comment`) VALUES (1, 1, 4, 'Decent experience');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_from_vendor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `comment_from_vendor` (`id`, `vendor_id`, `item_rental_id`, `comment`) VALUES (1, 1, 1, 'cool, cool');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment_from_customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaswagdb`;
INSERT INTO `comment_from_customer` (`id`, `customer_id`, `item_rental_id`, `comment`) VALUES (1, 1, 1, 'Cool dude');

COMMIT;
