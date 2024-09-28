package insurance_system_employee_service.jpa.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


@Entity
@Getter
@NoArgsConstructor
@Table(name="board")
public class BoardEntity  implements Serializable {


    @Id
    private String id;
    @Column(name = "author" , nullable = false)
    private String author;
    @Column(name = "title" , nullable = false)
    private String title;
    @Column(name = "content" , nullable = false)
    private String content;
    @Column(name = "answer")
    private String answer;
    @Column(name = "answerer")
    private String answerer;
    @Column(name = "is_answered" , nullable = false)
    private int is_answered;

    @Builder
    public BoardEntity(String id, String author, String title, String content, String answer, String answerer , int is_answered) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.answerer = answerer;
        this.is_answered = is_answered;
    }
    public void setIs_answered(int is_answered) {
        this.is_answered = is_answered;
    }
}
