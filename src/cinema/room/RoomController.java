package cinema.room;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    private final Room room;

    public RoomController(Room room) {
        this.room = room;
    }

    @GetMapping("room")
    public String getRoomAsString() {
        return this.room.toString();
    }


}
