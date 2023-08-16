package cinema.room;

public class Room {

    private final Integer rowNumber;
    private final Integer seatsInRowNumber;
    private final Boolean[][] seats;

    public Room(Integer rowNumber, Integer seatsInRowNumber) {
        this.rowNumber = rowNumber;
        this.seatsInRowNumber = seatsInRowNumber;
        seats = new Boolean[rowNumber][seatsInRowNumber];
    }

    @Override
    public String toString() {
        return "Room: " + rowNumber + " x " + seatsInRowNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Integer getSeatsInRowNumber() {
        return seatsInRowNumber;
    }

    public Boolean[][] getSeats() {
        return seats;
    }
}
