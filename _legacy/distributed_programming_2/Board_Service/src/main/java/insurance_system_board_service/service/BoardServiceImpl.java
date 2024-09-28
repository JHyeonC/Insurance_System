package insurance_system_board_service.service;

import insurance_system_board_service.jpa.Board;
import insurance_system_board_service.jpa.BoardDTO;
import insurance_system_board_service.jpa.BoardRepository;
import insurance_system_board_service.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board addBoard(BoardDTO boardDTO) {
        Board board = new Board(boardDTO);
        board.setId(UUID.randomUUID().toString());
        board.setAnswerer("");
        board.setAnswer("");
        board.setIs_answered(0);
        return boardRepository.save(board);
    }

    @Override
    public boolean deleteBoardById(String id) {
        Board board = getBoardById(id);
        boardRepository.delete(board);
        return true;
    }


    @Override
    public Board getBoardById(String id) {
        if (boardRepository.getBoardById(id) == null) throw new IllegalArgumentException();
        return boardRepository.getBoardById(id);
    }


    @Override
    public Board updateAnswer(BoardDTO boardDTO) {
        Board board = boardRepository.getBoardById(boardDTO.getId());
        board.setAnswer(boardDTO.getAnswer());
        board.setAnswerer(boardDTO.getAnswerer());
        board.setIs_answered(1);
        return boardRepository.save(board);
    }
}
