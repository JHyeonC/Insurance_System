package insurance_system_customer_service.service;

import insurance_system_customer_service.jpa.board.BoardEntity;
import insurance_system_customer_service.jpa.board.BoardRepository;
import insurance_system_customer_service.service.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public boolean createBoard(BoardVO vo) {
        BoardEntity entity = BoardEntity.builder()
                .id(UUID.randomUUID().toString())
                .author(vo.getAuthor())
                .title(vo.getTitle())
                .content(vo.getContent())
                .answer(vo.getAnswer())
                .answerer(vo.getAnswerer())
                .is_answered(0)
                .build();
        BoardEntity saveEntity = boardRepository.save(entity);
        if(saveEntity != null) return true;
        return false;
    }

    public List<BoardVO> getAllBoards() {
        List<BoardEntity> entityList = boardRepository.findAll();
        if(entityList.isEmpty() || entityList == null) return null;
        List<BoardVO> boardVOList = new ArrayList<>();
        for(BoardEntity entity : entityList){
            BoardVO vo = new BoardVO(entity.getId(), entity.getAuthor(), entity.getTitle(),
                    entity.getContent(), entity.getAnswer(), entity.getAnswerer(), entity.getIs_answered());
            boardVOList.add(vo);
        }
        return boardVOList;
    }
}
