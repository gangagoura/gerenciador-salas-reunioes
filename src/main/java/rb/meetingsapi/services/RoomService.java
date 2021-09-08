package rb.meetingsapi.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rb.meetingsapi.exception.ResourceNotFoundException;
import rb.meetingsapi.model.Room;
import rb.meetingsapi.repository.RoomRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> showRooms(){
        return roomRepository.findAll();
    }

    public ResponseEntity<Room> getById(long roomId)
            throws ResourceNotFoundException {
        Room room = checkId(roomId, "Room not found: ");
        return ResponseEntity.ok().body(room);
    }

    public Room newRoom (Room room){
        return roomRepository.save(room);
    }

    public ResponseEntity<Room> updateRoom(Long roomId, Room roomDetails)
            throws ResourceNotFoundException{
        Room room = checkId(roomId, "Room not found: ");
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartTime(roomDetails.getStartTime());
        room.setEndTime(roomDetails.getEndTime());

        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);
    }

    public Map<String, Boolean> deleteRoom(Long roomId)
            throws ResourceNotFoundException {
        Room room = checkId(roomId, "Room not found for this id: ");
        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public Room checkId(Long roomId, String message) throws ResourceNotFoundException {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException(message + roomId));
    }
}
