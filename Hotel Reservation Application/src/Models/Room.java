package Models;

public class Room implements IRoom {

    private final String roomNumber;
    private final Double RoomPrice;
    private final RoomType enumeration;

    public Room(String roomNumber, Double RoomPrice, RoomType enumeration){
        if (RoomPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.roomNumber = roomNumber;
        this.RoomPrice = RoomPrice;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return RoomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    public boolean isFree() {
        return RoomPrice == 0.0;
    }

    @Override
    public String toString() {
        return "Room{" + "roomNumber: " + roomNumber +
                ", price: " + RoomPrice +
                ", roomType: " + enumeration +
                '}';
    }


}
