package insurance_system_customer_service.service.vo;

import lombok.Getter;

@Getter
public class BoardVO {

    public BoardVO(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public BoardVO(String id, String author, String title, String content, String answer, String answerer, int is_answered) {
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
    public int is_answered;

}
