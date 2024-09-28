package insurance_system_customer_service.jpa.board;

import insurance_system_customer_service.jpa.board.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, String> {

}
