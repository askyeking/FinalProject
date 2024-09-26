-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema concertsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `concertsdb` ;

-- -----------------------------------------------------
-- Schema concertsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `concertsdb` DEFAULT CHARACTER SET utf8 ;
USE `concertsdb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `venue` ;

CREATE TABLE IF NOT EXISTS `venue` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `city` VARCHAR(200) NULL,
  `state` VARCHAR(200) NULL,
  `zip` CHAR(5) NULL,
  `street` VARCHAR(200) NULL,
  `image_url` VARCHAR(2000) NULL,
  `website_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert` ;

CREATE TABLE IF NOT EXISTS `concert` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `doors_time` TIME NULL,
  `start_time` TIME NULL,
  `day_of_event` DATE NULL,
  `image_url` VARCHAR(2000) NULL,
  `age_requirement` VARCHAR(45) NULL,
  `ticket_purchase_link` VARCHAR(2000) NULL,
  `website_url` VARCHAR(2000) NULL,
  `venue_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_concert_venue1_idx` (`venue_id` ASC) VISIBLE,
  CONSTRAINT `fk_concert_venue1`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `band`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `band` ;

CREATE TABLE IF NOT EXISTS `band` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `image_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `genre` ;

CREATE TABLE IF NOT EXISTS `genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `band_has_genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `band_has_genre` ;

CREATE TABLE IF NOT EXISTS `band_has_genre` (
  `band_id` INT NOT NULL,
  `genre_id` INT NOT NULL,
  PRIMARY KEY (`band_id`, `genre_id`),
  INDEX `fk_band_has_genre_genre1_idx` (`genre_id` ASC) VISIBLE,
  INDEX `fk_band_has_genre_band1_idx` (`band_id` ASC) VISIBLE,
  CONSTRAINT `fk_band_has_genre_band1`
    FOREIGN KEY (`band_id`)
    REFERENCES `band` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_band_has_genre_genre1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genre` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `concert_act`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `concert_act` ;

CREATE TABLE IF NOT EXISTS `concert_act` (
  `concert_id` INT NOT NULL,
  `band_id` INT NOT NULL,
  `lineup_position` INT NOT NULL,
  PRIMARY KEY (`concert_id`, `band_id`),
  INDEX `fk_concert_has_band_band1_idx` (`band_id` ASC) VISIBLE,
  INDEX `fk_concert_has_band_concert1_idx` (`concert_id` ASC) VISIBLE,
  CONSTRAINT `fk_concert_has_band_concert1`
    FOREIGN KEY (`concert_id`)
    REFERENCES `concert` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_concert_has_band_band1`
    FOREIGN KEY (`band_id`)
    REFERENCES `band` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_band`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_band` ;

CREATE TABLE IF NOT EXISTS `favorite_band` (
  `band_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`band_id`, `user_id`),
  INDEX `fk_band_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_band_has_user_band1_idx` (`band_id` ASC) VISIBLE,
  CONSTRAINT `fk_band_has_user_band1`
    FOREIGN KEY (`band_id`)
    REFERENCES `band` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_band_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_venue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_venue` ;

CREATE TABLE IF NOT EXISTS `favorite_venue` (
  `user_id` INT NOT NULL,
  `venue_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `venue_id`),
  INDEX `fk_user_has_venue_venue1_idx` (`venue_id` ASC) VISIBLE,
  INDEX `fk_user_has_venue_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_venue_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_venue_venue1`
    FOREIGN KEY (`venue_id`)
    REFERENCES `venue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS concertsuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'concertsuser'@'localhost' IDENTIFIED BY 'concertsuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'concertsuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`) VALUES (1, 'test', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'standard', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `image_url`) VALUES (2, 'ConcertGoer', '$2a$10$nShOi5/f0bKNvHB8x0u3qOpeivazbuN0NE4TO0LGvQiTMafaBxLJS', 1, 'standard', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `venue` (`id`, `name`, `city`, `state`, `zip`, `street`, `image_url`, `website_url`) VALUES (1, 'Gothic Theater', 'Englewood', 'Colorado', '80113', '3263', 'https://upload.wikimedia.org/wikipedia/commons/6/6b/The_Gothic_Theater_%286034645077%29.jpg', 'https://www.gothictheatre.com/');

COMMIT;


-- -----------------------------------------------------
-- Data for table `concert`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `concert` (`id`, `doors_time`, `start_time`, `day_of_event`, `image_url`, `age_requirement`, `ticket_purchase_link`, `website_url`, `venue_id`) VALUES (1, '7:00:00', '8:00:00', '2024-10-02', 'https://media.pitchfork.com/photos/663a3eaa68d7f4b7d12cde91/master/w_1280%2Cc_limit/Fontaines-DC-Tour-2024.jpg', '16', 'https://www.axs.com/events/559567/fontaines-d-c-tickets', 'https://www.gothictheatre.com/events/detail/?event_id=559567', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `band`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `band` (`id`, `name`, `image_url`) VALUES (1, 'Fontaines D.C.', 'https://images.discovery-prod.axs.com/2024/05/fontaines-d-c-tickets_10-02-24_17_663a387bcac49.jpg');
INSERT INTO `band` (`id`, `name`, `image_url`) VALUES (2, 'Been Stellar', 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRYO53fs_vMkcMmGNMq-dMAAtsrRtKJZG6I9voSKo_rW7xoSY41');

COMMIT;


-- -----------------------------------------------------
-- Data for table `genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `genre` (`id`, `name`) VALUES (1, 'Indie');
INSERT INTO `genre` (`id`, `name`) VALUES (2, 'Post-Punk');
INSERT INTO `genre` (`id`, `name`) VALUES (3, 'Shoegaze');
INSERT INTO `genre` (`id`, `name`) VALUES (4, 'Rock');

COMMIT;


-- -----------------------------------------------------
-- Data for table `band_has_genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `band_has_genre` (`band_id`, `genre_id`) VALUES (1, 1);
INSERT INTO `band_has_genre` (`band_id`, `genre_id`) VALUES (1, 2);
INSERT INTO `band_has_genre` (`band_id`, `genre_id`) VALUES (1, 4);
INSERT INTO `band_has_genre` (`band_id`, `genre_id`) VALUES (2, 1);
INSERT INTO `band_has_genre` (`band_id`, `genre_id`) VALUES (2, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `concert_act`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `concert_act` (`concert_id`, `band_id`, `lineup_position`) VALUES (1, 1, 1);
INSERT INTO `concert_act` (`concert_id`, `band_id`, `lineup_position`) VALUES (1, 2, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_band`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `favorite_band` (`band_id`, `user_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_venue`
-- -----------------------------------------------------
START TRANSACTION;
USE `concertsdb`;
INSERT INTO `favorite_venue` (`user_id`, `venue_id`) VALUES (1, 1);

COMMIT;

