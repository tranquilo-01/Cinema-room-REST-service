package cinema.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

public class Room {
    @JsonProperty("total_rows")
    private final Integer rowNumber;
    @JsonProperty("total_columns")
    private final Integer seatsInRowNumber;
    @JsonProperty("available_seats") //todo proper json
    private final boolean[][] seats;

    public Room(Integer rowNumber, Integer seatsInRowNumber) {
        seats = new boolean[rowNumber][seatsInRowNumber];
        this.rowNumber = rowNumber;
        this.seatsInRowNumber = seatsInRowNumber;
    }

    @Override
    public String toString() {
        return "Room: " + rowNumber + " x " + seatsInRowNumber;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Integer getSeatsInRowNumber() {
        return seatsInRowNumber;
    }

    public boolean[][] getSeats() {
        return seats;
    }
}
