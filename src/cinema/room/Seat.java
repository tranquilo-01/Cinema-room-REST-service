package cinema.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.UUID;

public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean bought;
    private UUID uuid;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.bought = false;
        this.uuid = new UUID(0, 0);
    }

    public ObjectNode buy() {
        this.bought = true;
        this.uuid = generateUuid();
        return ticketBoughtResponse();
    }

    public ObjectNode returnTicket() {
        if (!this.bought) {
            throw new SeatPurchaseException("Cannot return the seat that is not bought!");
        }
        this.bought = false;
        setNilUuid();
        return ticketReturnedResponse();
    }

    private UUID generateUuid() {
        return UUID.randomUUID();
    }

    private void setNilUuid() {
        this.uuid = new UUID(0, 0);
    }

    private ObjectNode ticketBoughtResponse() {
        ObjectNode ticketNode = objectMapper.createObjectNode();
        ticketNode.put("row", row);
        ticketNode.put("column", column);
        ticketNode.put("price", price);

        ObjectNode responseNode = objectMapper.createObjectNode();
        responseNode.put("token", uuid.toString());
        responseNode.set("ticket", ticketNode);
        return responseNode;

    }

    private ObjectNode ticketReturnedResponse() {
        ObjectNode ticketNode = objectMapper.createObjectNode();
        ticketNode.put("row", row);
        ticketNode.put("column", column);
        ticketNode.put("price", price);

        ObjectNode responseNode = objectMapper.createObjectNode();
        responseNode.set("returned_ticket", ticketNode);
        return responseNode;
    }


    //
//    ----------------getters and setters-------------------
//
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public UUID getTicketUuid() {
        return uuid;
    }


}
