CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`Question` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `text` VARCHAR(100) NOT NULL,
  `typeID` BIGINT(20) NOT NULL,
  `createdOn` DATE NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`Survey` (
  `id` VARCHAR(100) NOT NULL,
  `questionID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `questionFK_idx` (`questionID` ASC),
  CONSTRAINT `survey-questionFK`
    FOREIGN KEY (`questionID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Question` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
    
CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`CourseSurvey` (
  `courseID` BIGINT(20) NOT NULL,
  `surveyID` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`courseID`, `surveyID`),
  INDEX `surveyFK_idx` (`surveyID` ASC),
  CONSTRAINT `courseFK`
    FOREIGN KEY (`courseID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `surveyFK`
    FOREIGN KEY (`surveyID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Survey` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`QuestionBank` (
  `id` BIGINT(20) NOT NULL,
  `questionId` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `questionId`),
  INDEX `questionFK_idx` (`questionId` ASC),
  CONSTRAINT `questionFK`
    FOREIGN KEY (`questionId`)
    REFERENCES `CSCI5308_2_DEVINT`.`Question` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`InstructorQuestionBank` (
  `instructorID` BIGINT(20) NOT NULL,
  `questionBankID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`instructorID`),
  INDEX `questionbankFK_idx` (`questionBankID` ASC),
  CONSTRAINT `instructor-user`
    FOREIGN KEY (`instructorID`)
    REFERENCES `CSCI5308_2_DEVINT`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`MultipleChoice` (
  `questionID` BIGINT(20) NOT NULL,
  `choiceText` VARCHAR(100) NOT NULL,
  `storedAs` INT(11) NOT NULL,
  INDEX `choice-questionFK_idx` (`questionID` ASC),
  CONSTRAINT `choice-questionFK`
    FOREIGN KEY (`questionID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Question` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`QuestionType` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`Response` (
  `id` VARCHAR(100) NOT NULL,
  `questionID` BIGINT(20) NOT NULL,
  `answerText` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`SurveyResponse` (
  `studentID` BIGINT(20) NOT NULL,
  `surveyID` VARCHAR(100) NOT NULL,
  `responseID` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`studentID`, `surveyID`, `responseID`),
  INDEX `student-surveyFK_idx` (`surveyID` ASC),
  INDEX `responseFK_idx` (`responseID` ASC),
  CONSTRAINT `responseFK`
    FOREIGN KEY (`responseID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Response` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `student-surveyFK`
    FOREIGN KEY (`surveyID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Survey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `studentFK`
    FOREIGN KEY (`studentID`)
    REFERENCES `CSCI5308_2_DEVINT`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`CourseSurvey` (
  `courseID` BIGINT(20) NOT NULL,
  `surveyID` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`courseID`, `surveyID`),
  CONSTRAINT `courseFK`
    FOREIGN KEY (`courseID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`SurveyQuestions` (
  `id` VARCHAR(100) NOT NULL,
  `questionID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `questionID`),
  INDEX `questionFK_idx` (`questionID` ASC) VISIBLE,
  CONSTRAINT `survey-questionFK`
    FOREIGN KEY (`questionID`)
    REFERENCES `CSCI5308_2_DEVINT`.`Question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE IF NOT EXISTS `CSCI5308_2_DEVINT`.`logs` (
  `DATED` DATE NOT NULL,
  `LOGGER` VARCHAR(1000) NOT NULL,
  `LEVEL` VARCHAR(10) NOT NULL,
  `MESSAGE` VARCHAR(1000) NOT NULL);
    