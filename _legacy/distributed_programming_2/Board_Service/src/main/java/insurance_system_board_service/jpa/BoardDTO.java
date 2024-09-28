package insurance_system_board_service.jpa;

import lombok.Data;

@Data
public class BoardDTO {
    private String id;
    private String author;
    private String title;
    private String content;
    private String answer;
    private String answerer;
    private int is_answered;
}
