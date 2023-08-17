package cinema.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

    @JsonProperty("available_seats")
    private ArrayNode availableSeatsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode availableSeatsArray = objectMapper.createArrayNode();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (!seats[i][j]) {
                    ObjectNode seatNode = objectMapper.createObjectNode();
                    seatNode.put("row", i + 1);
                    seatNode.put("column", j + 1);
                    availableSeatsArray.add(seatNode);
                }
            }
        }
        return availableSeatsArray;
    }
}
