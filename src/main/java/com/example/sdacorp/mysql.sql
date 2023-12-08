-- Creating database schema
CREATE SCHEMA `corp` ;

-- Creating Employee table
CREATE TABLE `corp`.`employee` (
                                   `eid` INT NOT NULL AUTO_INCREMENT,
                                   `username` VARCHAR(45) NULL,
                                   `password` VARCHAR(45) NOT NULL,
                                   `name` VARCHAR(45) NULL,
                                   `dob` DATE NULL,
                                   `address` VARCHAR(45) NULL,
                                   `contact` VARCHAR(45) NULL,
                                   `email` VARCHAR(45) NULL,
                                   `isavailable` TINYINT NULL DEFAULT 0,
                                   PRIMARY KEY (`eid`),
                                   UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

INSERT INTO `corp`.`employee` (`eid`, `username`, `password`, `name`, `dob`, `address`, `contact`, `email`, `isavailable`) VALUES ('1', 'mmahad', 'mahad', 'Muhammad Mahad', '2000-01-01', 'Lahore', '123456', 'mahad@mahad.com', '0');

-- Manager
CREATE TABLE `corp`.`manager` (
                             `mid` INT NOT NULL AUTO_INCREMENT,
                             `username` VARCHAR(45) NULL,
                             `password` VARCHAR(45) NOT NULL,
                             `name` VARCHAR(45) NULL,
                             `dob` DATE NULL,
                             `room_no` VARCHAR(45) NULL,
                             `address` VARCHAR(45) NULL,
                             `contact` VARCHAR(45) NULL,
                             `email` VARCHAR(45) NULL,
                             `isavailable` TINYINT NULL DEFAULT 0,
                             PRIMARY KEY (`mid`),
                             UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
INSERT INTO `corp`.`manager` (`mid`, `username`, `password`, `name`, `dob`, `address`, `contact`, `email`, `isavailable`) VALUES ('1', 'mmahad', 'mahad', 'muhammad mahad', '2000-01-01', 'Lahore', '12323', 'mahad@gmail.com', '0');
UPDATE `corp`.`manager` SET `room_no` = '1' WHERE (`mid` = '1');



-- HR
CREATE TABLE `corp`.`hr` (
                             `hid` INT NOT NULL AUTO_INCREMENT,
                             `username` VARCHAR(45) NULL,
                             `password` VARCHAR(45) NOT NULL,
                             `name` VARCHAR(45) NULL,
                             `dob` DATE NULL,
                             `room_no` VARCHAR(45) NULL,
                             `no_of_hiring` INT NULL,
                             `address` VARCHAR(45) NULL,
                             `contact` VARCHAR(45) NULL,
                             `email` VARCHAR(45) NULL,
                             `isavailable` TINYINT NULL DEFAULT 0,
                             PRIMARY KEY (`hid`),
                             UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
INSERT INTO `corp`.`hr` (`hid`, `username`, `password`, `name`, `dob`, `address`, `contact`, `email`, `isavailable`) VALUES ('1', 'mmahad', 'mahad', 'muhammad mahad', '2000-01-01', 'Lahore', '12323', 'mahad@gmail.com', '0');
UPDATE `corp`.`hr` SET `room_no` = '1', `no_of_hiring` = '1' WHERE (`hid` = '1');



-- Employee Salary
CREATE TABLE `corp`.`employee_salary` (
                                          `eid` INT NOT NULL ,
                                          `base_salary` DECIMAL NULL,
                                          `bonus` DECIMAL NULL,
                                          `fine` DECIMAL NULL,
                                          PRIMARY KEY (`eid`),
                                          CONSTRAINT `eid`
                                              FOREIGN KEY (`eid`)
                                                  REFERENCES `corp`.`employee` (`eid`)
                                                  ON DELETE CASCADE
                                                  ON UPDATE CASCADE);
INSERT INTO `corp`.`employee_salary` (`eid`, `base_salary`, `bonus`, `fine`) VALUES ('1', '500', '50', '50');

-- manager salary
CREATE TABLE `corp`.`manager_salary` (
                                         `mid` INT NOT NULL,
                                         `base_salary` DECIMAL NULL,
                                         `bonus` DECIMAL NULL,
                                         `fine` DECIMAL NULL,
                                         PRIMARY KEY (`mid`),
                                         CONSTRAINT `mid`
                                             FOREIGN KEY (`mid`)
                                                 REFERENCES `corp`.`manager` (`mid`)
                                                 ON DELETE CASCADE
                                                 ON UPDATE CASCADE);
INSERT INTO `corp`.`manager_salary` (`mid`, `base_salary`, `bonus`, `fine`) VALUES ('1', '500', '50', '50');

-- HR salary
CREATE TABLE `corp`.`hr_salary` (
                                    `hid` INT NOT NULL,
                                    `base_salary` DECIMAL NULL,
                                    `bonus` DECIMAL NULL,
                                    `fine` DECIMAL NULL,
                                    PRIMARY KEY (`hid`),
                                    CONSTRAINT `hid`
                                        FOREIGN KEY (`hid`)
                                            REFERENCES `corp`.`hr` (`hid`)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE
                                );
INSERT INTO `corp`.`hr_salary` (`hid`, `base_salary`, `bonus`, `fine`) VALUES ('1', '500', '50', '50');


-- Project
CREATE TABLE `corp`.`project`
(
    `pid`         INT         NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45) NULL,
    `description` VARCHAR(45) NULL,
    `status`      VARCHAR(45) NULL,
    `deadline`    DATETIME    NULL,
    `start_date`  DATETIME    NULL,
    `end_date`    DATETIME    NULL,
    PRIMARY KEY (`pid`)
);
-- Project has
CREATE TABLE `corp`.`project_has` (
                                      `pid` INT NOT NULL,
                                      `eid` INT NOT NULL,
                                      `mid` INT NOT NULL,
                                      PRIMARY KEY (`pid`, `eid`, `mid`));
ALTER TABLE `corp`.`project_has`
    ADD CONSTRAINT `pid_project_has_fk`
        FOREIGN KEY (`pid`)
            REFERENCES `corp`.`project` (`pid`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE `corp`.`project_has`
    ADD CONSTRAINT `eid_project_has_fk`
        FOREIGN KEY (`eid`)
            REFERENCES `corp`.`employee` (`eid`);
-- need to add mid and pid as foreign key
ALTER TABLE `corp`.`project_has`
    ADD CONSTRAINT `project_has_m_mid_fk`
        FOREIGN KEY (`mid`)
            REFERENCES `corp`.`manager` (`mid`);
ALTER TABLE `corp`.`project_has`
    ADD CONSTRAINT `project_has_e_eid_fk`
        FOREIGN KEY (`eid`)
            REFERENCES `corp`.`employee` (`eid`);




-- Employee Attendence
CREATE TABLE `corp`.`employee_attendence` (
                                              `eid` INT NOT NULL,
                                              `date` DATETIME NULL,
                                              `ispresent` TINYINT NULL,
                                              PRIMARY KEY (`eid`));

-- Manager Attendence
CREATE TABLE `corp`.`manager_attendence` (
                                             `mid` INT NOT NULL,
                                             `date` DATETIME NULL,
                                             `ispresent` TINYINT NULL,
                                             PRIMARY KEY (`mid`));

-- HR Attendence
CREATE TABLE `corp`.`hr_attendence` (
                                        `hid` INT NOT NULL,
                                        `date` DATETIME NULL,
                                        `ispresent` TINYINT NULL,
                                        PRIMARY KEY (`hid`));


-- form
CREATE TABLE `corp`.`form` (
                               `fid` INT NOT NULL AUTO_INCREMENT,
                               `fname` VARCHAR(45) NULL,
                               `description` VARCHAR(45) NULL,
                               `type` VARCHAR(10) NULL,
                               `eid` INT NULL,
                               `mid` INT NULL,
                               `isaccepted` TINYINT NULL,
                               PRIMARY KEY (`fid`));
ALTER TABLE `corp`.`form`
    ADD CONSTRAINT `form_f_eid_fk`
        FOREIGN KEY (`eid`)
            REFERENCES `corp`.`employee` (`eid`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE `corp`.`form`
    DROP FOREIGN KEY `form_f_eid_fk`;

ALTER TABLE `corp`.`form`
    ADD CONSTRAINT `form_m_id_fk`
        FOREIGN KEY (`mid`)
            REFERENCES `corp`.`manager` (`mid`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- complain table
CREATE TABLE `corp`.`complain` (
                                   `cid` INT NOT NULL AUTO_INCREMENT,
                                   `cname` VARCHAR(45) NULL,
                                   `description` VARCHAR(45) NULL,
                                   `eid` INT NULL,
                                   `mid` INT NULL,
                                   `isaccepted` TINYINT NULL,
                                   PRIMARY KEY (`cid`),
                                   UNIQUE INDEX `cid_UNIQUE` (`cid` ASC) VISIBLE,
                                   INDEX `complain_emp_fk_idx` (`eid` ASC) VISIBLE,
                                   INDEX `complain_man_fk_idx` (`mid` ASC) VISIBLE,
                                   CONSTRAINT `complain_emp_fk`
                                       FOREIGN KEY (`eid`)
                                           REFERENCES `corp`.`employee` (`eid`)
                                           ON DELETE CASCADE
                                           ON UPDATE CASCADE,
                                   CONSTRAINT `complain_man_fk`
                                       FOREIGN KEY (`mid`)
                                           REFERENCES `corp`.`manager` (`mid`)
                                           ON DELETE CASCADE
                                           ON UPDATE CASCADE);

-- Review
CREATE TABLE `corp`.`review` (
                                 `mid` INT NOT NULL AUTO_INCREMENT,
                                 `eid` INT NULL,
                                 `pid` INT NULL,
                                 PRIMARY KEY (`mid`),
                                 CONSTRAINT `add_mid_fk`
                                     FOREIGN KEY (`mid`)
                                         REFERENCES `corp`.`manager` (`mid`)
                                         ON DELETE CASCADE
                                         ON UPDATE CASCADE);
-- pid as foreign key
ALTER TABLE `corp`.`review`
    ADD CONSTRAINT `add_pid_fk`
        FOREIGN KEY (`pid`)
            REFERENCES `corp`.`project` (`pid`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- rating
CREATE TABLE `corp`.`rating` (
                                 `rid` INT NOT NULL AUTO_INCREMENT,
                                 `mid` INT NULL,
                                 `eid` INT NULL,
                                 `pid` INT NULL,
                                 `rating` INT NULL,
                                 PRIMARY KEY (`rid`),
                                 CONSTRAINT `rating_mid_fk`
                                     FOREIGN KEY (`mid`)
                                         REFERENCES `corp`.`manager` (`mid`)
                                         ON DELETE CASCADE
                                         ON UPDATE CASCADE,
                                 CONSTRAINT `rating_eid_fk`
                                     FOREIGN KEY (`eid`)
                                         REFERENCES `corp`.`employee` (`eid`)
                                         ON DELETE CASCADE
                                         ON UPDATE CASCADE,
                                 CONSTRAINT `rating_pid_fk`
                                     FOREIGN KEY (`pid`)
                                         REFERENCES `corp`.`project` (`pid`)
                                         ON DELETE CASCADE
                                         ON UPDATE CASCADE
);

-- eid as foreign key

ALTER TABLE `corp`.`review`
    ADD CONSTRAINT `add_eid_fk`
        FOREIGN KEY (`eid`)
            REFERENCES `corp`.`employee` (`eid`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- Employee
INSERT INTO `corp`.`employee` (`eid`, `username`, `password`, `name`, `dob`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('2', 'ahmed', 'ahmed123', 'Ahmed Khan', '1985-2-2', 'Karachi', '234567891', 'ahmed@gmail.com', '1');
INSERT INTO `corp`.`employee` (`eid`, `username`, `password`, `name`, `dob`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('3', 'sara', 'sara123', 'Sara Ali', '1990-3-3', 'Islamabad', '345678912', 'sara@gmail.com', '1');
INSERT INTO `corp`.`employee` (`eid`, `username`, `password`, `name`, `dob`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('4', 'usman', 'usman123', 'Usman Malik', '1980-4-4', 'Rawalpindi', '456789123', 'usman@gmail.com', '1');

-- Manager
INSERT INTO `corp`.`manager` (`mid`, `username`, `password`, `name`, `dob`, `room_no`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('2', 'ali', 'ali123', 'Ali Raza', '1980-2-2', '101', 'Karachi', '234567891', 'ali@gmail.com', '1');
INSERT INTO `corp`.`manager` (`mid`, `username`, `password`, `name`, `dob`, `room_no`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('3', 'zara', 'zara123', 'Zara Khan', '1990-3-3', '102', 'Islamabad', '345678912', 'zara@gmail.com', '1');
INSERT INTO `corp`.`manager` (`mid`, `username`, `password`, `name`, `dob`, `room_no`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('4', 'umar', 'umar123', 'Umar Farooq', '1980-4-4', '103', 'Rawalpindi', '456789123', 'umar@gmail.com', '1');

-- HR
INSERT INTO `corp`.`hr` (`hid`, `username`, `password`, `name`, `dob`, `room_no`, `no_of_hiring`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('2', 'bilal', 'bilal123', 'Bilal Raza', '1987-2-2', '201', '5', 'Karachi', '234567891', 'bilal@gmail.com', '1');
INSERT INTO `corp`.`hr` (`hid`, `username`, `password`, `name`, `dob`, `room_no`, `no_of_hiring`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('3', 'saima', 'saima123', 'Saima Khan', '1990-3-3', '202', '6', 'Islamabad', '345678912', 'saima@gmail.com', '1');
INSERT INTO `corp`.`hr` (`hid`, `username`, `password`, `name`, `dob`, `room_no`, `no_of_hiring`, `address`, `contact`, `email`, `isavailable`) 
VALUES ('4', 'tariq', 'tariq123', 'Tariq Ali', '1980-4-4', '203', '7', 'Rawalpindi', '456789123', 'tariq@gmail.com', '1');

-- Employee Salary
INSERT INTO `corp`.`employee_salary` (`eid`, `base_salary`, `bonus`, `fine`) VALUES ('2', '600', '60', '60');
INSERT INTO `corp`.`employee_salary` (`eid`, `base_salary`, `bonus`, `fine`) VALUES ('3', '700', '70', '70');
INSERT INTO `corp`.`employee_salary` (`eid`, `base_salary`, `bonus`, `fine`) VALUES ('4', '800', '80', '80');

-- manager salary
INSERT INTO `corp`.`manager_salary` (`mid`, `base_salary`, `bonus`, `fine`) VALUES ('2', '600', '60', '60');
INSERT INTO `corp`.`manager_salary` (`mid`, `base_salary`, `bonus`, `fine`) VALUES ('3', '700', '70', '70');
INSERT INTO `corp`.`manager_salary` (`mid`, `base_salary`, `bonus`, `fine`) VALUES ('4', '800', '80', '80');

-- HR salary
INSERT INTO `corp`.`hr_salary` (`hid`, `base_salary`, `bonus`, `fine`) VALUES ('2', '600', '60', '60');
INSERT INTO `corp`.`hr_salary` (`hid`, `base_salary`, `bonus`, `fine`) VALUES ('3', '700', '70', '70');
INSERT INTO `corp`.`hr_salary` (`hid`, `base_salary`, `bonus`, `fine`) VALUES ('4', '800', '80', '80');

-- Project
INSERT INTO `corp`.`project` (`pid`, `name`, `description`, `status`, `deadline`, `start_date`, `end_date`)
VALUES ('1', 'Project 1', 'Project 1 Description', 'In Progress', '2021-12-12 00:00:00', '2021-12-12 00:00:00', '2021-12-12 00:00:00');
INSERT INTO `corp`.`project` (`pid`, `name`, `description`, `status`, `deadline`, `start_date`, `end_date`)
VALUES ('2', 'Project 2', 'Project 2 Description', 'In Progress', '2021-12-12 00:00:00', '2021-12-12 00:00:00', '2021-12-12 00:00:00');
INSERT INTO `corp`.`project` (`pid`, `name`, `description`, `status`, `deadline`, `start_date`, `end_date`)
VALUES ('3', 'Project 3', 'Project 3 Description', 'In Progress', '2021-12-12 00:00:00', '2021-12-12 00:00:00', '2021-12-12 00:00:00');

-- Project has
INSERT INTO `corp`.`project_has` (`pid`, `eid`, `mid`) VALUES ('1', '2', '2');
INSERT INTO `corp`.`project_has` (`pid`, `eid`, `mid`) VALUES ('2', '3', '3');
INSERT INTO `corp`.`project_has` (`pid`, `eid`, `mid`) VALUES ('3', '4', '4');

-- Employee Attendence
INSERT INTO `corp`.`employee_attendence` (`eid`, `date`, `ispresent`) VALUES ('2', '2021-12-12 00:00:00', '1');
INSERT INTO `corp`.`employee_attendence` (`eid`, `date`, `ispresent`) VALUES ('3', '2021-12-12 00:00:00', '1');
INSERT INTO `corp`.`employee_attendence` (`eid`, `date`, `ispresent`) VALUES ('4', '2021-12-12 00:00:00', '1');

-- Manager Attendence
INSERT INTO `corp`.`manager_attendence` (`mid`, `date`, `ispresent`) VALUES ('2', '2021-12-12 00:00:00', '1');
INSERT INTO `corp`.`manager_attendence` (`mid`, `date`, `ispresent`) VALUES ('3', '2021-12-12 00:00:00', '1');
INSERT INTO `corp`.`manager_attendence` (`mid`, `date`, `ispresent`) VALUES ('4', '2021-12-12 00:00:00', '1');

-- HR Attendence
INSERT INTO `corp`.`hr_attendence` (`hid`, `date`, `ispresent`) VALUES ('2', '2021-12-12 00:00:00', '1');
INSERT INTO `corp`.`hr_attendence` (`hid`, `date`, `ispresent`) VALUES ('3', '2021-12-12 00:00:00', '1');
INSERT INTO `corp`.`hr_attendence` (`hid`, `date`, `ispresent`) VALUES ('4', '2021-12-12 00:00:00', '1');

-- form
INSERT INTO `corp`.`form` (`fid`, `fname`, `description`, `type`, `eid`, `mid`, `isaccepted`) VALUES ('1', 'Form 1', 'Form 1 Description', 'Leave', '2', '2', '1');
INSERT INTO `corp`.`form` (`fid`, `fname`, `description`, `type`, `eid`, `mid`, `isaccepted`) VALUES ('2', 'Form 2', 'Form 2 Description', 'Leave', '3', '3', '1');
INSERT INTO `corp`.`form` (`fid`, `fname`, `description`, `type`, `eid`, `mid`, `isaccepted`) VALUES ('3', 'Form 3', 'Form 3 Description', 'Leave', '4', '4', '1');

-- Review
INSERT INTO `corp`.`review` (`mid`, `eid`, `pid`) VALUES ('2', '2', '1');
INSERT INTO `corp`.`review` (`mid`, `eid`, `pid`) VALUES ('3', '3', '2');
INSERT INTO `corp`.`review` (`mid`, `eid`, `pid`) VALUES ('4', '4', '3');

-- Complain
INSERT INTO `corp`.`complain` (`cid`, `cname`, `description`, `eid`, `mid`, `isaccepted`) VALUES ('1', 'Complain 1', 'Complain 1 Description', '1', '1', '1');
INSERT INTO `corp`.`complain` (`cid`, `cname`, `description`, `eid`, `mid`, `isaccepted`) VALUES ('2', 'Complain 2', 'Complain 2 Description', '2', '2', '1');
INSERT INTO `corp`.`complain` (`cid`, `cname`, `description`, `eid`, `mid`, `isaccepted`) VALUES ('3', 'Complain 3', 'Complain 3 Description', '3', '3', '1');
INSERT INTO `corp`.`complain` (`cid`, `cname`, `description`, `eid`, `mid`, `isaccepted`) VALUES ('4', 'Complain 4', 'Complain 4 Description', '2', '1', '1');
-- Rating
INSERT INTO `corp`.`rating` (`rid`, `mid`, `eid`, `pid`, `rating`) VALUES ('1', '2', '2', '1', '9');
INSERT INTO `corp`.`rating` (`rid`, `mid`, `eid`, `pid`, `rating`) VALUES ('2', '1', '3', '2', '8');
INSERT INTO `corp`.`rating` (`rid`, `mid`, `eid`, `pid`, `rating`) VALUES ('3', '3', '1', '3', '6');
INSERT INTO `corp`.`rating` (`rid`, `mid`, `eid`, `pid`, `rating`) VALUES ('4', '2', '2', '1', '9');
