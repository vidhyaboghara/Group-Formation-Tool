DELIMITER $$

DROP PROCEDURE IF EXISTS spFindQuestionInSurvey $$

CREATE PROCEDURE spFindQuestionInSurvey(
	IN surveyID VARCHAR(100),
    IN questionId1 BIGINT
)
BEGIN
	SELECT * FROM SurveyQuestions
    WHERE id = surveyID and questionID = questionId1;
END $$

DELIMITER ;