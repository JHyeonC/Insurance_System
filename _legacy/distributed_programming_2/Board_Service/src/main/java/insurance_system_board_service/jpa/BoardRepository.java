package insurance_system_board_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board , String> {
    @Query("select b from Board b where b.id = :board_id")
    Board getBoardById(@Param("board_id") String id);
}
