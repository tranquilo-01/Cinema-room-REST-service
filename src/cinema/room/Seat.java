package cinema.room;

// artificial class created only for RoomController POST with body
public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean bought;
    private String ticketToken;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
        this.bought = false;
        this.ticketToken = "";
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice(){
        return price;
    }

    public boolean isBought() {
        return bought;
    }

    public String getTicketToken() {
        return ticketToken;
    }

    protected int buy(){
        this.bought = true;
//        TODO: generating ticket token
        return getPrice();
    }

}
