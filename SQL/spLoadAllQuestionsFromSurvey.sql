DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestionsFromSurvey $$

CREATE PROCEDURE spLoadAllQuestionsFromSurvey(
    IN surveyId VARCHAR(100)
)
BEGIN		
		select q.id, q.title, q.text, qt.type, q.createdOn
		from Question q
        join QuestionType qt on q.typeID = qt.id
        join SurveyQuestions sq on q.id = sq.questionID
		where sq.id = surveyId;
END $$

DELIMITER ;