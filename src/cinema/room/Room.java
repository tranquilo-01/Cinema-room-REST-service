package cinema.room;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Room {
    @JsonProperty("total_rows")
    private final Integer rowNumber;
    @JsonProperty("total_columns")
    private final Integer columnNumber;
    @JsonProperty("available_seats") //todo proper json
    private final boolean[][] seats;

    public Room(Integer rowNumber, Integer columnNumber) {
        seats = new boolean[rowNumber][columnNumber];
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    @Override
    public String toString() {
        return "Room: " + rowNumber + " x " + columnNumber;
    }

    protected int purchaseSeat(int row, int col) {
        if (!isSeatInBounds(row, col)) {
            throw new SeatPurchaseException("The number of a row or a column is out of bounds!");
        }
        if (!isSeatAvailable(row, col)) {
            throw new SeatPurchaseException("The ticket has been already purchased!");
        }

        seats[row - 1][col - 1] = true;

        return getSeatPrice(row, col);
    }

    private boolean isSeatInBounds(int row, int col) {
        return 1 <= row && row <= rowNumber && 1 <= col && col <= columnNumber;
    }

    private int getSeatPrice(int row, int col) {
        return row <= 4 ? 10 : 8;
    }

    @JsonProperty("available_seats")
    private ArrayNode availableSeatsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode availableSeatsArray = objectMapper.createArrayNode();

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (isSeatAvailable(i + 1, j + 1)) {
                    ObjectNode seatNode = objectMapper.createObjectNode();
                    seatNode.put("row", i + 1);
                    seatNode.put("column", j + 1);
                    seatNode.put("price", getSeatPrice(i + 1, j + 1));
                    availableSeatsArray.add(seatNode);
                }
            }
        }
        return availableSeatsArray;
    }

    private boolean isSeatAvailable(int row, int col) {
        return !seats[row - 1][col - 1]; // decremented, so it is an index, not a row number
    }
}
