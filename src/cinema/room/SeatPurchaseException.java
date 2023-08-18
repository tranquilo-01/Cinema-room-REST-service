package cinema.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SeatPurchaseException extends RuntimeException {

    public SeatPurchaseException(String message) {
        super(message);
    }
}
