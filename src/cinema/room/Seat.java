package cinema.room;

// artificial class created only for RoomController POST with body
public class Seat {
    private int row;
    private int column;

    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
