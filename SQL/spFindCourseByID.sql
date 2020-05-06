DELIMITER $$

DROP PROCEDURE IF EXISTS spFindQuestionById $$

CREATE PROCEDURE spFindQuestionById (
	IN questionID BIGINT
)
BEGIN
	SELECT * FROM Question
    WHERE Question.id = questionID;
END $$

DELIMITER ;