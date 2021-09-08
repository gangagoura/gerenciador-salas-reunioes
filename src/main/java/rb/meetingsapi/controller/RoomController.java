package rb.meetingsapi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rb.meetingsapi.exception.ResourceNotFoundException;
import rb.meetingsapi.model.Room;
import rb.meetingsapi.services.RoomService;

import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomService.showRooms();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId)
            throws ResourceNotFoundException{
        return roomService.getById(roomId);
    }

    @PostMapping("/rooms")
    public Room createRoom (@Valid @RequestBody Room room){
        return roomService.newRoom(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Long roomId, @Valid @RequestBody Room roomDetails)
        throws ResourceNotFoundException{
            return roomService.updateRoom(roomId,roomDetails);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRooms(@PathVariable("id") Long roomId)
        throws ResourceNotFoundException {
            return roomService.deleteRoom(roomId);
    }
}
