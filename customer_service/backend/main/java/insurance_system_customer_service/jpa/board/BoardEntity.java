package insurance_system_customer_service.jpa.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity {
    @Id
    private String id;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "answer")
    private String answer;
    @Column(name = "answerer")
    private String answerer;
    @Column(name= "is_answered", nullable = false)
    private int is_answered;

    @Builder
    public BoardEntity(String id, String author, String title, String content, String answer, String answerer, int is_answered){
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.answerer = answerer;
        this.is_answered = is_answered;
    }
}
