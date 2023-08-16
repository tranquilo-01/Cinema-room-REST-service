package cinema.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    private final Room room;

    public RoomController(Room room) {
        this.room = room;
    }

    @GetMapping("seats")
    public String getRoomAsString() throws JsonProcessingException {
        return this.room.toJson();
    }


}
