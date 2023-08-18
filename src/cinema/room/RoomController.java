package cinema.room;

import cinema.SimpleErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class RoomController {
    private final Room room;
    ObjectMapper objectMapper = new ObjectMapper();

    public RoomController(Room room) {
        this.room = room;
    }

    @GetMapping("seats")
    public String getRoomAsJson() throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(room);
    }

    @PostMapping("purchase")
    public String purchaseSeat(@RequestBody Seat seat) throws JsonProcessingException {
        int price;
        int row = seat.getRow();
        int column = seat.getColumn();
        price = room.purchaseSeat(row, column);

        Map<String, Integer> successMap = Map.of("row", row, "column", column, "price", price);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(successMap);
    }

    /**
     * method takes over handling SeatPurchaseException in this controller class,
     * that might be thrown in purchaseSeat method.
     * @param exception SeatPurchaseException
     * @return ResponseEntity containing body of simple error message and http 400 status
     */
    @ExceptionHandler(SeatPurchaseException.class)
    public ResponseEntity<SimpleErrorMessage> handleSeatPurchaseException(SeatPurchaseException exception) {
        SimpleErrorMessage body = new SimpleErrorMessage(exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
