DELIMITER $$

DROP PROCEDURE IF EXISTS spFindSurveyForCourse $$

CREATE PROCEDURE spFindSurveyForCourse(
    IN courseId bigint
)
BEGIN
	select * from CourseSurvey where courseID = courseId;
END $$

DELIMITER ;