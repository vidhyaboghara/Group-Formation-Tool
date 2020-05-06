DELIMITER $$

DROP PROCEDURE IF EXISTS spCreateQuestion $$

CREATE PROCEDURE spCreateQuestion (
	IN titlej VARCHAR(45),
    IN textj VARCHAR(100),
    IN questiontypej VARCHAR(45),
    IN createdOnj DATE,
    IN instructorIdj bigint
)
BEGIN
	SELECT QuestionType.id 
	INTO @questionTypeID
	FROM QuestionType
	WHERE type = questiontypej;
	
    INSERT INTO Question(title, text, typeID, createdOn)
	VALUES (titlej, textj, @questionTypeID, createdOnj);
	SET @questionID = LAST_INSERT_ID();
    
    SELECT InstructorQuestionBank.questionBankID 
    INTO @questionBankID 
    FROM InstructorQuestionBank
    WHERE instructorID = instructorIdj;
    
    IF(@questionBankID is not null) 
		THEN
		INSERT INTO QuestionBank(id,questionId)
        VALUES (@questionBankID,@questionID);
	ELSE
		SELECT MAX(InstructorQuestionBank.questionBankID) 
		INTO @maxQuestionBankID
		FROM InstructorQuestionBank;
		IF (@maxQuestionBankID is not null)
        THEN
			INSERT INTO QuestionBank(id,questionId)
			VALUES(@maxQuestionBankID+1,@questionID);
            INSERT INTO InstructorQuestionBank(instructorID,questionBankID)
			values(instructorIdj,@maxQuestionBankID+1);
            
		else
			INSERT INTO QuestionBank(id,questionId)
			VALUES(1,@questionID);
            INSERT INTO InstructorQuestionBank(instructorID,questionBankID)
			values(instructorIdj,1);
		end if;
	end if;
	
	SELECT Question.id FROM Question WHERE id = @questionID;
END $$

DELIMITER ;