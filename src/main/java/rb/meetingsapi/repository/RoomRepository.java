package rb.meetingsapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rb.meetingsapi.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

}
