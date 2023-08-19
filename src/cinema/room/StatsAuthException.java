package cinema.room;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class StatsAuthException extends RuntimeException {
    public StatsAuthException(String message){
        super(message);
    }
}
