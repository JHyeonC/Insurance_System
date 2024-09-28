package insurance_system_employee_service.service;


import insurance_system_employee_service.dto.BoardDTO;
import insurance_system_employee_service.jpa.board.BoardEntity;
import insurance_system_employee_service.jpa.board.BoardRepository;
import insurance_system_employee_service.jpa.product.ProductEntity;
import insurance_system_employee_service.service.vo.BoardVO;
import insurance_system_employee_service.service.vo.ClaimVO;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final KieContainer kieContainer;

    public List<BoardDTO> getAllBoards() {
        List<BoardDTO> boards = new ArrayList<>();
        List<BoardEntity> boardEntities = boardRepository.findAll();
        for(BoardEntity board : boardEntities) {
            boards.add(new BoardDTO(
                    board.getId()
                    , board.getAuthor()
                    , board.getTitle()
                    , board.getContent()
                    , board.getAnswer()
                    , board.getAnswerer()
                    , board.getIs_answered()
            ));
        }
        return boards;
    }

    public boolean createAnswer(BoardVO vo) {
        if(vo.getAnswer().trim().isEmpty()) throw new NullPointerException();
        BoardEntity temp = boardRepository.getBoardByID(vo.getId());
        BoardEntity board = BoardEntity.builder()
                .id(temp.getId())
                .author(temp.getAuthor())
                .title(temp.getTitle())
                .content(temp.getContent())
                .answer(vo.getAnswer())
                .answerer(vo.getAnswerer())
                .build();
        excuteRules(board);
        boardRepository.save(board);
        return true;
    }

    public void excuteRules(BoardEntity board) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(board);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
