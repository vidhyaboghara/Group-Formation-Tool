DELIMITER $$

DROP PROCEDURE IF EXISTS spAddQuestionToSurvey $$

CREATE PROCEDURE spAddQuestionToSurvey(
	IN surveyID VARCHAR(100),
    IN questionID1 BIGINT
)
BEGIN
    INSERT INTO SurveyQuestions(id, questionID)
    VALUES (surveyID, questionID1);
END $$

DELIMITER ;