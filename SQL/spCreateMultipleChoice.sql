DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateMultipleChoice $$

CREATE PROCEDURE spCreateMultipleChoice (
	IN id BIGINT,
    IN choicetext1 VARCHAR(100),
    IN storedAs1 INT
)
BEGIN
	INSERT INTO MultipleChoice(questionID, choiceText, storedAs)
	VALUES (id,choicetext1,storedAs1);	
	
END $$

DELIMITER ;