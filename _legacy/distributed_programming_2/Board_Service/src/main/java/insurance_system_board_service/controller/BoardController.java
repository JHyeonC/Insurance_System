package insurance_system_board_service.controller;

import insurance_system_board_service.jpa.Board;
import insurance_system_board_service.jpa.BoardDTO;
import insurance_system_board_service.service.BoardService;
import insurance_system_board_service.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoardController {
    private final BoardService boardService;
    private final KafkaProducer kafkaProducer;

    @GetMapping("api/boards")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PostMapping("api/board/create")
    public Board addBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.addBoard(boardDTO);
    }

    @GetMapping("/board/{id}")
    public Board getBoardById(@PathVariable String id) {
        return boardService.getBoardById(id);
    }

    @PostMapping("api/board/update")
    public Board updateAnswer(@RequestBody BoardDTO boardDTO) {
        return boardService.updateAnswer(boardDTO);
    }

    @DeleteMapping("/board/{id}")
    public boolean deleteBoardByID(@PathVariable String id) {
        return boardService.deleteBoardById(id);
    }
}
