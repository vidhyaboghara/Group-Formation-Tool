DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateCourseSurvey $$

CREATE PROCEDURE spCreateCourseSurvey(
	IN surveyId1 VARCHAR(100),
    IN courseId1 bigint
)
BEGIN
	INSERT INTO CourseSurvey (`surveyID`,`courseID`) VALUES (surveyId1, courseId1);
END $$

DELIMITER ;