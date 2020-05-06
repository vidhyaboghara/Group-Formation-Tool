DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestionsByInstructor $$

CREATE PROCEDURE spLoadAllQuestionsByInstructor(
	IN instructorID bigint,
    IN columnName VARCHAR(50)
)
BEGIN

	DECLARE SQLStatement varchar(1000);

    SET @SQLStatement = CONCAT_WS('',
		'select q.id, q.title, q.text, qt.type, q.createdOn
		from Question q join QuestionType qt on q.typeID = qt.id
		join QuestionBank qb on q.id = qb.questionId
		join InstructorQuestionBank iqb on iqb.questionBankID = qb.id
		where iqb.instructorID = instructorID
        ORDER BY `', columnName, '` '
    );
    
	PREPARE stmt FROM @SQLStatement;
	EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END $$

DELIMITER ;