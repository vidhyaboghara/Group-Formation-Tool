DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionById $$

CREATE PROCEDURE spDeleteQuestionById`(
	IN questionId1 BIGINT
)
BEGIN
	SELECT id INTO @questionBankId1 FROM QuestionBank WHERE questionId = questionId1;
    SELECT questionId INTO @questions FROM QuestionBank WHERE id = @questionBankId1 AND questionId != questionId1 LIMIT 1;
	IF(@questions IS null)
    THEN
		DELETE FROM InstructorQuestionBank WHERE questionBankId = @questionBankId1;
	END IF;
    SELECT id INTO @surveyQuestionId FROM SurveyQuestions WHERE questionId = questionId1;
    IF(@surveyQuestionId IS NOT NULL)
    THEN
		DELETE FROM SurveyQuestions WHERE id = @surveyQuestionId;
	END IF;
    DELETE FROM Question where id = questionId1;
END $$

DELIMITER ;