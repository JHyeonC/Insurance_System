package insurance_system_employee_service.service.vo;

import insurance_system_employee_service.jpa.board.BoardEntity;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class BoardVO {

    private String id;
    private String author;
    private String title;
    private String content;
    private String answer;
    private String answerer;
    private int is_answered;

    public BoardVO(String id, String author, String title, String content, String answer , String answerer , int is_answered) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.answerer = answerer;
        this.is_answered = is_answered;
    }
}
