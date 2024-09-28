package insurance_system_employee_service.jpa.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<BoardEntity , String> {
    @Query("SELECT b FROM BoardEntity b WHERE b.id = :id")
    BoardEntity getBoardByID(String id);
}
