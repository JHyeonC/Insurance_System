package insurance_system_employee_service.dto;

import lombok.Data;

@Data
public class BoardDTO {

    public BoardDTO(String id, String author, String title, String content, String answer, String answerer, Integer is_answered) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.answerer = answerer;
        this.is_answered = is_answered;
    }

    public String id;
    public String author;
    public String title;
    public String content;
    public String answer;
    public String answerer;
    public Integer is_answered;
}
