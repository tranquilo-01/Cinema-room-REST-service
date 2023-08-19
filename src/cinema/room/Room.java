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
    @JsonProperty("available_seats")
    private final Seat[][] seats;
    private int availableSeatNumber;
    private int income;

    public Room(Integer rowNumber, Integer columnNumber) {
        seats = new Seat[rowNumber][columnNumber];
        for (int i = 0; i < rowNumber; i++) {
            for (int j = 0; j < columnNumber; j++) {
                seats[i][j] = new Seat(i + 1, j + 1);
            }
        }
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.availableSeatNumber = rowNumber * columnNumber;
        this.income = 0;
    }

    @Override
    public String toString() {
        return "Room: " + rowNumber + " x " + columnNumber;
    }

    protected ObjectNode purchaseSeat(int row, int col) {
        if (!isSeatInBounds(row, col)) {
            throw new SeatPurchaseException("The number of a row or a column is out of bounds!");
        }
        Seat seat = getSeat(row, col);
        if (seat.isBought()) {
            throw new SeatPurchaseException("The ticket has been already purchased!");
        }
        ObjectNode response = seat.buy();
        income += response.get("ticket").get("price").asInt();
        availableSeatNumber--;
        return response;
    }

    protected ObjectNode returnTicket(String token) {
        for (Seat[] rows : seats) {
            for (Seat seat : rows) {
                if (seat.getTicketUuid().toString().equals(token)) {
                    ObjectNode response = seat.returnTicket();
                    income -= response.get("returned_ticket").get("price").asInt();
                    availableSeatNumber++;
                    return response;
                }
            }
        }
        throw new SeatPurchaseException("Wrong token!");
    }

    protected ObjectNode getStats() {
        ObjectNode response = new ObjectMapper().createObjectNode();
        response.put("current_income", income);
        response.put("number_of_available_seats", availableSeatNumber);
        response.put("number_of_purchased_tickets", rowNumber * columnNumber - availableSeatNumber);
        return response;
    }

    private boolean isSeatInBounds(int row, int col) {
        return 1 <= row && row <= rowNumber && 1 <= col && col <= columnNumber;
    }

    public Seat getSeat(int row, int col) {
        return seats[row - 1][col - 1];
    }

    @JsonProperty("available_seats")
    private ArrayNode availableSeatsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode availableSeatsArray = objectMapper.createArrayNode();

        for (int i = 1; i <= rowNumber; i++) {
            for (int j = 1; j <= columnNumber; j++) {
                Seat seat = getSeat(i, j);
                if (!seat.isBought()) {
                    ObjectNode seatNode = objectMapper.createObjectNode();
                    seatNode.put("row", i);
                    seatNode.put("column", j);
                    seatNode.put("price", seat.getPrice());
                    availableSeatsArray.add(seatNode);
                }
            }
        }
        return availableSeatsArray;
    }

}
