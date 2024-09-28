package insurance_system_board_service.service;

import insurance_system_board_service.jpa.Board;
import insurance_system_board_service.jpa.BoardDTO;

import java.util.List;

public interface BoardService {

    List<Board> getAllBoards();

    Board addBoard(BoardDTO boardDTO);

    boolean deleteBoardById(String id);

    Board getBoardById(String id);
//    List<Contract> getContractByProductId(String id);

    Board updateAnswer(BoardDTO boardDTO);


}
