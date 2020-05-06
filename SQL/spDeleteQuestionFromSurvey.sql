DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionFromSurvey $$

CREATE PROCEDURE spDeleteQuestionFromSurvey(
	IN surveyId VARCHAR(100),
    IN questionId1 BIGINT
)
BEGIN
	DELETE FROM SurveyQuestions where id = surveyId and questionID = questionId1;
END $$

DELIMITER ;